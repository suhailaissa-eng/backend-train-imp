package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Book;
import com.example.demo.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/borrow/{memberId}/{bookId}")
    public String borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        return bookService.borrowBook(bookId, memberId);
    }

    @PostMapping("/return/{bookId}")
    public String returnBook(@PathVariable Long bookId) {
        bookService.returnBook(bookId);
        return "Book returned successfully";
    }
}