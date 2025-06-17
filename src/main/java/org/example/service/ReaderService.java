package org.example.service;

import org.example.data.model.Book;
import org.example.dto.request.BorrowRequest;
import org.example.dto.request.LoginReaderRequest;
import org.example.dto.request.RegisterReaderRequest;
import org.example.dto.response.BorrowResponse;
import org.example.dto.response.LoginReaderResponse;
import org.example.dto.response.RegisterReaderResponse;

import java.util.List;

public interface ReaderService {
    RegisterReaderResponse register(RegisterReaderRequest request);
    LoginReaderResponse login(LoginReaderRequest request);
    List<Book> getAuthorBooks(String author);
    BorrowResponse borrowBook(BorrowRequest request);
}
