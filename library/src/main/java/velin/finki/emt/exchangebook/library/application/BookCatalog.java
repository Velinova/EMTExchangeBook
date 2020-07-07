package velin.finki.emt.exchangebook.library.application;


import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import velin.finki.emt.exchangebook.library.domain.model.Book;
import velin.finki.emt.exchangebook.library.domain.model.BookId;
import velin.finki.emt.exchangebook.library.domain.repository.BookRepository;

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


//    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
//    public void onBorrowingCreatedEvent(BorrowingCreatedEvent event) {
//        Book b = bookRepository.findById(event.getBookId()).orElseThrow(RuntimeException::new);
//        b.changeStatus();
//        bookRepository.save(b);
//    }

}
