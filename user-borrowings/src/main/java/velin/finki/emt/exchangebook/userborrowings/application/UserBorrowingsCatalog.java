package velin.finki.emt.exchangebook.userborrowings.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velin.finki.emt.exchangebook.core.valueobjects.MeetingAddress;
import velin.finki.emt.exchangebook.userborrowings.application.viewmodels.BorrowingViewModel;
import velin.finki.emt.exchangebook.userborrowings.application.viewmodels.MeetingAddressViewModel;
import velin.finki.emt.exchangebook.userborrowings.domain.model.Borrowing;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BorrowingId;
import velin.finki.emt.exchangebook.userborrowings.domain.repository.BorrowingRepository;
import velin.finki.emt.exchangebook.userborrowings.domain.repository.UserRepository;
import velin.finki.emt.exchangebook.userborrowings.domain.event.BorrowingCreated;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
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
// borrowing created apply changes after creating borrowingForm
    public BorrowingId createBorrowing(@NonNull BorrowingViewModel borrowing) {
        Objects.requireNonNull(borrowing,"borrowing must not be null");
        var constraintViolations = validator.validate(borrowing);

        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The BorrowingForm is not valid", constraintViolations);
        }

        var newBorrowing = borrowingRepository.saveAndFlush(toDomainModel(borrowing));
        applicationEventPublisher.publishEvent(new BorrowingCreated(newBorrowing.id(), newBorrowing.getMadeOnDate().toInstant(), newBorrowing.getBorrowedBook()));
        return newBorrowing.id();
    }

    //for accessing borrowing by id
    @NonNull
    public Optional<Borrowing> findById(@NonNull BorrowingId borrowingId) {
        Objects.requireNonNull(borrowingId, "orderId must not be null");
        return borrowingRepository.findById(borrowingId);
    }

    @NonNull
    private Borrowing toDomainModel(@NonNull BorrowingViewModel borrowingViewModel) {
        var borrowing = new Borrowing(borrowingViewModel.getExchangeDuration(),
                toDomainModel(borrowingViewModel.getMeetingAddress()), borrowingViewModel.getBorrower(), borrowingViewModel.getLender(), borrowingViewModel.getMadeOnDate(), borrowingViewModel.getBorrowerNote(), borrowingViewModel.getLentBook());
        return borrowing;
    }

    @NonNull
    private MeetingAddress toDomainModel(@NonNull MeetingAddressViewModel viewModel) {
        return new MeetingAddress(viewModel.getAddress(), viewModel.getCity(),viewModel.getTime());
    }

}
