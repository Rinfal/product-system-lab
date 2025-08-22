package com.example.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static com.example.common.security.JwtUtil.validateToken;


@Component
public class AuthTokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();


        //白名单
        if (path.startsWith("/auth/user/login") || path.startsWith("/auth/user/add")) {
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            boolean isValid = validateToken(token);
            boolean inRedis = redisTemplate.hasKey("token:" + token);

            System.out.println("isValid:"  + isValid);
            System.out.println("inRedis:"  + inRedis);

            if (!isValid || !inRedis) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            //若isValid且
            redisTemplate.expire(token, Duration.ofMinutes(30));
        } catch (Exception e) {
            // 捕获远程调用异常
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        //通过验证，从Redis中取得用户信息
        String userJson = redisTemplate.opsForValue().get("token:" + token);
        JSONObject userObj = JSONObject.parseObject(userJson);
        String userId = userObj.getString("userId");
        String userName = userObj.getString("userName");

        //将用户信息加入请求头
        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(
                        exchange.getRequest().mutate()
                                .header("X-User-Id", userId)
                                .header("X-User-Name", userName)
                                .build()
                )
                .build();

        return chain.filter(mutatedExchange);

    }

    @Override
    public int getOrder() {
        return -1;
    }
}

