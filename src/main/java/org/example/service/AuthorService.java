package org.example.service;

import org.example.data.model.Book;
import org.example.dto.request.BookRequest;
import org.example.dto.request.LoginAuthorRequest;
import org.example.dto.request.RegisterAuthorRequest;
import org.example.dto.response.BookResponse;
import org.example.dto.response.LoginAuthorResponse;
import org.example.dto.response.RegisterAuthorResponse;

public interface AuthorService {
    RegisterAuthorResponse register (RegisterAuthorRequest request);
    LoginAuthorResponse login (LoginAuthorRequest request);
    BookResponse addBook(BookRequest bookRequest);
    BookResponse addToBook(BookRequest bookRequest);

}
