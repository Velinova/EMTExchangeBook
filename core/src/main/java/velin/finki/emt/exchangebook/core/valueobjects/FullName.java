package velin.finki.emt.exchangebook.core.valueobjects;

import lombok.Getter;
import lombok.NonNull;
import velin.finki.emt.exchangebook.core.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Embeddable
@Getter
@MappedSuperclass
public class FullName implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final String name;

    private final String surname;

    public FullName(@NonNull String name, @NonNull String surname) {

        this.name = Objects.requireNonNull(name, "name must not be null");
        this.surname = Objects.requireNonNull(surname, "surname must not be null");
    }

    public static FullName valueOf(String name, String surname) {
        return new FullName(name, surname);
    }

    private FullName() {
        this.name = null;
        this.surname = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(name, fullName.name) &&
                Objects.equals(surname, fullName.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "FullName{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
