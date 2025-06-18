package org.example.service;

import org.example.data.model.Book;
import org.example.data.repository.Readers;
import org.example.dto.request.BorrowRequest;
import org.example.dto.request.LoginReaderRequest;
import org.example.dto.request.RegisterReaderRequest;
import org.example.dto.request.ReturnRequest;
import org.example.dto.response.BorrowResponse;
import org.example.dto.response.LoginReaderResponse;
import org.example.dto.response.RegisterReaderResponse;
import org.example.dto.response.ReturnResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Reader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ReaderServiceImplTest {
    @Autowired
    public ReaderServiceImpl readerService;

@Test
    public void testRegister(){
    RegisterReaderRequest request = request();
    RegisterReaderResponse response = readerService.register(request);
    assertEquals("register successful", response.getMessage());

}

private RegisterReaderRequest request(){
    RegisterReaderRequest request = new RegisterReaderRequest();
    request.setEmail("eric@gmail.com");
    request.setName("eric");
    request.setPassword("eric123");
    request.setPhoneNumber("09033527715");
    return request;
}

@Test
    public void testLogin(){
    LoginReaderRequest request = login();
    LoginReaderResponse response = readerService.login(request);
    assertEquals("login successful", response.getMessage());

}
    private LoginReaderRequest login(){
    LoginReaderRequest request = new LoginReaderRequest();
    request.setEmail("eric@gmail.com");
    request.setPassword("eric123");
    return request;
    }

    @Test
    public void testGetAuthor(){
        List<Book> books = readerService.getAuthorBooks("usman");
        System.out.println(books);
        assertEquals(2,books.size());
    }

    @Test
    public  void testBorrowBook(){
        BorrowResponse response = readerService.borrowBook(borrowRequest());
        assertEquals("borrowed successful",response.getMessage());
    }

    private BorrowRequest borrowRequest(){
        BorrowRequest request = new BorrowRequest();
        request.setTitle("my temo");
        request.setAuthor("usman");
        request.setEmail("eric@gmail.com");
        return request;
    }

    @Test
    public void testReturnBook(){
        ReturnResponse response = readerService.returnBook(returnRequest());
        assertEquals("return successful",response.getMessage());
    }

    private ReturnRequest returnRequest(){
        ReturnRequest request = new ReturnRequest();
        request.setTitle("my temo");
        request.setAuthor("usman");
        request.setEmail("eric@gmail.com");
        return request;
    }

}