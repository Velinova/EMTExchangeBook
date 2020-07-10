package velin.finki.emt.exchangebook.userborrowings.application.viewmodels;

import velin.finki.emt.exchangebook.core.enums.BookStatus;
import velin.finki.emt.exchangebook.core.valueobjects.FullName;
import velin.finki.emt.exchangebook.userborrowings.domain.model.UserId;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookAddedViewModel {

    @NotNull
    private UserId userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String plot;

    private BookStatus status = BookStatus.AVAILABLE;

    @Valid
    @NotEmpty
    private FullNameViewModel author = new FullNameViewModel();

    @Valid
    @NotEmpty
    private GenreViewModel genre = new GenreViewModel();

    public String getPlot() {
        return plot;
    }

    public BookStatus getStatus() {
        return status;
    }

    public FullNameViewModel getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public UserId getUserId() {
        return userId;
    }

    public GenreViewModel getGenre() {
        return genre;
    }
}
