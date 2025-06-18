package org.example.service;

import org.example.data.model.Book;
import org.example.dto.request.BookRequest;
import org.example.dto.request.LoginAuthorRequest;
import org.example.dto.request.LoginReaderRequest;
import org.example.dto.request.RegisterAuthorRequest;
import org.example.dto.response.LoginAuthorResponse;
import org.example.dto.response.RegisterAuthorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class AuthorServiceImplTest {
    @Autowired
    private AuthorServiceImpl authorService;
    @Autowired
    private LibrariesServiceImpl librariesService;
    @Test
    public void testRegister(){
        RegisterAuthorRequest request = request();
        RegisterAuthorResponse response = authorService.register(request);
        assertEquals("register successful", response.getMessage());
    }

    private RegisterAuthorRequest request(){
        RegisterAuthorRequest request = new RegisterAuthorRequest();
        request.setEmail("usman@gmail.com");
        request.setName("usman");
        request.setPassword("usman1234");
        request.setPhoneNumber("09022735518");
        return request;
    }

    @Test
    public void testLogin(){
        LoginAuthorRequest request = login();
        LoginAuthorResponse response = authorService.login(request);
        assertEquals("login successful", response.getMessage());
    }
    private LoginAuthorRequest login(){
        LoginAuthorRequest request = new LoginAuthorRequest();
        request.setEmail("usman@gmail.com");
        request.setPassword("usman1234");
        return request;
    }

    @Test
    public void testAddBook(){
        BookRequest book = book();
        authorService.addBook(book);


    }

    private BookRequest book(){
        BookRequest book = new BookRequest();
        book.setAuthor("usman");
        book.setTitle("my demo");
        book.setEmail("usman@gmail.com");
        book.setNumber(5);
        return book;
    }

    @Test
    public void testAddToBook(){
        BookRequest book = book();
        authorService.addToBook(book);
    }

    @Test
    public void testGetBooks(){
        Map<String,Integer> book = librariesService.getAllBook();
        assertEquals(2,book.size());

    }

    @Test
    public void testGetBorrowedBook(){
        Map<String, List<Book>> book = librariesService.getBorrowBook();
        assertEquals(1,book.size());
    }

}