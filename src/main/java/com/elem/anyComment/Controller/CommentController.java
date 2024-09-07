package com.elem.anyComment.Controller;

import com.elem.anyComment.Dto.CommentDto;
import com.elem.anyComment.Dto.UserDto;
import com.elem.anyComment.service.CommentService;
import com.elem.anyComment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    // Comments
    @GetMapping("comments/{userName}/{publicId}")
    public ResponseEntity<CommentDto> fetchComment(@PathVariable String userName, @PathVariable String publicId){
        Optional<CommentDto> commentDto = commentService.getCommentByUserNameAndPublicId(userName, publicId);
        if (commentDto.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentDto.get());
    }

    @GetMapping("/comments/forPath")
    public ResponseEntity<List<CommentDto>> commentsByPath(@RequestParam String path){
        List<CommentDto> commentDtos = commentService.getCommentsByPath(path);
        if(commentDtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentDtos);
    }

    @GetMapping("comments/{userName}")
    public ResponseEntity<List<CommentDto>> fetchComment(@PathVariable String userName){
        List<CommentDto> comments = commentService.getCommentsByUserName(userName);

        if(comments.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping("/comments")
    public ResponseEntity<Void> addComment(@RequestBody CommentDto comment){
        boolean isCreated = commentService.createComment(comment);
        if(!isCreated){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("comments/{userName}/{publicId}")
    public ResponseEntity<Void> updateComment(@PathVariable String userName, @PathVariable String publicId, @RequestBody CommentDto commentDto){
        boolean isUpdated = commentService.updateCommentByUserNameAndPublicId(userName, publicId, commentDto);
        if(!isUpdated){
            return  ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("comments/{userName}/{publicId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String userName, @PathVariable String publicId){
        boolean isDeleted = commentService.deleteCommentByUserNameAndPublicId(userName, publicId);
        if(!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //User
    @GetMapping("/user/{userName}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userName){
        Optional<UserDto> userDto = userService.getUser(userName);
        if(userDto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userDto.get());
    }

    @PostMapping("/user")
    public ResponseEntity<Void> postUser(@RequestBody UserDto userDto){
        userService.postUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName){
        boolean isDeleted = userService.deleteUser(userName);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/user")
    public ResponseEntity<Void> putUser(@RequestBody UserDto userDto){
        boolean isUpdated = userService.putUser(userDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
