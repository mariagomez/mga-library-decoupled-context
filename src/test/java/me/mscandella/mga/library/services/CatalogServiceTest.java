package me.mscandella.mga.library.services;

import me.mscandella.mga.library.dao.Book;
import me.mscandella.mga.library.models.BookWithRating;
import me.mscandella.mga.library.rating.dao.Rating;
import me.mscandella.mga.library.rating.repositories.RatingRepository;
import me.mscandella.mga.library.repositories.BookRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogServiceTest {

    @Autowired
    private CatalogService catalogService;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private RatingRepository ratingRepository;
    @MockBean
    private RestTemplate restTemplate;
    private Book book;
    private Rating rating;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        book = new Book("Name", "Author", "Description", 1,
                true, "path");
        rating = new Rating(3);
    }

    @Test
    public void shouldReturnAllBooksWithRating() throws Exception {
        Iterable<Book> items = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(items);
        when(ratingRepository.findOne(anyLong())).thenReturn(rating);
        List<BookWithRating> books = catalogService.getAllBooks();

        assertThat(books, is(notNullValue()));
    }

    @Test
    @Ignore
    public void shouldReturnValidValuesForTheBook() throws Exception {
//        when(ratingRepository.findOne(anyLong())).thenReturn(rating);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(rating);
        BookWithRating expectedBook = new BookWithRating(book.getId(), book.getName(), book.getAuthor(),
                book.getDescription(), rating.getRating(), book.getImagePath(), book.isAvailable());
        Iterable<Book> items = Arrays.asList(book);
        when(bookRepository.findAll()).thenReturn(items);
        List<BookWithRating> books = catalogService.getAllBooks();

        assertThat(books, is(notNullValue()));
        assertThat(books.get(0), samePropertyValuesAs(expectedBook));
    }

    @Test
    public void shouldModifyAvailabilityOfBooks() throws Exception {
        long bookId = 1L;
        when(bookRepository.findOne(bookId)).thenReturn(book);
        when(ratingRepository.findOne(anyLong())).thenReturn(rating);
        Book borrowedBook = new Book(book.getName(), book.getAuthor(), book.getDescription(),
                rating.getRating(), false, book.getImagePath());
        when(bookRepository.save(any(Book.class))).thenReturn(borrowedBook);
        BookWithRating book = catalogService.borrowBook(bookId);
        assertThat(book.isAvailable(), is(false));
    }
}