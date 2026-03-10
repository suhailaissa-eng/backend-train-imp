package com.example.demo.services;

import com.example.demo.entities.Loan;
import com.example.demo.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepo;

    public LoanService(LoanRepository loanRepo) {
        this.loanRepo = loanRepo;
    }

    public List<Loan> getAllLoans() {
        return loanRepo.findAll();
    }

    public Loan saveLoan(Loan loan) {
        return loanRepo.save(loan);
    }

    public Map<Long, Long> mostBorrowedBooks() {
        return loanRepo.findAll().stream()
                .collect(Collectors.groupingBy(Loan::getBookId, Collectors.counting()));
    }
}