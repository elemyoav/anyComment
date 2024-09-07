package com.elem.anyComment.Repository;

import com.elem.anyComment.Document.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByUserName(String userName);
    Optional<Comment> findByUserNameAndPublicId(String userName, String publicId);
    List<Comment> getCommentByPath(String path);
    void deleteAllByUserName(String userName);
}
