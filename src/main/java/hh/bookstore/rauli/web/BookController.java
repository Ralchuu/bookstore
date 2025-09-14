package hh.bookstore.rauli.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/index")
    public String index(Model model) {
        return "index"; // hakee templates/index.html
    }
}

