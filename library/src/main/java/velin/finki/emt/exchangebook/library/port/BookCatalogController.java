package velin.finki.emt.exchangebook.library.port;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import velin.finki.emt.exchangebook.library.application.BookCatalog;
import velin.finki.emt.exchangebook.library.domain.model.Book;
import velin.finki.emt.exchangebook.library.domain.model.BookId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
class BookCatalogController {

    private final BookCatalog bookCatalog;

    BookCatalogController(BookCatalog bookCatalog) {
        this.bookCatalog = bookCatalog;
    }

    // Please note: in a real-world application it would be better to have separate DTO classes that are serialized
    // to JSON. However, to save time, we're using the entity classes directly in this example.

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") String bookId) {
        return bookCatalog.findById(new BookId(bookId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Book> findAll() {
        return bookCatalog.findAll();
    }

    @GetMapping("/all/{userId}")
    public List<Book> findAllByUser(@PathVariable ("userId") String userId){
        return bookCatalog.findByUserId(userId).stream().collect(Collectors.toList());
    }
}

