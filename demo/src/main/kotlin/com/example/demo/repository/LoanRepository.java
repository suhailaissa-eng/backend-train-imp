package com.example.demo.repository;

import com.example.demo.entity.Loan;
import java.util.List;

public interface LoanRepository {

    void save(Loan loan);

    List<Loan> findAll();
}