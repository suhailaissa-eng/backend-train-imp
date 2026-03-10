package com.example.demo.controllers;

import com.example.demo.entities.Book;
import com.example.demo.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) return "Book not found";
        return bookService.borrowBook(book);
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) return "Book not found";
        bookService.returnBook(book);
        return "Book returned successfully";
    }
}