package by.loiko.library.entity;

import java.time.LocalDate;
import java.util.Objects;


/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class BookOrder extends Entity {
    
    /** The id. */
    private long id;
    
    /** The user. */
    private User user;
    
    /** The book. */
    private Book book;
    
    /** The date from. */
    private LocalDate dateFrom;
    
    /** The date to. */
    private LocalDate dateTo;
    
    /** The date return. */
    private LocalDate dateReturn;
    
    /** The order type id. */
    private int orderTypeId;
    
    /** The status id. */
    private int statusId;
    
    /** The is deleted. */
    private boolean isDeleted;

    /**
     * Instantiates a new book order.
     */
    public BookOrder() {
    }

    /**
     * Instantiates a new book order.
     *
     * @param id the id
     * @param user the user
     * @param book the book
     * @param dateFrom the date from
     * @param dateTo the date to
     * @param dateReturn the date return
     * @param orderTypeId the order type id
     * @param statusId the status id
     * @param isDeleted the is deleted
     */
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
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the book.
     *
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book.
     *
     * @param book the new book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Gets the date from.
     *
     * @return the date from
     */
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the date from.
     *
     * @param dateFrom the new date from
     */
    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * Gets the date to.
     *
     * @return the date to
     */
    public LocalDate getDateTo() {
        return dateTo;
    }

    /**
     * Sets the date to.
     *
     * @param dateTo the new date to
     */
    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * Gets the date return.
     *
     * @return the date return
     */
    public LocalDate getDateReturn() {
        return dateReturn;
    }

    /**
     * Sets the date return.
     *
     * @param dateReturn the new date return
     */
    public void setDateReturn(LocalDate dateReturn) {
        this.dateReturn = dateReturn;
    }

    /**
     * Gets the order type id.
     *
     * @return the order type id
     */
    public int getOrderTypeId() {
        return orderTypeId;
    }

    /**
     * Sets the order type id.
     *
     * @param orderTypeId the new order type id
     */
    public void setOrderTypeId(int orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    /**
     * Gets the status id.
     *
     * @return the status id
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * Sets the status id.
     *
     * @param statusId the new status id
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    /**
     * Checks if is deleted.
     *
     * @return true, if is deleted
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
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
