package hh.bookstore.rauli.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import hh.bookstore.rauli.domain.BookRepository;
import hh.bookstore.rauli.domain.Book;
import hh.bookstore.rauli.domain.Category;
import hh.bookstore.rauli.domain.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBookSubmit(@ModelAttribute Book book, @RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        book.setCategory(category);
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "redirect:/booklist";
        }
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book, @RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        book.setCategory(category);
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
}
