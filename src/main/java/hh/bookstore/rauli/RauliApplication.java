package hh.bookstore.rauli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.rauli.domain.Book;
import hh.bookstore.rauli.domain.BookRepository;
import hh.bookstore.rauli.domain.Category;
import hh.bookstore.rauli.domain.CategoryRepository;
import hh.bookstore.rauli.domain.AppUser;
import hh.bookstore.rauli.domain.AppUserRepository;

@SpringBootApplication
public class RauliApplication {

    private static final Logger log = LoggerFactory.getLogger(RauliApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RauliApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(BookRepository bookRepo, 
                                          CategoryRepository categoryRepo, 
                                          AppUserRepository appUserRepo) {
        return (args) -> {
            log.info("save a couple of categories");
            Category fantasy = categoryRepo.save(new Category("Fantasia"));
            Category scifi = categoryRepo.save(new Category("Scifi"));
            Category biography = categoryRepo.save(new Category("Elämäkerta"));

            log.info("save a couple of books");
            bookRepo.save(new Book("Hauska Kirja", "Gandalf J. Peikkonen", 1754, "987654321", 45.99, fantasy));
            bookRepo.save(new Book("Metsäretki", "Frodo K. Peltomyyrä", 2018, "123456789", 54.99, scifi));
            bookRepo.save(new Book("Sienikirja", "Sieni Keräilijänen", 1994, "4545454545", 39.99, biography));

            log.info("create users: admin/admin99999 and user/user99999 (passwords are BCrypt-hashed)");

            
            // user -> salasana "user99999"
            // admin -> salasana "admin99999"
            AppUser user1 = new AppUser("user", "$2b$12$c1sG.SuXTSWJfIMy2LpTvu4wyJjwHQA0t19eq2cevuE.PLpy9Lpd6", "USER");
            AppUser user2 = new AppUser("admin", "$2b$12$E7S8KGW90DPeuwrmh9cqB.1OXmX07LH1B0nBNcXtQY7xbklx4NhWK", "ADMIN");
            appUserRepo.save(user1);
            appUserRepo.save(user2);

            log.info("fetch all books");
            for (Book book : bookRepo.findAll()) {
                log.info(book.toString());
            }

            log.info("seeded users:");
            appUserRepo.findAll().forEach(u -> log.info("User: " + u.getUsername() + ", role: " + u.getRole()));
        };
    }
}
