package org.example.data.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document("Reader")
public class Reader extends User{
    @Id
    private String id;
    private List<Book> booksBorrowed;
}
