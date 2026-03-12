package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Book;
import com.example.demo.entities.Loan;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.LoanRepository;

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
        return bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }

    public String borrowBook(Long bookId, Long memberId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookId + " not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book not available");
        }

        book.borrow();
        bookRepo.save(book);
        loanRepo.save(new Loan(memberId, bookId, System.currentTimeMillis()));
        return "Book borrowed successfully";
    }

    public void returnBook(Long bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookId + " not found"));
        book.returnBook();
        bookRepo.save(book);
    }

    public List<Book> searchBooks(String keyword) {
        return bookRepo.searchBooks(keyword);
    }

    public List<Book> getAvailableBooks() {
        return bookRepo.findByAvailable(true);
    }

    public List<Object[]> getMostBorrowedBooksNative() {
        return bookRepo.findMostBorrowedBooksNative();
    }
}