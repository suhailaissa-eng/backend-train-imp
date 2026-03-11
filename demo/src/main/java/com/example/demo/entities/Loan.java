package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private Long bookId;
    private Long timestamp;

    public Loan(Long memberId, Long bookId, Long timestamp) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.timestamp = timestamp;
    }
    public Long getBookId() {
        return bookId;
    }
    public Long getMemberId() {
        return memberId;
    }
}