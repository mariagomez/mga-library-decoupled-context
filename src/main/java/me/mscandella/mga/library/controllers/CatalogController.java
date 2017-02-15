package me.mscandella.mga.library.controllers;

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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class CatalogController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/catalog")
    public String catalog(ModelMap model) {
        Iterable<Item> items = bookRepository.findAll();
        List<Book> books = getAllBooks(items);
        model.addAttribute("books", books);
        model.addAttribute("data", new BorrowData());
        return "catalog";
    }

    private List<Book> getAllBooks(Iterable<Item> items) {
        return StreamSupport.stream(items.spliterator(), false)
                    .map(item -> new Book(item.getId(), item.getName(), item.getAuthor(), item.getDescription(), item.getRating(),
                            item.getImagePath(), item.isAvailable()))
                    .collect(Collectors.toList());
    }

    @PostMapping("/catalog/borrow")
    public String borrow(@ModelAttribute BorrowData data) {
        Item book = bookRepository.findOne(data.getId());
        book.setAvailable(false);
        bookRepository.save(book);
        return "redirect:/catalog";
    }
}
