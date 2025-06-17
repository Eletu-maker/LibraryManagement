package org.example.data.model;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Book {
    private  int number;
    private String title;
    private String author;
    private LocalDate timeCollected;
    private LocalDate timeToReturn;
}
