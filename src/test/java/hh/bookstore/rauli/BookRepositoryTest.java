package hh.bookstore.rauli;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.bookstore.rauli.domain.Book;
import hh.bookstore.rauli.domain.BookRepository;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void createNewBook() {
        Book book = new Book("Test Book", "Author", 1849 , "1234567890", 9.99, null);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBookShouldRemoveIt() {
        Book book = new Book("To Delete", "Author", 2021, "9877898798", 19.99, null);
        repository.save(book);
        Long id = book.getId();

        repository.delete(book);

        assertThat(repository.findById(id)).isEmpty();
    }

    @Test
    public void findBookByTitle() {
        Book book = new Book("Unique Title", "Author", 2025, "17687655", 29.99, null);
        repository.save(book);

        List<Book> books = new ArrayList<>();
        repository.findAll().forEach(books::add);

        Book found = books.stream()
                .filter(b -> "Unique Title".equals(b.getTitle()))
                .findFirst()
                .orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Unique Title");
    }
}
