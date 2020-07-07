package velin.finki.emt.exchangebook.library.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velin.finki.emt.exchangebook.library.domain.model.Book;
import velin.finki.emt.exchangebook.library.domain.model.BookId;

public interface BookRepository extends JpaRepository<Book, BookId> {
}
