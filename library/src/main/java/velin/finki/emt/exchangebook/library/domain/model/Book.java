package velin.finki.emt.exchangebook.library.domain.model;

import lombok.Getter;
import velin.finki.emt.exchangebook.core.base.AbstractEntity;
import velin.finki.emt.exchangebook.core.base.DomainObjectId;
import velin.finki.emt.exchangebook.core.enums.BookStatus;
import velin.finki.emt.exchangebook.core.enums.Genre;
import velin.finki.emt.exchangebook.core.valueobjects.FullName;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "books")
public class Book extends AbstractEntity<BookId> {
    @Version
    private Long version;

    @Column(name = "title", nullable = false)
    private String title;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "surname", column = @Column(name = "surname"))
    })
    private FullName author;

    @Column(name = "plot", nullable = false)
    private String plot;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "genre", column = @Column(name = "genre"))
    })
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookStatus status;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @ManyToOne()
    private UserId userId;

    public Book() {

    }

    public Book(String title,  Genre genre, FullName author, BookStatus status, String plot) {
        super(DomainObjectId.randomId(BookId.class));
        this.title = title;
        this.plot = plot;
        this.genre = genre;
        this.author = author;
        this.status = status;
    }
    public void changeStatus(){
        if(this.status.equals(BookStatus.NOT_AVAILABLE) )
            this.status = BookStatus.AVAILABLE;
        else
            this.status = BookStatus.NOT_AVAILABLE;
    }
}
