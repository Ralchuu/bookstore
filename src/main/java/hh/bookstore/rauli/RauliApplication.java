package hh.bookstore.rauli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.rauli.domain.Book;
import hh.bookstore.rauli.domain.BookRepository;

@SpringBootApplication
public class RauliApplication {

	public static void main(String[] args) {
		SpringApplication.run(RauliApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(BookRepository repository) {
		return args -> {
			repository.save(new Book("Hauska Kirja", "Gandalf J. Peikkonen", 1754, "987654321", 45.99));
			repository.save(new Book("Metsäretki", "Frodo K. Peltomyyrä", 2018, "123456789", 54.99));
			repository.save(new Book("Sienikirja", "Sieni Keräilijänen", 1994, "4545454545", 39.99));
		};
	}
}
