package entity;

public class Book {

    private Long id;
    private String title;
    private String author;
    private boolean available;

    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public boolean isAvailable() { return available; }

    public void borrow() {
        this.available = false;
    }

    public void returnBook() {
        this.available = true;
    }

   
}