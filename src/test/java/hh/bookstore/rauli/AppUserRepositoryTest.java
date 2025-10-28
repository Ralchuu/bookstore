package hh.bookstore.rauli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.bookstore.rauli.domain.AppUser;
import hh.bookstore.rauli.domain.AppUserRepository;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository repository;

    @Test
    public void createNewUser() {
        AppUser user = new AppUser("testuser", "passhash", "USER");
        repository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void deleteUserShouldRemoveIt() {
        AppUser user = new AppUser("deleteuser", "passhash", "USER");
        repository.save(user);
        Long id = user.getId();
        repository.delete(user);
        assertThat(repository.findById(id)).isEmpty();
    }

    @Test
    public void findByUsernameShouldReturnUser() {
        AppUser user = new AppUser("findme", "passhash", "USER");
        repository.save(user);
        AppUser found = repository.findByUsername("findme");
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("findme");
    }
}
