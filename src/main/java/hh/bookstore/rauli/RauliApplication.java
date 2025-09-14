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
			repository.save(new Book("Clean Code", "Robert C. Martin", 2008, "9780132350884", 45.99));
			repository.save(new Book("Effective Java", "Joshua Bloch", 2018, "9780134685991", 54.99));
			repository.save(new Book("Design Patterns", "Erich Gamma", 1994, "9780201633610", 39.99));
		};
	}
}
