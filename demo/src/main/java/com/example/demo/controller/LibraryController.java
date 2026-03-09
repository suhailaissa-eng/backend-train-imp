package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @PostMapping("/borrow/{memberId}/{bookId}")
    public String borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        return libraryService.borrowBook(memberId, bookId);
    }
}