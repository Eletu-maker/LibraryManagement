package org.example.service;

import org.example.data.model.Author;
import org.example.data.model.Book;
import org.example.data.model.Library;
import org.example.data.repository.Authors;
import org.example.data.repository.Libraries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class LibrariesServiceImpl implements LibrariesService {
    @Autowired
    private Authors authors;
    @Autowired
    private Libraries libraries;
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
}
