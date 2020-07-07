package velin.finki.emt.exchangebook.userborrowings.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import velin.finki.emt.exchangebook.core.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class BookId extends DomainObjectId {

    private BookId() {
        super(DomainObjectId.randomId(BookId.class).toString());
    }

    @JsonCreator
    public BookId(String id) {
        super(id);
    }

}

