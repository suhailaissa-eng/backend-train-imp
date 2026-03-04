package repositoryImpl;

import entity.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import repository.BookRepository;

public class BookRepositoryImpl implements BookRepository {

    private static BookRepositoryImpl instance;
    private List<Book> books = new ArrayList<>();

    private BookRepositoryImpl() {}

    public static synchronized BookRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new BookRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void save(Book book) { books.add(book); }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    @Override
    public List<Book> findAll() { return books; }
}