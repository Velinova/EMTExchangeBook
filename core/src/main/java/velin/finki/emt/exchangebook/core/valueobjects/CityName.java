package velin.finki.emt.exchangebook.core.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;
import velin.finki.emt.exchangebook.core.base.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CityName implements ValueObject {

    @Column(name="city_name")
    private final String name;

    //unused
    private CityName() {this.name="";}

    @JsonCreator
    public CityName(@NonNull String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityName cityName = (CityName) o;
        return Objects.equals(name, cityName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
    }
}
