package velin.finki.emt.exchangebook.userborrowings.application.viewmodels;

import velin.finki.emt.exchangebook.userborrowings.domain.enums.BorrowingStatus;
import velin.finki.emt.exchangebook.userborrowings.domain.enums.Duration;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BookId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.Borrowing;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BorrowingId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.UserId;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

public class BorrowingDoneViewModel {
    @NotNull
    private BorrowingId id;


    private Date doneOnDate = Date.from(Instant.now());

    public BorrowingId getId(){
        return id;
    }
    public Date getDoneOnDate(){
        return doneOnDate;
    }
}
