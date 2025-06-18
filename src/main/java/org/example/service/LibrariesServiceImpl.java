package org.example.service;

import org.example.data.model.Author;
import org.example.data.model.Book;
import org.example.data.model.Library;
import org.example.data.model.Reader;
import org.example.data.repository.Authors;
import org.example.data.repository.Libraries;
import org.example.data.repository.Readers;
import org.example.dto.request.RemoveBookRequest;
import org.example.dto.response.RemoveBookResponse;
import org.example.exception.RemoveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class LibrariesServiceImpl implements LibrariesService {
    @Autowired
    private Authors authors;
    @Autowired
    private Libraries libraries;
    @Autowired
    private Readers readers;
    @Override
    public Map<String, Integer> getAllBook() {

        List<Author> books = authors.findAll();
        HashMap<String,Integer> bookMap = new HashMap<>();
        for (Author author: books){
            for (Book book:author.getMyBooks()){
                bookMap.put(book.getTitle(),book.getNumber());
            }
        }
       Library library = new Library();
        library.setCollectionOfBook(bookMap);
        libraries.save(library);
        return  library.getCollectionOfBook();
    }

    @Override
    public Map<String, List<Book>> getBorrowBook() {
        List<Reader> read = readers.findAll();
        HashMap<String,List<Book>> borrowMap = new HashMap<>();
        for(Reader reader : read){
            borrowMap.put(reader.getPhoneNumber(),reader.getBooksBorrowed());
        }
        Library library = new Library();
        library.setCollectionOfBorrowers(borrowMap);
        libraries.save(library);

        return library.getCollectionOfBorrowers();
    }

    @Override
    public Map<String, List<Book>> getAfterReturnDay() {
        List<Reader> read = readers.findAll();
        HashMap<String,List<Book>> borrowMap = new HashMap<>();
        for(Reader reader : read){
            List<Book> collection = new ArrayList<>();
            for (Book book: reader.getBooksBorrowed()){
                if (book.getTimeToReturn().isAfter(LocalDate.now())){
                    collection.add(book);
                }
            }
            borrowMap.put(reader.getPhoneNumber(),collection);
        }
        Library library = new Library();
        library.setAfterReturnDay(borrowMap);
        libraries.save(library);

        return borrowMap;
    }

    @Override
    public RemoveBookResponse removeBook(RemoveBookRequest request) {
        RemoveBookResponse response = new RemoveBookResponse();
        Author author = authors.findByName(request.getAuthor());
        if(author == null)throw  new RemoveException("author does not exist");
        if(checkBook(request)) throw new RemoveException("can not remove book because the have not be fully returned");
        Book book = removeTheBook(request);
        if (book == null) throw new RemoveException("book does not exist");
        author.getMyBooks().remove(book);
        authors.save(author);
        response.setMessage("Removed successfully");

        return response;
    }

    private Book removeTheBook(RemoveBookRequest request){
        Author author = authors.findByName(request.getAuthor());
        if(author == null)throw  new RemoveException("author does not exist");
        for(Book book : author.getMyBooks()){
            if(book.getTitle().equals(request.getTitle())){
                return book;
            }
        }
        return null;
    }


    private boolean checkBook(RemoveBookRequest request){
        List<Reader> read = readers.findAll();
        for (Reader reader:read){
            for (Book book:reader.getBooksBorrowed()){
                if(book.getTitle().equals(request.getTitle()) && book.getAuthor().equals(request.getAuthor())){
                    return true;
                }
            }
        }
        return false;
    }


}
