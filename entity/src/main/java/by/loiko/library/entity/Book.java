package by.loiko.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class Book extends Entity {
    
    /** The Book id. */
    private long id;
    
    /** The Book title. */
    private String title;
    
    /** The Book publish year. */
    private int publishYear;
    
    /** The Book total amount. */
    private int totalAmount;
    
    /** The Book real amount. */
    private int realAmount;
    
    /** The Book is deleted. */
    private boolean isDeleted;
    
    /** The Book genres list. */
    private List<Genre> genres;
    
    /** The Book authors list. */
    private List<Author> authors;

    /**
     * Instantiates a new book.
     */
    public Book() {
        genres = new ArrayList<>();
        authors = new ArrayList<>();
    }

    /**
     * Instantiates a new book.
     *
     * @param id the id
     * @param title the title
     * @param publishYear the publish year
     * @param totalAmount the total amount
     * @param realAmount the real amount
     * @param isDeleted the is deleted
     * @param genres the genres list
     * @param authors the authors list
     */
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

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the publish year.
     *
     * @return the publish year
     */
    public int getPublishYear() {
        return publishYear;
    }

    /**
     * Sets the publish year.
     *
     * @param publishYear the new publish year
     */
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    /**
     * Gets the total amount.
     *
     * @return the total amount
     */
    public int getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the total amount.
     *
     * @param totalAmount the new total amount
     */
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Gets the real amount.
     *
     * @return the real amount
     */
    public int getRealAmount() {
        return realAmount;
    }

    /**
     * Sets the real amount.
     *
     * @param realAmount the new real amount
     */
    public void setRealAmount(int realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * Gets the checks if is deleted.
     *
     * @return the checks if is deleted
     */
    public boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the checks if is deleted.
     *
     * @param deleted the new checks if is deleted
     */
    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Gets the genres.
     *
     * @return the genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * Sets the genres.
     *
     * @param genres the new genres
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Gets the authors.
     *
     * @return the authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Sets the authors.
     *
     * @param authors the new authors
     */
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
