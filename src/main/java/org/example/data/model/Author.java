package org.example.data.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@Document("Librarian")
public class Librarian extends User {
    @Id
    private long id;
    private List<Book> myBooks;
}
