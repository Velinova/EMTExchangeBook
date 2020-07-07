package velin.finki.emt.exchangebook.userborrowings.domain.model;

import lombok.Getter;
import velin.finki.emt.exchangebook.core.base.AbstractEntity;
import velin.finki.emt.exchangebook.core.valueobjects.Address;
import velin.finki.emt.exchangebook.core.valueobjects.FullName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "users")
public class User extends AbstractEntity<UserId> {
    @Version
    private Long version;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "surname", column = @Column(name = "surname"))
    })
    private FullName fullName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "address")),
            @AttributeOverride(name = "city", column = @Column(name = "city"))
    })
    private Address address;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Book> books;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Borrowing> borrowings;

    public User() {

    }

    public User(UserId userId, String username, String password, FullName fullName, String phoneNumber, String email, Address address) {
        super(userId);
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

}
