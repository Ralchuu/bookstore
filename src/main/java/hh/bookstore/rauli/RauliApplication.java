package hh.bookstore.rauli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.rauli.domain.Book;
import hh.bookstore.rauli.domain.BookRepository;
import hh.bookstore.rauli.domain.Category;
import hh.bookstore.rauli.domain.CategoryRepository;

@SpringBootApplication
public class RauliApplication {

    public static void main(String[] args) {
        SpringApplication.run(RauliApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepo, CategoryRepository categoryRepo) {
        return args -> {
            
            Category fantasy = categoryRepo.save(new Category("Fantasia"));
            Category scifi = categoryRepo.save(new Category("Scifi"));
            Category biography = categoryRepo.save(new Category("Elämäkerta"));

            System.out.println("= Categories =");
            categoryRepo.findAll().forEach(cat -> System.out.println("Category: " + cat));

            Book b1 = bookRepo.save(new Book("Hauska Kirja", "Gandalf J. Peikkonen", 1754, "987654321", 45.99, fantasy));
            Book b2 = bookRepo.save(new Book("Metsäretki", "Frodo K. Peltomyyrä", 2018, "123456789", 54.99, scifi));
            Book b3 = bookRepo.save(new Book("Sienikirja", "Sieni Keräilijänen", 1994, "4545454545", 39.99, biography));

            System.out.println("= Books =");
            bookRepo.findAll().forEach(book -> System.out.println("Book: " + book));
        };
    }
}
