package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

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