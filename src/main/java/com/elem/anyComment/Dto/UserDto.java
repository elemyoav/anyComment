package com.elem.anyComment.Dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    String userName;
    List<String> commentsPublicId;
}