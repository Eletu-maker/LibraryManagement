package org.example.service;

import org.example.data.model.Author;
import org.example.data.model.Book;
import org.example.data.model.Reader;
import org.example.data.repository.Authors;
import org.example.data.repository.Readers;
import org.example.dto.request.BorrowRequest;
import org.example.dto.request.LoginReaderRequest;
import org.example.dto.request.RegisterReaderRequest;
import org.example.dto.response.BorrowResponse;
import org.example.dto.response.LoginReaderResponse;
import org.example.dto.response.RegisterReaderResponse;
import org.example.exception.LoginException;
import org.example.exception.RegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Book> getAuthorBooks(String author) {
        Author authorAcc = authors.findByName(author);
        return authorAcc.getMyBooks();
    }

    @Override
    public BorrowResponse borrowBook(BorrowRequest request) {
        BorrowResponse response = new BorrowResponse();
        //List<Author> authorsBook = authors.findAll()
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
