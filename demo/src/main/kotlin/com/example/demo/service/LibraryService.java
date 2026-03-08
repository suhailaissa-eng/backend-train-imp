package com.example.demo.service;

import com.example.demo.entity.Loan;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.MemberRepository;
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

    public void borrowBook(Long memberId, Long bookId) {
        new Thread(() -> {
            bookRepo.findById(bookId).ifPresent(book -> {
                if (book.isAvailable()) {
                    book.borrow();
                    Loan loan = new Loan(System.currentTimeMillis(), memberId, bookId);
                    loanRepo.save(loan);
                    System.out.println("Book borrowed successfully");
                } else {
                    System.out.println("Book not available");
                }
            });
        }).start();
    }

    public void printBooks() {
        bookRepo.findAll().forEach(System.out::println);
    }

    public void mostBorrowedBooks() {
        Map<Long, Long> result = loanRepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Loan::getBookId,
                        Collectors.counting()
                ));
        result.forEach((book, count) ->
                System.out.println("Book " + book + " borrowed " + count + " times"));
    }
}