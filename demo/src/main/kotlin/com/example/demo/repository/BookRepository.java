package com.example.demo.repository;

import com.example.demo.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll();
}