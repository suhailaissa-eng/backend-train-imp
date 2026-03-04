import entity.Book;
import entity.Member;
import repositoryImpl.BookRepositoryImpl;
import repositoryImpl.LoanRepositoryImpl;
import repositoryImpl.MemberRepositoryImpl;
import service.LibraryService;

public class Main {
    public static void main(String[] args) {

        BookRepositoryImpl bookRepo = BookRepositoryImpl.getInstance();
        MemberRepositoryImpl memberRepo = MemberRepositoryImpl.getInstance();
        LoanRepositoryImpl loanRepo = LoanRepositoryImpl.getInstance();

        LibraryService service = new LibraryService(bookRepo, memberRepo, loanRepo);

        bookRepo.save(new Book(1L,"Clean Code","Robert Martin"));
        bookRepo.save(new Book(2L,"Effective Java","Joshua Bloch"));

        memberRepo.save(new Member(1L,"Ahmad"));
        memberRepo.save(new Member(2L,"Sara"));

        service.borrowBook(1L,1L);
        service.borrowBook(2L,1L);

        service.printBooks();
        service.mostBorrowedBooks();
    }
}