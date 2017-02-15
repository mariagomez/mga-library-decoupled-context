package me.mscandella.mga.library.services;

import me.mscandella.mga.library.dao.Book;
import me.mscandella.mga.library.dao.Rating;
import me.mscandella.mga.library.models.BookWithRating;
import me.mscandella.mga.library.repositories.BookRepository;
import me.mscandella.mga.library.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CatalogService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RatingRepository ratingRepository;

    public List<BookWithRating> getAllBooks() {
        Iterable<Book> items = bookRepository.findAll();
        return StreamSupport.stream(items.spliterator(), false)
                    .map(book -> {
                        Rating rating = ratingRepository.findOne(book.getId());
                        return new BookWithRating(book.getId(), book.getName(), book.getAuthor(),
                                book.getDescription(), rating.getRating(),
                                book.getImagePath(), book.isAvailable());
                    })
                    .collect(Collectors.toList());
    }

    public BookWithRating borrowBook(Long id) {
        Book book = bookRepository.findOne(id);
        book.setAvailable(false);
        Book savedBook = bookRepository.save(book);
        return new BookWithRating(savedBook.getId(), savedBook.getName(), savedBook.getAuthor(), savedBook.getDescription(),
                savedBook.getRating(), savedBook.getImagePath(), savedBook.isAvailable());
    }
}
