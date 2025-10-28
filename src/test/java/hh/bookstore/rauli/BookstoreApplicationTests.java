package hh.bookstore.rauli;

import static org.assertj.core.api.Assertions.assertThat;

import hh.bookstore.rauli.web.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookstoreApplicationTests {

    @Autowired private BookController bookController;
    @Autowired private BookRestController bookRestController;
    @Autowired private CategoryController categoryController;
    @Autowired private AppUserController appUserController;

    @Test
    public void contextLoads() {
        assertThat(bookController).isNotNull();
        assertThat(bookRestController).isNotNull();
        assertThat(categoryController).isNotNull();
        assertThat(appUserController).isNotNull();
    }
}