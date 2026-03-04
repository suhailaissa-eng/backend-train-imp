package entity;

import java.time.LocalDate;

public class Loan {

    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDate date;

    public Loan(Long id, Long memberId, Long bookId) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.date = LocalDate.now();
    }

    public Long getMemberId() { return memberId; }
    public Long getBookId() { return bookId; }

    @Override
    public String toString() {
        return "Loan{" + "id=" + id + ", memberId=" + memberId +
               ", bookId=" + bookId + ", date=" + date + '}';
    }
}