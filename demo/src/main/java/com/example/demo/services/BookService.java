package com.example.demo.services;

import com.example.demo.entities.Book;
import com.example.demo.entities.Loan;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private LoanRepository loanRepo;

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

    public String borrowBook(Long bookId, Long memberId) {
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) return "Book not found";
        if (!book.isAvailable()) return "Book not available";

        book.borrow();
        bookRepo.save(book);
        loanRepo.save(new Loan(memberId, bookId, System.currentTimeMillis()));
        return "Book borrowed successfully";
    }

    public void returnBook(Long bookId) {
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book != null) {
            book.returnBook();
            bookRepo.save(book);
        }
    }
}