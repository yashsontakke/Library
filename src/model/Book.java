package model;

public class Book {
    String title ;
    Author author ;
    int price ;

    public Book(String title, Author author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }
}

