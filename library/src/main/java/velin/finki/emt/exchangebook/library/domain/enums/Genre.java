package velin.finki.emt.exchangebook.library.domain.enums;

import lombok.NonNull;
import velin.finki.emt.exchangebook.core.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Embeddable
@MappedSuperclass
public class Genre implements ValueObject {

    private String genre;

    public Genre(@NonNull String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre1 = (Genre) o;
        return Objects.equals(genre, genre1.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre='" + genre + '\'' +
                '}';
    }
}
