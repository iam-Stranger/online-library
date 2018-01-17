package by.loiko.library.entity;

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
   private boolean deleted;

    public Book() {
    }

    public Book(long id, String title, int publishYear, int totalAmount, int realAmount, boolean deleted) {
        this.id = id;
        this.title = title;
        this.publishYear = publishYear;
        this.totalAmount = totalAmount;
        this.realAmount = realAmount;
        this.deleted = deleted;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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
                deleted == book.deleted &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, publishYear, totalAmount, realAmount, deleted);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishYear=" + publishYear +
                ", totalAmount=" + totalAmount +
                ", realAmount=" + realAmount +
                ", deleted=" + deleted +
                '}';
    }
}
