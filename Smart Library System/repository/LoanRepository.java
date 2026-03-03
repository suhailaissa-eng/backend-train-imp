package repository;

import entity.Loan;

import java.util.List;

public interface LoanRepository {

    void save(Loan loan);

    List<Loan> findAll();
}