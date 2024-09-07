package com.elem.anyComment.Document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Comment {

    @Id
    String id;
    String publicId;
    String comment;
    @Indexed
    String userName;
    String path;
}
