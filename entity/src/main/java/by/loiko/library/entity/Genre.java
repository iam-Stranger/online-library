package by.loiko.library.entity;

import java.util.Objects;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class Genre extends Entity {
    private long id;
    private String type;
    private boolean isDeleted;

    public Genre() {
    }

    public Genre(long id, String type, boolean isDeleted) {
        this.id = id;
        this.type = type;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

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
