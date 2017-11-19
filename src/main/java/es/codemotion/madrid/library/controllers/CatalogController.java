package es.codemotion.madrid.library.controllers;

import es.codemotion.madrid.library.borrow.BorrowService;
import es.codemotion.madrid.library.catalog.CatalogService;
import es.codemotion.madrid.library.dao.Item;
import es.codemotion.madrid.library.models.Book;
import es.codemotion.madrid.library.models.BookWithAvailability;
import es.codemotion.madrid.library.models.BorrowData;
import es.codemotion.madrid.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
public class CatalogController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private BorrowService borrowService;

    @RequestMapping("/catalog")
    public String catalog(ModelMap model) {
        List<Book> books = catalogService.getAllBooks();
        List<BookWithAvailability> booksWithAvailability = addAvailability(books);
        model.addAttribute("books", booksWithAvailability);
        model.addAttribute("data", new BorrowData());
        return "catalog";
    }

    private List<BookWithAvailability> addAvailability(List<Book> books) {
        List<BookWithAvailability> booksWithAvailability = StreamSupport.stream(books.spliterator(), false)
                .map(book -> new BookWithAvailability(book.getId(), book.getName(), book.getAuthor(), book.getDescription(), book.getRating(), book.getImagePath(), book.isAvailable()))
                .collect(Collectors.toList());
        return booksWithAvailability;
    }

    @PostMapping("/catalog/borrow")
    public String borrow(@ModelAttribute BorrowData data) {
        Item book = bookRepository.findOne(data.getId());
        book.setAvailable(false);
        bookRepository.save(book);
        return "redirect:/catalog";
    }
}
