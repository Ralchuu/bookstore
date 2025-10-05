package hh.bookstore.rauli.web;

import hh.bookstore.rauli.domain.Book;
import hh.bookstore.rauli.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long id) {
        return bookRepository.findById(id);
    }
}
