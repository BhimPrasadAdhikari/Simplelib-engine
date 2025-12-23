package com.luminalib.model ;

public class Book extends AbstractResource {
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;

    }

    @Override
    public int getLoanPeriodInDays() {
        return 14;
    }

    public String getAuthor() { return author;}
    public String getIsbn() {return isbn;}

}
