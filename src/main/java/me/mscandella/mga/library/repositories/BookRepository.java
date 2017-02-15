package me.mscandella.mga.library.repositories;

import me.mscandella.mga.library.dao.Item;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Item, Long> {
}
