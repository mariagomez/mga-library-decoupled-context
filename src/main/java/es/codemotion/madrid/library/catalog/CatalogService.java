package es.codemotion.madrid.library.catalog;

import es.codemotion.madrid.library.models.Book;
import es.codemotion.madrid.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return new ArrayList<>();
    }
}
