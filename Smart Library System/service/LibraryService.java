package service;

import entity.Loan;
import java.util.Map;
import java.util.stream.Collectors;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;

public class LibraryService {

    private BookRepository bookRepo;
    private MemberRepository memberRepo;
    private LoanRepository loanRepo;

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