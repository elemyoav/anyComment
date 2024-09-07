package com.elem.anyComment.service;

import com.elem.anyComment.Document.Comment;
import com.elem.anyComment.Document.User;
import com.elem.anyComment.Dto.CommentDto;
import com.elem.anyComment.Repository.CommentRepository;
import com.elem.anyComment.Repository.UserRepository;
import com.elem.anyComment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    private String randomPublicId(){
        Random random = new Random();
        int randomId = random.nextInt(1_000_000_000); // 0 to 999,999,999
        Long id = 10_000_000_000L + randomId;
        return id.toString();
    }

    public boolean createComment(CommentDto commentDto){

        Comment comment = new Comment();
        comment = CommentMapper.mapToComment(commentDto, comment);
        String publicId = randomPublicId();
        comment.setPublicId(publicId);
        commentDto.setPublicId(publicId);

        Optional<User> userOpt = userRepository.getUserByUserName(comment.getUserName());
        if(userOpt.isEmpty()){
            return false;
        }

        User user = userOpt.get();
        user.getCommentsPublicId().add(publicId);
        commentRepository.save(comment);
        userRepository.save(user);
        return true;
    }

    public List<CommentDto> getCommentsByUserName(String userName){
        List<Comment> comments = commentRepository.findAllByUserName(userName);
        List<CommentDto> commentDtos = comments
                .stream()
                .map(c -> CommentMapper.mapToCommentDto(c, new CommentDto()))
                .collect(Collectors.toList());
        return commentDtos;
    }

    public Optional<CommentDto> getCommentByUserNameAndPublicId(String userName, String publicId){
        Optional<Comment> comment = commentRepository.findByUserNameAndPublicId(userName, publicId);
        Optional<CommentDto> commentDto = comment.map(c->CommentMapper.mapToCommentDto(c, new CommentDto()));
        return commentDto;
    }

    public boolean updateCommentByUserNameAndPublicId(String userName, String publicId, CommentDto commentDto) {
        Optional<Comment> commentOp = commentRepository.findByUserNameAndPublicId(userName, publicId);
        if(commentOp.isEmpty()){
            return false;
        }
        Comment comment = commentOp.get();
        if(commentDto.getComment() != null) {
            comment.setComment(commentDto.getComment());
        }
        if(commentDto.getPath() != null){
            comment.setPath(commentDto.getPath());
        }
        commentRepository.save(comment);
        return true;
    }

    public boolean deleteCommentByUserNameAndPublicId(String userName, String publicId){
        Optional<Comment> comment = commentRepository.findByUserNameAndPublicId(userName, publicId);
        if(comment.isEmpty()){
            return false;
        }

        Optional<User> user = userRepository.getUserByUserName(userName);
        if(user.isEmpty()){
            return false;
        }

        user.get().getCommentsPublicId().remove(publicId);
        String id = comment.get().getId();
        commentRepository.deleteById(id);
        userRepository.save(user.get());

        return true;
    }

    public List<CommentDto> getCommentsByPath(String path){
        List<Comment> comments = commentRepository.getCommentByPath(path);
        List<CommentDto> commentDtos = comments
                .stream()
                .map(c->CommentMapper.mapToCommentDto(c, new CommentDto()))
                .collect(Collectors.toList());
        return commentDtos;
    }
}
