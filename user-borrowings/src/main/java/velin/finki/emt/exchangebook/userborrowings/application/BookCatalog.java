package velin.finki.emt.exchangebook.userborrowings.application;


import org.springframework.stereotype.Service;
import velin.finki.emt.exchangebook.userborrowings.domain.model.Book;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BookId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.UserId;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface BookCatalog {
    List<Book> findAll();

    Book findById(BookId id);
    List<Book> findByUserId(UserId id);

}
