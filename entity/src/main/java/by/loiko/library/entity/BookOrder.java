package by.loiko.library.entity;

import java.time.LocalDate;
import java.util.Objects;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class BookOrder extends Entity {
    private long id;
    private User user;
    private Book book;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate dateReturn;
    private int orderTypeId;
    private int statusId;
    private boolean isDeleted;

    public BookOrder() {
    }

    public BookOrder(long id, User user, Book book, LocalDate dateFrom, LocalDate dateTo, LocalDate dateReturn, int orderTypeId, int statusId, boolean isDeleted) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dateReturn = dateReturn;
        this.orderTypeId = orderTypeId;
        this.statusId = statusId;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(LocalDate dateReturn) {
        this.dateReturn = dateReturn;
    }

    public int getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(int orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookOrder)) return false;
        BookOrder bookOrder = (BookOrder) o;
        return id == bookOrder.id &&
                orderTypeId == bookOrder.orderTypeId &&
                statusId == bookOrder.statusId &&
                isDeleted == bookOrder.isDeleted &&
                Objects.equals(user, bookOrder.user) &&
                Objects.equals(book, bookOrder.book) &&
                Objects.equals(dateFrom, bookOrder.dateFrom) &&
                Objects.equals(dateTo, bookOrder.dateTo) &&
                Objects.equals(dateReturn, bookOrder.dateReturn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, book, dateFrom, dateTo, dateReturn, orderTypeId, statusId, isDeleted);
    }

    @Override
    public String toString() {
        return "BookOrder{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", dateReturn=" + dateReturn +
                ", orderTypeId=" + orderTypeId +
                ", statusId=" + statusId +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
