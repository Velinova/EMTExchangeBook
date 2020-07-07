package velin.finki.emt.exchangebook.userborrowings.domain.model;

import lombok.Getter;
import velin.finki.emt.exchangebook.core.enums.BookStatus;
import velin.finki.emt.exchangebook.core.enums.Genre;

@Getter
public class Book {
    private BookId idd;
    private String title;
    private String plot;
    private BookStatus status;
    private Genre genre;
}
