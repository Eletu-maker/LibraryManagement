package org.example.data.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Document("Library")
public class Library {
    @Id
    private String id;
    private Map<String,Integer> collectionOfBook;
    private Map<String,List<Book>> collectionOfBorrowers;
    private List<HashMap<Reader,Book>> afterReturnDay;

}
