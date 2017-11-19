package es.codemotion.madrid.library.repositories;

import es.codemotion.madrid.library.dao.ItemAvailability;
import org.springframework.data.repository.CrudRepository;

public interface ItemAvailabilityRepository extends CrudRepository<ItemAvailability, Long> {
}
