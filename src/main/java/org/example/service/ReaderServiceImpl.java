package org.example.service;

import org.example.data.model.Author;
import org.example.data.model.Book;
import org.example.data.model.Reader;
import org.example.data.repository.Authors;
import org.example.data.repository.Readers;
import org.example.dto.request.*;
import org.example.dto.response.BorrowResponse;
import org.example.dto.response.LoginReaderResponse;
import org.example.dto.response.RegisterReaderResponse;
import org.example.dto.response.ReturnResponse;
import org.example.exception.BorrowException;
import org.example.exception.LoginException;
import org.example.exception.RegisterException;
import org.example.exception.ReturnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService{
    @Autowired
    public Authors authors;
    @Autowired
    public Readers readers;

    @Override
    public RegisterReaderResponse register(RegisterReaderRequest request) {
        RegisterReaderResponse response = new RegisterReaderResponse();
        if(readers.existsByEmail(request.getEmail()) ||
                readers.existsByPhoneNumber(request.getPhoneNumber())) throw new RegisterException("Account already exist");
        save(request);
        response.setMessage("register successful");
        return response;
    }

    @Override
    public LoginReaderResponse login(LoginReaderRequest request) {
        LoginReaderResponse response = new LoginReaderResponse();
        Reader reader = readers.findByEmail(request.getEmail());
        if(reader == null)throw new LoginException("Account does not exist");
        if(!reader.getPassword().equals(request.getPassword())) throw new LoginException("wrong password");
        reader.setLogin(true);
        readers.save(reader);
        response.setMessage("login successful");
        return response;
    }

    @Override
    public List<Book> getAuthorBooks(AuthorName author) {
        Author authorAcc = authors.findByName(author.getName());
        if (authorAcc == null) throw new BorrowException("Author not found");
        return authorAcc.getMyBooks();
    }
    @Override
    public BorrowResponse borrowBook(BorrowRequest request) {
        BorrowResponse response = new BorrowResponse();
        Reader reader= readers.findByEmail(request.getEmail());
        if(!reader.isLogin()) throw new LoginException("need to login");

        if(reader.getBooksBorrowed() == null){
            reader.setBooksBorrowed(new ArrayList<>());
        }

        if (checkBook(request)) throw new BorrowException("cant borrow the same book");

        Book theBook = getTheBook(request);
        if (theBook == null) throw  new BorrowException("book is not in the library");

        reader.getBooksBorrowed().add(theBook);
        readers.save(reader);
        response.setMessage("borrowed successful");
        return response;
    }

    @Override
    public ReturnResponse returnBook(ReturnRequest request) {
        ReturnResponse response = new ReturnResponse();
        Reader reader= readers.findByEmail(request.getEmail());
        if(!reader.isLogin()) throw new LoginException("need to login");
        if(reader.getBooksBorrowed() == null) throw new ReturnException("they is no books in your storage");
        Author authorAcc = authors.findByName(request.getAuthor());
        if (authorAcc == null) throw new BorrowException("Author not found");
        reader.getBooksBorrowed().remove(getBook(request));
        readers.save(reader);
        for (Book book: authorAcc.getMyBooks()){
            if(book.getTitle().equals(request.getTitle())){
                int num = book.getNumber() +1;
                book.setNumber(num);
                book.setTimeCollected(null);
                book.setTimeToReturn(null);
                authors.save(authorAcc);
            }
        }
        response.setMessage("return successful");

        return response;
    }

    private Book getBook(ReturnRequest request){
        Reader reader= readers.findByEmail(request.getEmail());
        if(!reader.isLogin()) throw new LoginException("need to login");
        if(reader.getBooksBorrowed() == null) throw new ReturnException("they is no books in your storage");
        for (Book book:reader.getBooksBorrowed()){
            if(book.getTitle().equals(request.getTitle()) && book.getAuthor().equals(request.getAuthor())){
                return book;
            }
        }
        return  null;

    }


    private boolean checkBook(BorrowRequest request){
        Reader reader= readers.findByEmail(request.getEmail());
        if (reader.getBooksBorrowed() == null) return false;
        for(Book book: reader.getBooksBorrowed()){
            if(book.getTitle().equals(request.getTitle()) && book.getAuthor().equals(request.getAuthor())){
                return true;
            }
        }
        return false;
    }

    private Book getTheBook(BorrowRequest request){
        Author authorAcc = authors.findByName(request.getAuthor());
        if (authorAcc == null) throw new BorrowException("Author not found");
        for (Book book:authorAcc.getMyBooks()){
            if(book.getTitle().equals(request.getTitle())){
                if(book.getNumber() <= 0) throw new BorrowException("out of book");
                int num = book.getNumber() -1;
                book.setNumber(num);
                authors.save(authorAcc);


                Book borrowedBook = new Book();
                borrowedBook.setTitle(book.getTitle());
                borrowedBook.setAuthor(book.getAuthor());
                borrowedBook.setTimeCollected(LocalDate.now());
                borrowedBook.setTimeToReturn(LocalDate.now().plusDays(7));
                return borrowedBook;

            }
        }
        return null;
    }


    private void save(RegisterReaderRequest request){
        Reader reader = new Reader();
        reader.setName(request.getName());
        reader.setEmail(request.getEmail());
        reader.setPhoneNumber(request.getPhoneNumber());
        reader.setPassword(request.getPassword());
        readers.save(reader);

    }
}
