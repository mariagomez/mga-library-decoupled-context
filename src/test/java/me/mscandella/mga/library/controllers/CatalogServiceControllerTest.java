package me.mscandella.mga.library.controllers;

import me.mscandella.mga.library.services.CatalogService;
import me.mscandella.mga.library.dao.Book;
import me.mscandella.mga.library.models.BookWithRating;
import me.mscandella.mga.library.repositories.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CatalogController.class)
public class CatalogServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CatalogService catalogService;

    private final String name = "Lorem Ipsum";
    private final String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas placerat odio felis, vel bibendum justo pulvinar nec. Nam et consectetur turpis, sed venenatis diam. Nunc consectetur ultrices nisl venenatis venenatis. Integer venenatis suscipit lorem quis varius. Aliquam quis erat erat. Nunc aliquet nulla in turpis imperdiet, eget condimentum tellus ornare. Pellentesque fringilla dictum massa, et dapibus purus elementum vitae. Aliquam erat volutpat. Donec libero ante, molestie porta odio ut, lobortis finibus urna. Aenean interdum massa elit, ut feugiat urna rhoncus eu. Morbi ac ex ut lorem cursus congue. Mauris dignissim libero et ullamcorper bibendum. Ut turpis metus, viverra et cursus eget, suscipit ut arcu. Morbi sit amet vehicula est. Quisque sodales sapien elit, in pharetra erat elementum ut. In hac habitasse platea dictumst.";
    private final int rating = 3;
    private final String imagePath = "http://bulma.io/images/placeholders/640x480.png";
    private final boolean available = true;
    private final String author = "Lorem Ipsum Dolor";

    @Test
    public void shouldReturnAListOfBooks() throws Exception {
        BookWithRating book = new BookWithRating(1l, name, author, description, rating, imagePath, available);
        List<BookWithRating> books = Arrays.asList(book);
        when(catalogService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/catalog"))
                .andExpect(view().name("catalog"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());
    }

    @Test
    public void shouldRedirectToCatalogAfterBorrow() throws Exception {
        when(bookRepository.findOne(anyLong())).thenReturn(any(Book.class));
        mockMvc.perform(post("/catalog/borrow", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/catalog"));
    }
}