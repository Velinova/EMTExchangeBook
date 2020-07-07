package velin.finki.emt.exchangebook.userborrowings.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velin.finki.emt.exchangebook.userborrowings.domain.model.Borrowing;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BorrowingId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.User;
import velin.finki.emt.exchangebook.userborrowings.domain.model.UserId;

public interface BorrowingRepository extends JpaRepository<Borrowing, BorrowingId> {
}
