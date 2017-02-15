package me.mscandella.mga.library.services;

import me.mscandella.mga.library.dao.Item;
import me.mscandella.mga.library.models.Book;
import me.mscandella.mga.library.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogServiceTest {

    @Autowired
    private CatalogService catalogService;
    @MockBean
    private BookRepository bookRepository;
    private Item item;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        item = new Item("Name", "Author", "Description", 1,
                true, "path");
    }

    @Test
    public void shouldReturnAllBooks() throws Exception {
        Iterable<Item> items = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(items);
        List<Book> books = catalogService.getAllBooks();

        assertThat(books, is(notNullValue()));
    }

    @Test
    public void shouldReturnValidValuesForTheBook() throws Exception {
        Book expectedBook = new Book(item.getId(), item.getName(), item.getAuthor(),
                item.getDescription(), item.getRating(), item.getImagePath(), item.isAvailable());
        Iterable<Item> items = Arrays.asList(item);
        when(bookRepository.findAll()).thenReturn(items);
        List<Book> books = catalogService.getAllBooks();

        assertThat(books, is(notNullValue()));
        assertThat(books.get(0), samePropertyValuesAs(expectedBook));
    }

    @Test
    public void shouldModifyAvailabilityOfBooks() throws Exception {
        long bookId = 1L;
        when(bookRepository.findOne(bookId)).thenReturn(item);
        Item borrowedItem = new Item(item.getName(), item.getAuthor(), item.getDescription(),
                item.getRating(), false, item.getImagePath());
        when(bookRepository.save(any(Item.class))).thenReturn(borrowedItem);
        Book book = catalogService.borrowBook(bookId);
        assertThat(book.isAvailable(), is(false));
    }
}