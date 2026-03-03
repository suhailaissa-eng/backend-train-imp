package repositoryImpl;

import entity.Loan;
import repository.LoanRepository;

import java.util.ArrayList;
import java.util.List;

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