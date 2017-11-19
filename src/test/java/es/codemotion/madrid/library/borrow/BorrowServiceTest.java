package es.codemotion.madrid.library.borrow;

import es.codemotion.madrid.library.dao.ItemAvailability;
import es.codemotion.madrid.library.repositories.ItemAvailabilityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowServiceTest {

    @Autowired
    private BorrowService borrowService;
    @MockBean
    private ItemAvailabilityRepository itemAvailabilityRepository;
    private ItemAvailability first = new ItemAvailability(true);
    private ItemAvailability second = new ItemAvailability(false);

    @Before
    public void setUp() {
        initMocks(this);
        when(itemAvailabilityRepository.findOne(1L)).thenReturn(first);
        when(itemAvailabilityRepository.findOne(2L)).thenReturn(second);
    }

    @Test
    public void shouldReturnItemAvailability() {
        assertThat(borrowService.getAvailability(1L), samePropertyValuesAs(first));
        assertThat(borrowService.getAvailability(2L), samePropertyValuesAs(second));
    }

}
