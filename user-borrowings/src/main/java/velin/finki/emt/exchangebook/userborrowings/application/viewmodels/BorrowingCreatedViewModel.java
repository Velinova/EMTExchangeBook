package velin.finki.emt.exchangebook.userborrowings.application.viewmodels;


import velin.finki.emt.exchangebook.core.valueobjects.MeetingAddress;
import velin.finki.emt.exchangebook.userborrowings.domain.enums.BorrowingStatus;
import velin.finki.emt.exchangebook.userborrowings.domain.enums.Duration;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BookId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.UserId;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowingCreatedViewModel implements Serializable {

    @NotNull
    private UserId borrower;

    @NotNull
    private UserId lender;

    @NotNull
    private BookId lentBook;

    @NotNull
    private Duration exchangeDuration;

    private String borrowerNote;
    private Date madeOnDate = Date.from(Instant.now());
    private BorrowingStatus status = BorrowingStatus.PENDING;

    @Valid
    @NotNull
    private MeetingAddressViewModel address = new MeetingAddressViewModel();


    public Duration getExchangeDuration(){
        return exchangeDuration;
    }
    public UserId getBorrower(){
        return borrower;
    }
    public UserId getLender(){
        return lender;
    }
    public BookId getLentBook(){
        return lentBook;
    }
    public MeetingAddressViewModel getMeetingAddress() {
        return address;
    }
    public Date getMadeOnDate(){
        return madeOnDate;
    }
    public String getBorrowerNote(){
        return borrowerNote;
    }
}

