package by.loiko.library.entity;

import java.util.Objects;

/***
 Author: Aliaksei Loika
 Date: 21.12.2017
 ***/
public class Author extends Entity {
    private long id;
    private String name;
    private boolean isDeleted;

    public Author() {
    }

    public Author(long id, String name, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

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
