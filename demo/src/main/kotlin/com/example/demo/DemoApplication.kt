package com.example.demo;

import entity.Book;
import entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import repository.BookRepository;
import repository.MemberRepository;
import service.LibraryService;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(BookRepository bookRepo,
                          MemberRepository memberRepo,
                          LibraryService service) {

        return args -> {

            bookRepo.save(new Book(1L,"Clean Code","Robert Martin"));
            bookRepo.save(new Book(2L,"Effective Java","Joshua Bloch"));

            memberRepo.save(new Member(1L,"Ahmad"));
            memberRepo.save(new Member(2L,"Sara"));

            service.borrowBook(1L,1L);
            service.borrowBook(2L,1L);

            service.printBooks();
            service.mostBorrowedBooks();
        };
    }
}