package org.example.data.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document("Author")
public class Author extends User {
    @Id
    private String id;
    private List<Book> myBooks ;

}
