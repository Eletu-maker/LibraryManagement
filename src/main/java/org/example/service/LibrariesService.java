package org.example.service;

import org.example.data.model.Book;

import java.util.List;
import java.util.Map;

public interface LibrariesService {
    Map<String,Integer> getAllBook();
    Map<String, List<Book>> getBorrowBook();
}
