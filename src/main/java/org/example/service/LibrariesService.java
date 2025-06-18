package org.example.service;

import org.example.data.model.Book;
import org.example.dto.request.RemoveBookRequest;
import org.example.dto.response.RemoveBookResponse;

import java.util.List;
import java.util.Map;

public interface LibrariesService {
    Map<String,Integer> getAllBook();
    Map<String, List<Book>> getBorrowBook();
    Map<String, List<Book>> getAfterReturnDay();
    RemoveBookResponse removeBook(RemoveBookRequest request);

}
