package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.model.Movie;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return super.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return super.getAll(Movie.class);
    }

    @Override
    public Optional<Movie> getById(Long id) {
        return super.getById(Movie.class, id);
    }
}
