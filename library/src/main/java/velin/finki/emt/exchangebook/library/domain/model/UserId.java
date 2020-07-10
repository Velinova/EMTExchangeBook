package velin.finki.emt.exchangebook.library.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import velin.finki.emt.exchangebook.core.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {

    private UserId() {
        super(DomainObjectId.randomId(UserId.class).toString());
    }

    @JsonCreator
    public UserId(String id) {
        super(id);
    }

}

