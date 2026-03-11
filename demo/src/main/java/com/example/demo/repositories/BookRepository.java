package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByAvailable(boolean available);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword% OR b.author LIKE %:keyword%")
    List<Book> searchBooks(@Param("keyword") String keyword);

    @Query(
        value = "SELECT b.id, b.title, b.author, COUNT(l.id) AS borrow_count " +
                "FROM books b LEFT JOIN loans l ON b.id = l.book_id " +
                "GROUP BY b.id, b.title, b.author " +
                "ORDER BY borrow_count DESC",
        nativeQuery = true
    )
    List<Object[]> findMostBorrowedBooksNative();
}