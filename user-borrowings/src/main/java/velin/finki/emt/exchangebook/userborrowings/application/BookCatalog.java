package velin.finki.emt.exchangebook.userborrowings.application;


import org.springframework.stereotype.Service;
import velin.finki.emt.exchangebook.userborrowings.domain.model.Book;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BookId;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public interface BookCatalog {
    List<Book> findAll();

    Book findById(BookId id);

}
