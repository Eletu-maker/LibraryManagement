package org.example.controller;

import org.example.data.model.Book;
import org.example.dto.request.*;
import org.example.dto.response.*;
import org.example.service.AuthorServiceImpl;
import org.example.service.LibrariesServiceImpl;
import org.example.service.ReaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private AuthorServiceImpl authorService;
    @Autowired
    private LibrariesServiceImpl librariesService;
    @Autowired
    private ReaderServiceImpl readerService;

    @PostMapping("/registerAuthor")
    public ResponseEntity<?> registerAuthor (@RequestBody RegisterAuthorRequest request){
        try {
            RegisterAuthorResponse response = authorService.register(request);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/loginAuthor")
    public ResponseEntity<?> loginAuthor(@RequestBody LoginAuthorRequest request){
        try{
            LoginAuthorResponse response = authorService.login(request);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerReader")
    public ResponseEntity<?> registerReader (@RequestBody RegisterReaderRequest request){
        try{
            RegisterReaderResponse response = readerService.register(request);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/loginReader")
    public ResponseEntity<?> loginReader (@RequestBody LoginReaderRequest request){
        try {
            LoginReaderResponse response = readerService.login(request);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody BookRequest request){
        try {
            BookResponse response = authorService.addBook(request);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addToBook")
    public ResponseEntity<?> addToBook(@RequestBody BookRequest request){
        try {
            BookResponse response = authorService.addToBook(request);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getAllBook")
    public ResponseEntity<?> getAllBook(){
        try {
            Map<String,Integer> response = librariesService.getAllBook();
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getAuthorBooks")
    public ResponseEntity<?> getAuthorBooks(@RequestBody AuthorName author){
        try {
            List<Book> response = readerService.getAuthorBooks(author);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request){
        try {
            BorrowResponse response = readerService.borrowBook(request);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getBorrowBook")
    public ResponseEntity<?> getBorrowBook(){
        try {
            Map<String, List<Book>> response = librariesService.getBorrowBook();
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(@RequestBody ReturnRequest request){
        try {
            ReturnResponse response = readerService.returnBook(request);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/removeBook")
    public ResponseEntity<?> removeBook(@RequestBody RemoveBookRequest request){
        try {
            RemoveBookResponse response = librariesService.removeBook(request);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
