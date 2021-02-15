package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.model.CinemaHall;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {
    @Autowired
    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return super.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return super.getAll(CinemaHall.class);
    }

    @Override
    public Optional<CinemaHall> getById(Long id) {
        return super.getById(CinemaHall.class, id);
    }
}
