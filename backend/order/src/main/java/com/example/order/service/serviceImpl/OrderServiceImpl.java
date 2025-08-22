package com.example.order.service.serviceImpl;

import com.example.order.feignClients.ProductFeignClient;
import com.example.order.feignClients.ScheduleFeignClient;
import com.example.order.feignClients.UserFeignClient;
import com.example.order.mapper.OrderMapper;
import com.example.order.pojo.PojoConverter;
import com.example.order.pojo.dto.OrderDto;
import com.example.order.pojo.po.Order;
import com.example.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    //查询库存
    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private ScheduleFeignClient scheduleFeignClient;

    @Resource
    private OrderMapper orderMapper;
    @Autowired
    private PojoConverter pojoConverter;

    public String generateOrderNo() {
        long timestamp = System.currentTimeMillis(); // 当前时间戳：13位
        int randomPart = (int)(Math.random() * 100000); // 5位随机数
        return "ORD" + timestamp + String.format("%05d", randomPart);
    }

    //查询库存
    @Override
    public Integer getStockById(String productId) {
        System.out.println(productFeignClient.getStockById(productId));
        return productFeignClient.getStockById(productId);

    }

    @Override
    public void cancelOrder(String orderId){
        //还回库存
        Order releasingOrder = orderMapper.getOrderById(orderId);
        productFeignClient.releaseStock(releasingOrder.getProductId(), releasingOrder.getOrderAmount());
        //取消订单
        orderMapper.cancelOrder(orderId);
    }

    @Override
    public void reduceStock(String productId, Integer orderAmount){
        //用feign减少订单库存
        productFeignClient.reduceStock( productId, orderAmount);
    }

    @Override
    public void releaseStock(String productId, Integer orderAmount){

        //当订单失效时还回库存
        productFeignClient.releaseStock(productId, orderAmount);
    }



    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        //通过锁实现防止同用户同商品重复请求

        synchronized ((orderDto.getUserId() + "-" + orderDto.getProductId()).intern()){
            Order existingOrder = orderMapper.getUnpaidOrder(orderDto.getUserId(), orderDto.getProductId());
            System.out.println("existingOrder" + existingOrder);
            if (existingOrder != null) {
                System.out.println("existingOrder");
                return pojoConverter.Po2Dto(existingOrder);
            }

            //暂时设为定值1---------------------------------------------！！！
            orderDto.setOrderAmount(1);
            //校验库存
            if (getStockById(orderDto.getProductId()) > orderDto.getOrderAmount()) {
                //通过feign拿到userName
                //String UserName = userFeignClient.getUserNameById(orderDto.getUserId());
                orderDto.setProductName(productFeignClient.getProductNameById(orderDto.getProductId()));
                orderDto.setOrderStatus(0);
                orderDto.setOrderTime(LocalDateTime.now());
                orderDto.setOrderId(generateOrderNo());
                orderDto.setExpireTime(orderDto.getOrderTime().plusMinutes(1));
                //设置用户名
                orderDto.setUserName(userFeignClient.getUserNameById(orderDto.getUserId()));
                //设置价格
                BigDecimal unitPrice = productFeignClient.getProductPriceById(orderDto.getProductId());
                orderDto.setUnitPrice(unitPrice);
                BigDecimal totalPrice = unitPrice.multiply(new BigDecimal(orderDto.getOrderAmount()));
                orderDto.setTotalPrice(totalPrice);
                System.out.println(orderDto);

                //创建订单
                orderMapper.createOrder(orderDto);
                //减少库存
                productFeignClient.reduceStock(orderDto.getProductId(), orderDto.getOrderAmount());
                //创建失效任务
                scheduleFeignClient.scheduleCancelOrder(orderDto.getOrderId(), 10L);
                return orderDto;

            }
        }

        return orderDto;
    }
}



