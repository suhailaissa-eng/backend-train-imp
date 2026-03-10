package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private Long bookId;
    private Long timestamp;

    public Loan() {}
    public Loan(Long memberId, Long bookId, Long timestamp) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public Long getMemberId() { return memberId; }
    public Long getBookId() { return bookId; }
    public Long getTimestamp() { return timestamp; }
}