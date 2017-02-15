package me.mscandella.mga.library.controllers;

import me.mscandella.mga.library.services.CatalogService;
import me.mscandella.mga.library.models.Book;
import me.mscandella.mga.library.models.BorrowData;
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
        catalogService.borrowBook(data.getId());
        return "redirect:/catalog";
    }
}

