package by.loiko.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class Book extends Entity {
    private long id;
    private String title;
    private int publishYear;
    private int totalAmount;
    private int realAmount;
    private boolean isDeleted;
    private List<Genre> genres;
    private List<Author> authors;

    public Book() {
        genres = new ArrayList<>();
        authors = new ArrayList<>();
    }

    public Book(long id, String title, int publishYear, int totalAmount, int realAmount, boolean isDeleted, List<Genre> genres, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.publishYear = publishYear;
        this.totalAmount = totalAmount;
        this.realAmount = realAmount;
        this.isDeleted = isDeleted;
        this.genres = genres;
        this.authors = authors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(int realAmount) {
        this.realAmount = realAmount;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id &&
                publishYear == book.publishYear &&
                totalAmount == book.totalAmount &&
                realAmount == book.realAmount &&
                isDeleted == book.isDeleted &&
                Objects.equals(title, book.title) &&
                Objects.equals(genres, book.genres) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, publishYear, totalAmount, realAmount, isDeleted, genres, authors);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishYear=" + publishYear +
                ", totalAmount=" + totalAmount +
                ", realAmount=" + realAmount +
                ", isDeleted=" + isDeleted +
                ", genres=" + genres +
                ", authors=" + authors +
                '}';
    }
}
