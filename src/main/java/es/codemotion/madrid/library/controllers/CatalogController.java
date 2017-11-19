package es.codemotion.madrid.library.controllers;

import es.codemotion.madrid.library.catalog.CatalogService;
import es.codemotion.madrid.library.dao.Item;
import es.codemotion.madrid.library.models.Book;
import es.codemotion.madrid.library.models.BorrowData;
import es.codemotion.madrid.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CatalogController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CatalogService catalogService;

    @RequestMapping("/catalog")
    public String catalog(ModelMap model) {
        List<Book> books = catalogService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("data", new BorrowData());
        return "catalog";
    }

    @PostMapping("/catalog/borrow")
    public String borrow(@ModelAttribute BorrowData data) {
        Item book = bookRepository.findOne(data.getId());
        book.setAvailable(false);
        bookRepository.save(book);
        return "redirect:/catalog";
    }
}
