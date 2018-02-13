package by.loiko.library.entity;

import java.util.Objects;


/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class Author extends Entity {
    
    /** The Author ID. */
    private long id;
    
    /** The Author name. */
    private String name;
    
    /** Parameter deleted user or not*/
    private boolean isDeleted;

    /**
     * Instantiates a new author.
     */
    public Author() {
    }

    /**
     * Instantiates a new author.
     *
     * @param id the id
     * @param name the name
     * @param isDeleted the is deleted
     */
    public Author(long id, String name, boolean isDeleted) {
        this.id = id;
        this.name = name;
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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return id == author.id &&
                isDeleted == author.isDeleted &&
                Objects.equals(name, author.name);
    }


    @Override
    public int hashCode() {

        return Objects.hash(id, name, isDeleted);
    }


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
