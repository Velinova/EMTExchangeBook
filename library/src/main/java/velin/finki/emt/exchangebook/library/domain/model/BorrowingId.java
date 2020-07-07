package velin.finki.emt.exchangebook.library.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import velin.finki.emt.exchangebook.core.base.DomainObjectId;

public class BorrowingId extends DomainObjectId {

    private BorrowingId() {
        super(DomainObjectId.randomId(BorrowingId.class).toString());
    }

    @JsonCreator
    public BorrowingId(String id) {
        super(id);
    }

}
