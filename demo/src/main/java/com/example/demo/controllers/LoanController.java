package com.example.demo.controllers;

import com.example.demo.entities.Loan;
import com.example.demo.services.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/most-borrowed")
    public Map<Long, Long> mostBorrowedBooks() {
        return loanService.mostBorrowedBooks();
    }
}