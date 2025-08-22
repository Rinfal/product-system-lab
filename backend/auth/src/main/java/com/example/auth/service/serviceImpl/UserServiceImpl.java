package com.example.auth.service.serviceImpl;
import com.alibaba.fastjson.JSON;
import com.example.auth.exception.UserErrorCode;
import com.example.auth.mapper.UserMapper;
import com.example.auth.pojo.PojoConverter;
import com.example.auth.pojo.dto.UserDto;
import com.example.auth.pojo.po.User;
import com.example.auth.service.IUserService;
import com.example.common.security.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PojoConverter pojoConverter;

    @Override
    public String add(UserDto userDto) {

        //验证用户是否已注册
        UserErrorCode.USER_HAS_REGISTERED.assertNull(getUserByName(userDto.getUserName()));
        //验证密码是否重复
        UserErrorCode.PASSWORD_HAS_EXISTED.assertNull(getUserByPassword(BCrypt.hashpw(userDto.getUserPassword(),BCrypt.gensalt())));
        //加密密码
        userDto.setUserPassword(BCrypt.hashpw(userDto.getUserPassword(), BCrypt.gensalt()));
        //存入数据库
        userMapper.insert(userDto);
        return "Success";
    }

    @Override
    public String login(UserDto  userDto) {
        //分别从前后端取数据
        String userName = userDto.getUserName();
        String userPassword = userDto.getUserPassword();
        User user = userMapper.getUserPassword(userName);

        //验证用户是否存在
        UserErrorCode.USER_NOT_FOUND.assertNotNull(user);

        //验证登陆密码是否正确
        boolean matches = new BCryptPasswordEncoder().matches(userPassword, user.getUserPassword());
        UserErrorCode.WRONG_PASSWORD.assertNotFalse(matches);

        //生成token：取token需要遍历，太麻烦了
        String token = JwtUtil.generateToken(user.getUserId());

        //将token与用户信息存入Redis
        String key = "token:" + token;
        //写一个map方便后面加其他信息
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("userName", user.getUserName());
        String value = JSON.toJSONString(map);
        //没法用注解，只能用template了
        redisTemplate.opsForValue().set("token:" + token, value, Duration.ofMinutes(30));

        return token;
    }

    @Override
    public UserDto getUserById(String userId){
        pojoConverter.Po2Dto(userMapper.getUserById(userId));

        return pojoConverter.Po2Dto(userMapper.getUserById(userId));
    }

    @Override
    public UserDto getUserByName(String userName){

        return pojoConverter.Po2Dto(userMapper.getUserByName(userName));
    }

    @Override
    public UserDto getUserByPassword(String userPassword){

        return pojoConverter.Po2Dto(userMapper.getUserByPassword(userPassword));
    }


}
