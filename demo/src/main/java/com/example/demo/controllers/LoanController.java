package com.example.demo.controllers;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entities.Loan;
import com.example.demo.services.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ApiResponse<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return new ApiResponse<List<Loan>>(true, 200, "Loans retrieved successfully", loans);
    }

    @GetMapping("/most-borrowed")
    public ApiResponse<Map<Long, Long>> mostBorrowedBooks() {
        Map<Long, Long> result = loanService.mostBorrowedBooks();
        return new ApiResponse<Map<Long, Long>>(true, 200, "Most borrowed books count retrieved successfully", result);
    }
}