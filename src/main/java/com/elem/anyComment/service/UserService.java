package com.elem.anyComment.service;

import com.elem.anyComment.Document.User;
import com.elem.anyComment.Dto.UserDto;
import com.elem.anyComment.Repository.CommentRepository;
import com.elem.anyComment.Repository.UserRepository;
import com.elem.anyComment.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    public Optional<UserDto> getUser(String userName){
        Optional<User> user = userRepository.getUserByUserName(userName);
        Optional<UserDto> userDto = user.map(u-> UserMapper.mapToUserDto(u, new UserDto()));
        return  userDto;
    }

    public void postUser(UserDto userDto){
        Optional<User> userOpt = userRepository.getUserByUserName(userDto.getUserName());
        if(userOpt.isPresent()){
            return;
        }
        userDto.setCommentsPublicId(new ArrayList<>());
        User user = UserMapper.mapToUser(userDto, new User());
        userRepository.save(user);
        return;
    }

    public boolean deleteUser(String userName){
        Optional<User> user = userRepository.getUserByUserName(userName);
        if(user.isEmpty()){
            return false;
        }

        userRepository.delete(user.get());
        commentRepository.deleteAllByUserName(userName);
        return true;
    }

    public boolean putUser(UserDto userDto){
        Optional<User> userOp = userRepository.getUserByUserName(userDto.getUserName());
        if(userOp.isEmpty()){
            return false;
        }
        User user = userOp.get();
        user.setUserName(userDto.getUserName());
        user.setCommentsPublicId(userDto.getCommentsPublicId());
        userRepository.save(user);
        return true;
    }
}
