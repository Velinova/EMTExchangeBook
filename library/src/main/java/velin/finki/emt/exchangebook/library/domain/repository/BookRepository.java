package velin.finki.emt.exchangebook.library.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velin.finki.emt.exchangebook.library.domain.model.Book;
import velin.finki.emt.exchangebook.library.domain.model.BookId;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, BookId> {
    Optional<Book> findByUserId(String userId);
}
