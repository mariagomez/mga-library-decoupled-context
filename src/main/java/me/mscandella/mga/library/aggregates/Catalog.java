package me.mscandella.mga.library.aggregates;

import me.mscandella.mga.library.dao.Item;
import me.mscandella.mga.library.models.Book;
import me.mscandella.mga.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Catalog {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        Iterable<Item> items = bookRepository.findAll();
        return StreamSupport.stream(items.spliterator(), false)
                    .map(item -> new Book(item.getId(), item.getName(), item.getAuthor(), item.getDescription(), item.getRating(),
                            item.getImagePath(), item.isAvailable()))
                    .collect(Collectors.toList());
    }
}
