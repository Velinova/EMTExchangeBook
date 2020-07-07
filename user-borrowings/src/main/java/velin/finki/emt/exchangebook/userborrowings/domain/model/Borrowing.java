package velin.finki.emt.exchangebook.userborrowings.domain.model;

import lombok.Getter;
import velin.finki.emt.exchangebook.core.base.AbstractEntity;
import velin.finki.emt.exchangebook.core.valueobjects.Address;
import velin.finki.emt.exchangebook.core.valueobjects.FullName;
import velin.finki.emt.exchangebook.core.valueobjects.MeetingAddress;
import velin.finki.emt.exchangebook.userborrowings.domain.enums.BorrowingStatus;
import velin.finki.emt.exchangebook.userborrowings.domain.enums.Duration;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Table(name = "borrowings")
public class Borrowing extends AbstractEntity<BorrowingId> {
    @Version
    private Long version;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="borrower_id",nullable = false))
    private UserId borrower;


    @Column(name = "lender_id", nullable = false)
    private UserId lender;

    @Column(name = "borrowed_book_id", nullable = true)
    private BookId borrowedBook;

    @Column(name = "lent_book_id", nullable = false)
    private BookId lentBook;

    @Enumerated(EnumType.STRING)
    @Column(name = "duration", nullable = false)
    private Duration exchangeDuration;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BorrowingStatus status = BorrowingStatus.PENDING;

    @Column(name = "borrower_note", nullable = true)
    private String borrowerNote;

    @Column(name = "lender_note", nullable = true)
    private String lenderNote;

    @Column(name = "made_date", nullable = true)
    private Date madeOnDate;

    @Column(name = "done_date", nullable = true)
    private Date doneOnDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "address")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "time", column = @Column(name = "time"))
    })
    private MeetingAddress address;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public Borrowing() {

    }

    public Borrowing(Duration exchangeDuration, MeetingAddress meetingAddress, UserId borrower, UserId lender, Date madeOnDate, String borrowerNote, BookId lendedBook) {
        this.borrowerNote = borrowerNote;
        this.exchangeDuration = exchangeDuration;
        this.address = meetingAddress;
        this.borrower = borrower;
        this.lender = lender;
        this.lentBook = lendedBook;
        this.madeOnDate = madeOnDate;
        this.doneOnDate = calculateDoneOnDate(madeOnDate, exchangeDuration);
    }


    //method which calculates borrowing's duration from date that it's been made on
    private Date calculateDoneOnDate(Date madeOnDate, Duration exchangeDuration){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(madeOnDate);
        switch(exchangeDuration){
            case ONE_WEEK:
                calendar.add(Calendar.DATE, 7);
                break;
            case TWO_WEEKS:
                calendar.add(Calendar.DATE, 14);
                break;
            case THREE_WEEKS:
                calendar.add(Calendar.DATE, 21);
                break;
            case FOUR_WEEKS:
                calendar.add(Calendar.DATE, 28);
                break;
        }
        return calendar.getTime();
    }

}
