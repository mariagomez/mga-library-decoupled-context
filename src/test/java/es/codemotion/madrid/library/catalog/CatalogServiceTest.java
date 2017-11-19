package es.codemotion.madrid.library.catalog;

import es.codemotion.madrid.library.dao.Item;
import es.codemotion.madrid.library.models.Book;
import es.codemotion.madrid.library.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class CatalogServiceTest {

    private CatalogService catalogService;
    private BookRepository bookRepository = mock(BookRepository.class);
    private Item first = new Item("First book", "Author", "Description", 1, true, "path");
    private Item second = new Item("Second book", "Author", "Description", 5, true, "path");
    private List<Item> completeCollection = new ArrayList() {{
        add(first);
        add(second);
    }};

    @Before
    public void setUp() {
        catalogService = new CatalogService();
        when(bookRepository.findAll()).thenReturn(completeCollection);
    }

    @Test
    public void shouldReturnAllBooksAvailableInBookRepository() {
        List<Book> completeCatalog = catalogService.getAllBooks();
        assertThat(completeCatalog, is(notNullValue()));
    }
}
