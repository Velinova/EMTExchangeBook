package velin.finki.emt.exchangebook.userborrowings.application.viewmodels;

import velin.finki.emt.exchangebook.userborrowings.domain.model.Book;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BookId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BorrowingId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.UserId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BorrowingAcceptedViewModel implements Serializable {

    @NotNull
    private BorrowingId id;

    @NotNull
    private UserId borrower;

    @NotNull
    private UserId lender;

    @NotNull
    private BookId borrowedBook;

    @NotNull
    private BookId lentBook;

    private String lenderNote;

    public BorrowingId getId(){return id;}
    public BookId getBorrowedBook(){
        return borrowedBook;
    }
    public BookId getLentBook() { return lentBook;}
    public UserId getBorrower() {return borrower;}
    public UserId getLender() {return lender;}

    public String getLenderNote(){
        return lenderNote;
    }
}

