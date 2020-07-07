package velin.finki.emt.exchangebook.userborrowings.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velin.finki.emt.exchangebook.userborrowings.domain.model.User;
import velin.finki.emt.exchangebook.userborrowings.domain.model.UserId;

interface UserRepository extends JpaRepository<User, UserId> {
}
