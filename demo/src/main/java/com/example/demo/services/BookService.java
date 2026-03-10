package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }

    public String borrowBook(Book book) {
        if (book.isAvailable()) {
            book.borrow();
            bookRepo.save(book);
            return "Book borrowed successfully";
        } else {
            return "Book not available";
        }
    }

    public void returnBook(Book book) {
        book.returnBook();
        bookRepo.save(book);
    }
}