package es.codemotion.madrid.library.borrow;

import es.codemotion.madrid.library.dao.ItemAvailability;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowServiceTest {

    private BorrowService borrowService;

    private ItemAvailability first = new ItemAvailability(true);
    private ItemAvailability second = new ItemAvailability(false);

    @Before
    public void setUp() {
        borrowService = new BorrowService();
    }

    @Test
    public void shouldReturnItemAvailability() {
        assertThat(borrowService.getAvailability(1L), samePropertyValuesAs(first));
        assertThat(borrowService.getAvailability(2L), samePropertyValuesAs(second));
    }

}
