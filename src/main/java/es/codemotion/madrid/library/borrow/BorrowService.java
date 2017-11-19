package es.codemotion.madrid.library.borrow;

import es.codemotion.madrid.library.dao.ItemAvailability;
import es.codemotion.madrid.library.repositories.ItemAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {

    @Autowired
    private ItemAvailabilityRepository itemAvailabilityRepository;

    public ItemAvailability getAvailability(long itemId) {
        return itemAvailabilityRepository.findOne(itemId);
    }

}
