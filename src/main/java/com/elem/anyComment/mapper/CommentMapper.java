package com.elem.anyComment.mapper;

import com.elem.anyComment.Document.Comment;
import com.elem.anyComment.Dto.CommentDto;

public class CommentMapper {

    public static CommentDto mapToCommentDto(Comment comment, CommentDto commentDto){
        commentDto.setComment(comment.getComment());
        commentDto.setUserName(comment.getUserName());
        commentDto.setPublicId(comment.getPublicId());
        commentDto.setPath(comment.getPath());
        return commentDto;
    }

    public static Comment mapToComment(CommentDto commentDto, Comment comment){
        comment.setComment(commentDto.getComment());
        comment.setUserName(commentDto.getUserName());
        comment.setPublicId(commentDto.getPublicId());
        comment.setPath(commentDto.getPath());
        return comment;
    }
}
