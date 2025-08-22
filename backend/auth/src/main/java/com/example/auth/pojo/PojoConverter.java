package com.example.auth.pojo;

import com.example.auth.pojo.dto.UserDto;
import com.example.auth.pojo.po.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PojoConverter {


    UserDto Po2Dto(User user);

    List<UserDto> Po2DtoList(List<User> userList);



//    @Named("formatDateTime")
//    default String formatDateTime(LocalDateTime time) {
//        if (time == null) return null;
//        time = time.withNano(0);
//        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
