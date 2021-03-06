package velin.finki.emt.exchangebook.library.application;


import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import velin.finki.emt.exchangebook.core.enums.BookStatus;
import velin.finki.emt.exchangebook.core.enums.Genre;
import velin.finki.emt.exchangebook.core.valueobjects.FullName;
import velin.finki.emt.exchangebook.library.domain.model.Book;
import velin.finki.emt.exchangebook.library.domain.model.BookId;
import velin.finki.emt.exchangebook.library.domain.repository.BookRepository;
import velin.finki.emt.exchangebook.library.integration.BookAddedEvent;
import velin.finki.emt.exchangebook.library.integration.BookDeletedEvent;
import velin.finki.emt.exchangebook.library.integration.BorrowingAcceptedEvent;
import velin.finki.emt.exchangebook.library.integration.BorrowingDoneEvent;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BookCatalog {
    private final BookRepository bookRepository;

    public BookCatalog(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @NonNull
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @NonNull
    public Optional<Book> findById(@NonNull BookId productId) {
        Objects.requireNonNull(productId, "productId must not be null");
        return bookRepository.findById(productId);
    }

    @NonNull
    public Optional<Book> findByUserId(@NonNull String userId) {
        Objects.requireNonNull(userId, "userId must not be null");
        return bookRepository.findByUserId(userId);
    }

    //handling book added event
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBookAddedEvent(BookAddedEvent event) {
        Book book = new Book(event.getTitle(), new Genre(event.getGenre().getName()), new FullName(event.getAuthor().getName(), event.getAuthor().getSurname()), BookStatus.AVAILABLE, event.getPlot());
        bookRepository.saveAndFlush(book);
    }
    //handling book added event
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBookAddedEvent(BookDeletedEvent event) {
        Book book = bookRepository.findById(event.getId()).get();
        bookRepository.delete(book);
    }

    //handling borrowing accepted event, changing status to NOT_AVAILABLE
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBorrowingAcceptedEvent(BorrowingAcceptedEvent event) {
        Book borrowedBook = bookRepository.findById(event.getBorrowedBookId()).orElseThrow(RuntimeException::new);
        borrowedBook.changeStatus();
        bookRepository.save(borrowedBook);
        Book lentBook = bookRepository.findById(event.getLentBookId()).orElseThrow(RuntimeException::new);
        lentBook.changeStatus();
        bookRepository.save(lentBook);
    }

    //handling borrowing done event, changing status to AVAILABLE
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBorrowingDoneEvent(BorrowingDoneEvent event) {
        Book borrowedBook = bookRepository.findById(event.getBorrowedBookId()).orElseThrow(RuntimeException::new);
        borrowedBook.changeStatus();
        bookRepository.save(borrowedBook);
        Book lentBook = bookRepository.findById(event.getLentBookId()).orElseThrow(RuntimeException::new);
        lentBook.changeStatus();
        bookRepository.save(lentBook);
    }
}
