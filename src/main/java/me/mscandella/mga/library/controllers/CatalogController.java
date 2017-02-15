package me.mscandella.mga.library.controllers;

import me.mscandella.mga.library.aggregates.Catalog;
import me.mscandella.mga.library.dao.Item;
import me.mscandella.mga.library.models.Book;
import me.mscandella.mga.library.models.BorrowData;
import me.mscandella.mga.library.repositories.BookRepository;
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
    private Catalog catalog;

    @RequestMapping("/catalog")
    public String catalog(ModelMap model) {
        List<Book> books = catalog.getAllBooks();
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

