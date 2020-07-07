package velin.finki.emt.exchangebook.userborrowings.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import velin.finki.emt.exchangebook.core.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class BorrowingId extends DomainObjectId {

    private BorrowingId() {
        super(DomainObjectId.randomId(BorrowingId.class).toString());
    }

    @JsonCreator
    public BorrowingId(String id) {
        super(id);
    }

}

