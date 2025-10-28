package hh.bookstore.rauli;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.bookstore.rauli.domain.Category;
import hh.bookstore.rauli.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void createNewCategory() {
        Category cat = new Category("New Category");
        repository.save(cat);
        assertThat(cat.getCategoryid()).isNotNull();
    }

    @Test
    public void deleteCategoryShouldRemoveIt() {
        Category cat = new Category("To Delete");
        repository.save(cat);
        Long id = cat.getCategoryid();

        repository.delete(cat);

        assertThat(repository.findById(id)).isEmpty();
    }

    @Test
    public void findCategoryByName() {
        Category cat = new Category("Unique Category");
        repository.save(cat);

        List<Category> categories = new ArrayList<>();
        repository.findAll().forEach(categories::add);

        Category found = categories.stream()
                .filter(c -> "Unique Category".equals(c.getName()))
                .findFirst()
                .orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Unique Category");
    }
}
