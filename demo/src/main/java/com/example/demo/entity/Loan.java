package com.example.demo.entity;

public class Loan {

    private Long timestamp;
    private Long memberId;
    private Long bookId;

    public Loan() {}

    public Loan(Long timestamp, Long memberId, Long bookId) {
        this.timestamp = timestamp;
        this.memberId = memberId;
        this.bookId = bookId;
    }

    public Long getTimestamp() { return timestamp; }
    public Long getMemberId() { return memberId; }
    public Long getBookId() { return bookId; }

    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
}