package com.example.demo.repositoryImpl;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }
}