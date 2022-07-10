package com.spring.project01.kaplanjpahibernate.controller;


import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    //    @RequestMapping(value="save/book",method=RequestMethod.POST)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }


    @GetMapping("/get")
//    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getBookByID(@PathVariable("id")Long bookId) {
//        return new ResponseEntity<>(bookService.getBookByID(bookId), HttpStatus.CREATED);
        return bookService.getBookByID(bookId).map(ResponseEntity::ok).
                orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id")Long bookId,@RequestBody Book book) {
//        return new ResponseEntity<>(bookService.getBookByID(bookId), HttpStatus.CREATED);
        return bookService.getBookByID(bookId).map(savedBook->{
            savedBook.setBookName(book.getBookName());
            savedBook.setGenre(book.getGenre());
            Book updatedBook=bookService.saveBook(savedBook);
            return new ResponseEntity<>(updatedBook,HttpStatus.OK);
                }).
                orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id")Long book) {
//        return new ResponseEntity<>(bookService.getBookByID(bookId), HttpStatus.CREATED);
        bookService.deleteBookById(book);
        return new ResponseEntity<String >("Book deleted succesfully!",HttpStatus.OK);
    }
}
