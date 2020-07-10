package velin.finki.emt.exchangebook.userborrowings.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velin.finki.emt.exchangebook.core.enums.BookStatus;
import velin.finki.emt.exchangebook.core.valueobjects.MeetingAddress;
import velin.finki.emt.exchangebook.userborrowings.application.viewmodels.*;
import velin.finki.emt.exchangebook.userborrowings.domain.enums.BorrowingStatus;
import velin.finki.emt.exchangebook.userborrowings.domain.event.BookAdded;
import velin.finki.emt.exchangebook.userborrowings.domain.event.BookDeleted;
import velin.finki.emt.exchangebook.userborrowings.domain.event.BorrowingAccepted;
import velin.finki.emt.exchangebook.userborrowings.domain.event.BorrowingDone;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BookId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.Borrowing;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BorrowingId;
import velin.finki.emt.exchangebook.userborrowings.domain.repository.BorrowingRepository;
import velin.finki.emt.exchangebook.userborrowings.domain.repository.UserRepository;

import javax.management.InvalidAttributeValueException;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class UserBorrowingsCatalog {
    private final UserRepository userRepository;

    private final BorrowingRepository borrowingRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Validator validator;

    private BookCatalog bookCatalog;

    public UserBorrowingsCatalog(UserRepository userRepository,
                        BorrowingRepository borrowingRepository,
                        BookCatalog bookCatalog,
                        Validator validator,
                        ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.borrowingRepository = borrowingRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validator = validator;
        this.bookCatalog = bookCatalog;
    }


    //BOOK ADDED (published event)
    public void addBook(@NotNull BookAddedViewModel book){
        Objects.requireNonNull(book,"book must not be null");
        var constraintViolations = validator.validate(book);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The BookViewModel is not valid", constraintViolations);
        }
        //publish event
        applicationEventPublisher.publishEvent(new BookAdded(book.getTitle(), book.getPlot(), book.getAuthor(), book.getGenre(), book.getUserId(), Instant.now()));
        return;
    }

    //BOOK DELETED (published event)
    public void deleteBook(@NotNull BookId id){

        //publish event
        applicationEventPublisher.publishEvent(new BookDeleted(id,  Instant.now()));
        return;
    }


    //BORROWING CREATED (not published event)
    public BorrowingId createBorrowing(@NonNull BorrowingCreatedViewModel borrowing) throws InvalidAttributeValueException {
        //check validator constraints
        Objects.requireNonNull(borrowing,"borrowing must not be null");
        var constraintViolations = validator.validate(borrowing);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The BorrowingViewModel is not valid", constraintViolations);
        }
        //check if borrowed book is available
        if(bookCatalog.findById(toDomainModel(borrowing).getBorrowedBook()).equals(BookStatus.NOT_AVAILABLE)){
            throw new InvalidAttributeValueException("The borrowed book is not available for lending at this moment.");
        }

        //save object
        var newBorrowing = borrowingRepository.saveAndFlush(toDomainModel(borrowing));
        return newBorrowing.id();
    }

    //BORROWING DECLINED (not published event)
    public BorrowingId declinedBorrowing(BorrowingId id) throws InvalidAttributeValueException {
        Borrowing borrowingForUpdate = findById(id).get();
        borrowingForUpdate.setStatus(BorrowingStatus.DECLINED_BY_USER);

        //save object
        var newBorrowing = borrowingRepository.saveAndFlush(borrowingForUpdate);
        return newBorrowing.id();
    }


    //BORROWING ACCEPTED (published event)
    public BorrowingId acceptedBorrowing(@NonNull BorrowingAcceptedViewModel borrowing) throws InvalidAttributeValueException {
        //check validator constraints
        Objects.requireNonNull(borrowing,"borrowing must not be null");
        var constraintViolations = validator.validate(borrowing);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The BorrowingViewModel is not valid", constraintViolations);
        }
        //check if lent book is available
        if(bookCatalog.findById(borrowing.getLentBook()).equals(BookStatus.NOT_AVAILABLE)){
            throw new InvalidAttributeValueException("The lent book is not available for lending at this moment.");
        }

        //update attributes of the pending borrowing
        Borrowing borrowingForUpdate = findById(borrowing.getId()).get();
        borrowingForUpdate.setLenderNote(borrowing.getLenderNote());
        borrowingForUpdate.setLentBook(borrowing.getLentBook());
        borrowingForUpdate.setStatus(BorrowingStatus.ACCEPTED);

        //update object and publish event
        var acceptedBorrowing = borrowingRepository.saveAndFlush(borrowingForUpdate);
        applicationEventPublisher.publishEvent(new BorrowingAccepted(acceptedBorrowing.id(), Instant.now(), acceptedBorrowing.getBorrowedBook(), acceptedBorrowing.getLentBook()));
        return acceptedBorrowing.id();
    }

    //BORROWING DONE (published event)
    public BorrowingId doneBorrowing(@NonNull BorrowingDoneViewModel borrowing) throws InvalidAttributeValueException {
        //update attributes of the completed borrowing
        Borrowing borrowingForUpdate = findById(borrowing.getId()).get();
        borrowingForUpdate.setStatus(BorrowingStatus.COMPLETED);
        borrowingForUpdate.setDoneOnDate(borrowing.getDoneOnDate());

        //update object and publish event
        var completedBorrowing = borrowingRepository.saveAndFlush(borrowingForUpdate);
        applicationEventPublisher.publishEvent(new BorrowingDone(completedBorrowing.id(), Instant.now(), completedBorrowing.getBorrowedBook(), completedBorrowing.getLentBook()));
        return completedBorrowing.id();
    }

    //for accessing borrowing by id
    @NonNull
    public Optional<Borrowing> findById(@NonNull BorrowingId borrowingId) {
        Objects.requireNonNull(borrowingId, "orderId must not be null");
        return borrowingRepository.findById(borrowingId);
    }

    @NonNull
    private Borrowing toDomainModel(@NonNull BorrowingCreatedViewModel borrowingViewModel) {
        var borrowing = new Borrowing(borrowingViewModel.getExchangeDuration(),
                toDomainModel(borrowingViewModel.getMeetingAddress()), borrowingViewModel.getBorrower(), borrowingViewModel.getLender(), borrowingViewModel.getMadeOnDate(), borrowingViewModel.getBorrowerNote(), borrowingViewModel.getLentBook());
        return borrowing;
    }

    @NonNull
    private MeetingAddress toDomainModel(@NonNull MeetingAddressViewModel viewModel) {
        return new MeetingAddress(viewModel.getAddress(), viewModel.getCity(),viewModel.getTime());
    }

}
