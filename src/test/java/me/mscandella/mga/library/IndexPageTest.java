package me.mscandella.mga.library;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import me.mscandella.mga.library.services.CatalogService;
import me.mscandella.mga.library.controllers.CatalogController;
import me.mscandella.mga.library.controllers.IndexController;
import me.mscandella.mga.library.models.BookWithRating;
import me.mscandella.mga.library.repositories.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@WebMvcTest({IndexController.class, CatalogController.class})
public class IndexPageTest {

    @Autowired
    private WebClient webClient;

    @MockBean
    private CatalogService catalogService;
    @MockBean
    private BookRepository bookRepository;

    @Test
    public void shouldShowCatalogWhenRequestingIndex() throws Exception {
        BookWithRating book = mock(BookWithRating.class);
        given(catalogService.getAllBooks()).willReturn(Arrays.asList(book));

        HtmlPage page = this.webClient.getPage("/");
        assertThat(page.getBody().getTextContent()).contains("Catalog");
    }

}