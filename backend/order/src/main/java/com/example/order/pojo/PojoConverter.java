package com.example.order.pojo;


import com.example.order.pojo.dto.OrderDto;
import com.example.order.pojo.po.Order;
import com.example.order.pojo.vo.OrderVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PojoConverter {


    //@Mapping(source = "orderTime", target = "orderTimeView", qualifiedByName = "formatDateTime")
    @Mapping(source = "orderStatus",target = "orderStatusView",qualifiedByName = "formatStatus")
    OrderVo Dto2Vo(OrderDto orderDto);

    OrderDto Po2Dto(Order order);

//    @Named("formatDateTime")
//    default String formatDateTime(LocalDateTime time) {
//        if (time == null) return null;
//        time = time.withNano(0);
//        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }

    @Named("formatStatus")
    default String formatStatus(Integer orderStatus) {
        if (orderStatus == null) return "未知状态";
        return switch (orderStatus) {
            case 0 -> "未支付";
            case 1 -> "已支付";
            case 2 -> "已完成";
            case 3 -> "已失效";
            default -> "未知状态";
        };


    }
}


