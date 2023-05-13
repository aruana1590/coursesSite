package kz.aruana.coursesSite.mapper;

import kz.aruana.coursesSite.dto.request.UserDtoResponse;
import kz.aruana.coursesSite.entities.Users;

public class UserMapper {
    public static UserDtoResponse userToDto(Users user){
        UserDtoResponse userDtoResponse= new UserDtoResponse();
        userDtoResponse.setUsername(user.getUsername());
        userDtoResponse.setName(user.getName());
        return userDtoResponse;
    }
}
