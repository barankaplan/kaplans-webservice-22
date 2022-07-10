package com.spring.project01.kaplanjpahibernate.controller;


import com.spring.project01.kaplanjpahibernate.data.entity.Book;
import com.spring.project01.kaplanjpahibernate.data.entity.Customer;
import com.spring.project01.kaplanjpahibernate.data.entity.PostalCode;
import com.spring.project01.kaplanjpahibernate.service.BookService;
import com.spring.project01.kaplanjpahibernate.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final BookService bookService;

    public CustomerController(CustomerService customerService, BookService bookService) {
        this.customerService = customerService;
        this.bookService = bookService;
    }

    //@ResponseStatus() also can be used!
    @PostMapping("save/customer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> mapCustomerAndBook(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.mapCustomerAndBook(customer), HttpStatus.CREATED);
    }
    @PostMapping("update/customer/v1")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> mapCustomerAndBookUpdateV1(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.mapCustomerAndBookUpdateV1(customer), HttpStatus.CREATED);
    }
    @PostMapping("update/customer/v2")
    public ResponseEntity<?> mapCustomerAndBookUpdateV2 (@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.mapCustomerAndBookUpdateV2(customer), HttpStatus.CREATED);
    }



//    @RequestMapping(value="save/book",method=RequestMethod.POST)
    @PostMapping("/save/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
    @PostMapping("save/postal")
    public ResponseEntity<?> mapCustomerAndPostalCode(@RequestBody PostalCode postalCode) {
        return new ResponseEntity<>(customerService.mapCustomerAndPostalCode(postalCode), HttpStatus.CREATED);
    }

}
