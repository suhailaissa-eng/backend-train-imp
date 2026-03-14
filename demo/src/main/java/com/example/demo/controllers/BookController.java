package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
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
    public ApiResponse<Book> getBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return new ApiResponse<Book>(true, 200, "Book retrieved successfully", book);
    }
    @GetMapping
    public ApiResponse<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ApiResponse<>(true, 200, "All books retrieved", books);
    }

    @PostMapping
    public ApiResponse<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ApiResponse<Book>(true, 201, "Book created successfully", savedBook);
    }

    @PostMapping("/borrow/{memberId}/{bookId}")
    public ApiResponse<String> borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        String message = bookService.borrowBook(bookId, memberId);
        return new ApiResponse<String>(true, 200, message, null);
    }

    @PostMapping("/return/{bookId}")
    public ApiResponse<String> returnBook(@PathVariable Long bookId) {
        bookService.returnBook(bookId);
        return new ApiResponse<String>(true, 200, "Book returned successfully", null);
    }

    @GetMapping("/search")
    public ApiResponse<List<Book>> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        return new ApiResponse<List<Book>>(true, 200, "Books retrieved successfully", books);
    }

    @GetMapping("/available")
    public ApiResponse<List<Book>> availableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        return new ApiResponse<List<Book>>(true, 200, "Available books retrieved successfully", books);
    }

    @GetMapping("/most-borrowed")
    public ApiResponse<List<Object[]>> mostBorrowedBooksNative() {
        List<Object[]> books = bookService.getMostBorrowedBooksNative();
        return new ApiResponse<List<Object[]>>(true, 200, "Most borrowed books retrieved successfully", books);
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ApiResponse<>(true, 200, "Book deleted successfully", null);
    }
}