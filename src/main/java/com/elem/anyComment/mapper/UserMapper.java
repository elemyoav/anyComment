package com.elem.anyComment.mapper;

import com.elem.anyComment.Document.User;
import com.elem.anyComment.Dto.UserDto;

public class UserMapper {

    public static UserDto mapToUserDto(User user, UserDto userDto){
        userDto.setUserName(user.getUserName());
        userDto.setCommentsPublicId(user.getCommentsPublicId());
        return userDto;
    }

    public static User mapToUser(UserDto userDto, User user){
        user.setUserName(userDto.getUserName());
        user.setCommentsPublicId(userDto.getCommentsPublicId());
        return user;
    }
}
