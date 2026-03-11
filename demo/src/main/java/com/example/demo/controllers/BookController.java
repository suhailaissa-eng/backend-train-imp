package com.example.demo.controllers;

import com.example.demo.entities.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword) {
        return bookService.searchBooks(keyword);
    }

    @GetMapping("/available")
    public List<Book> availableBooks() {
        return bookService.getAvailableBooks();
    }

    @GetMapping("/most-borrowed")
    public List<Object[]> mostBorrowedBooksNative() {
        return bookService.getMostBorrowedBooksNative();
    }
}