package com.example.demo.services;

import com.example.demo.entities.Loan;
import com.example.demo.repositories.LoanRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepo;

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