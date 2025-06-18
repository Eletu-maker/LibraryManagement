package org.example.service;

import org.example.data.model.Author;
import org.example.data.model.Book;
import org.example.data.model.Library;
import org.example.data.repository.Authors;
import org.example.data.repository.Libraries;
import org.example.dto.request.BookRequest;
import org.example.dto.request.LoginAuthorRequest;
import org.example.dto.request.RegisterAuthorRequest;
import org.example.dto.response.BookResponse;
import org.example.dto.response.LoginAuthorResponse;
import org.example.dto.response.RegisterAuthorResponse;
import org.example.exception.AddException;
import org.example.exception.LoginException;
import org.example.exception.RegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorServiceImpl implements AuthorService{
    //private Library library = new Library();
    @Autowired
    public Libraries libraries;
    @Autowired
    public Authors authors;
    @Override
    public RegisterAuthorResponse register(RegisterAuthorRequest request) {
        RegisterAuthorResponse response = new RegisterAuthorResponse();
        if(authors.existsByEmail(request.getEmail())
                || authors.existsByPhoneNumber(request.getPhoneNumber())) throw new RegisterException("Account already exist");
        if(authors.existsByEmail(request.getName())) throw new RegisterException("another Author is using the name");
       save(request);
       response.setMessage("register successful");
       return response;
    }

    @Override
    public LoginAuthorResponse login(LoginAuthorRequest request) {
        LoginAuthorResponse response = new LoginAuthorResponse();
        Author author = authors.findByEmail(request.getEmail());
        if(author == null)throw new LoginException("Account does not exist");
        if(!author.getPassword().equals(request.getPassword()))throw new LoginException("wrong password");
        author.setLogin(true);
        authors.save(author);
        response.setMessage("login successful");
        return response;
    }
    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        BookResponse response = new BookResponse();
        Author author = authors.findByEmail(bookRequest.getEmail());
        if (author == null) throw new RegisterException("need to register before you can upload a book");
        if (!author.isLogin()) throw new LoginException("need to login");

        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setNumber(bookRequest.getNumber());
        book.setTitle(bookRequest.getTitle());


        if (author.getMyBooks() == null) {
            author.setMyBooks(new ArrayList<>());
        }
        if(checkBook(bookRequest))throw new AddException("book already in library");
        author.getMyBooks().add(book);
        authors.save(author);
        response.setMessage("Book added successfully");
        return  response;

    }

    @Override
    public BookResponse addToBook(BookRequest bookRequest) {
        BookResponse response = new BookResponse();
        if(checkBook(bookRequest)){
            Author author = authors.findByEmail(bookRequest.getEmail());
            for (Book book: author.getMyBooks()){
                if(book.getTitle().equals(bookRequest.getTitle())){
                    int num = book.getNumber() + bookRequest.getNumber();
                    book.setNumber(num);

                }


            }
            authors.save(author);
        }

        response.setMessage("Book added successfully");
        return  response;
    }


    private void save(RegisterAuthorRequest request){
        Author author =new Author();
        author.setEmail(request.getEmail());
        author.setName(request.getName());
        author.setPassword(request.getPassword());
        author.setPhoneNumber(request.getPhoneNumber());
        authors.save(author);
    }

    private boolean checkBook(BookRequest bookRequest){
        Author author = authors.findByEmail(bookRequest.getEmail());
        if(author.getMyBooks() == null) return false;
        for (Book book: author.getMyBooks()){
            if(book.getTitle().equals(bookRequest.getTitle()))
                return true;
        }
        return false;
    }
}
