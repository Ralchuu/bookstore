package hh.bookstore.rauli.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import hh.bookstore.rauli.domain.BookRepository;
import hh.bookstore.rauli.domain.Book;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    // CREATE
    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBookSubmit(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
}
