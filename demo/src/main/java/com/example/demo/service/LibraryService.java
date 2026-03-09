package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Loan;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.MemberRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    private final BookRepository bookRepo;
    private final MemberRepository memberRepo;
    private final LoanRepository loanRepo;

    public LibraryService(BookRepository bookRepo,
                          MemberRepository memberRepo,
                          LoanRepository loanRepo) {
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
        this.loanRepo = loanRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public String borrowBook(Long memberId, Long bookId) {
        return bookRepo.findById(bookId)
                .map(book -> {
                    if (book.isAvailable()) {
                        book.borrow();
                        bookRepo.save(book);
                        loanRepo.save(new Loan(memberId, bookId, System.currentTimeMillis()));
                        return "Book borrowed successfully";
                    } else {
                        return "Book not available";
                    }
                }).orElse("Book not found");
    }

    public Map<Long, Long> mostBorrowedBooks() {
        return loanRepo.findAll().stream()
                .collect(Collectors.groupingBy(Loan::getBookId, Collectors.counting()));
    }
}