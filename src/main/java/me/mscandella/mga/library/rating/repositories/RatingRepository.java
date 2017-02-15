package me.mscandella.mga.library.rating.repositories;

import me.mscandella.mga.library.rating.dao.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
}
