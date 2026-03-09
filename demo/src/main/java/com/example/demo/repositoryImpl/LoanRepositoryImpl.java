package com.example.demo.repositoryImpl;

import com.example.demo.entity.Loan;
import com.example.demo.repository.LoanRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

    private List<Loan> loans = new ArrayList<>();

    @Override
    public void save(Loan loan) {
        loans.add(loan);
    }

    @Override
    public List<Loan> findAll() {
        return loans;
    }
}