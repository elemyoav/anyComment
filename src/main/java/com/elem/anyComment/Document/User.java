package com.elem.anyComment.Document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {
    @Id
    String id;
    String userName;
    List<String> commentsPublicId;
}
