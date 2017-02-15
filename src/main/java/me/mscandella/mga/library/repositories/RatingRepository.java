package me.mscandella.mga.library.repositories;

import me.mscandella.mga.library.dao.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
}
