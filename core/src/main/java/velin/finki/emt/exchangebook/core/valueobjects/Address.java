package velin.finki.emt.exchangebook.core.valueobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import velin.finki.emt.exchangebook.core.base.ValueObject;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@MappedSuperclass
public class Address implements ValueObject {

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    @Embedded
    private CityName city;


    @SuppressWarnings("unused") // Used by JPA only.
    protected Address() {
    }

    public Address(@NonNull String address, @NonNull CityName city) {
        this.address = Objects.requireNonNull(address, "address must not be null");
        this.city = Objects.requireNonNull(city, "city must not be null");
    }

    @NonNull
    @JsonProperty("address")
    public String address() {
        return address;
    }

    @NonNull
    @JsonProperty("city")
    public CityName city() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) &&
                Objects.equals(city, address1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", city=" + city +
                '}';
    }
}
