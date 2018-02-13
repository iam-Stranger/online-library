package by.loiko.library.entity;

import java.util.Objects;


/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class Genre extends Entity {
    
    /** The id. */
    private long id;
    
    /** The type. */
    private String type;
    
    /** The is deleted. */
    private boolean isDeleted;

    /**
     * Instantiates a new genre.
     */
    public Genre() {
    }

    /**
     * Instantiates a new genre.
     *
     * @param id the id
     * @param type the type
     * @param isDeleted the is deleted
     */
    public Genre(long id, String type, boolean isDeleted) {
        this.id = id;
        this.type = type;
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
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
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
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
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
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                isDeleted == genre.isDeleted &&
                Objects.equals(type, genre.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, isDeleted);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
