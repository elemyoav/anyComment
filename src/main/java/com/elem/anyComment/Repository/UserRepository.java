package com.elem.anyComment.Repository;

import com.elem.anyComment.Document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> getUserByUserName(String userName);
}
