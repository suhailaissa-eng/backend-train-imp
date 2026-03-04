package repositoryImpl;

import entity.Loan;
import java.util.ArrayList;
import java.util.List;
import repository.LoanRepository;

public class LoanRepositoryImpl implements LoanRepository {

    private static LoanRepositoryImpl instance;
    private List<Loan> loans = new ArrayList<>();

    private LoanRepositoryImpl() {}

    public static synchronized LoanRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new LoanRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void save(Loan loan) { loans.add(loan); }

    @Override
    public List<Loan> findAll() { return loans; }
}