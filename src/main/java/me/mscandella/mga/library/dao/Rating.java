package me.mscandella.mga.library.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int rating;

    protected Rating() {
    }

    public Rating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
