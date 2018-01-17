package by.loiko.library.entity;

import java.util.Objects;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class Genre extends Entity {
    private long id;
    private String type;

    public Genre() {
    }

    public Genre(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                Objects.equals(type, genre.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
