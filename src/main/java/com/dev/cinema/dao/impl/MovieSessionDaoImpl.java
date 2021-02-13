package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.AbstractDao;
import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl extends AbstractDao<MovieSession> implements MovieSessionDao {
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = super.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery("select ms from MovieSession ms "
                            + "join fetch ms.movie m "
                            + "where m.id = :movieId "
                            + "and DATE_FORMAT(ms.showTime,'%Y-%m-%d') = :date",
                    MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("date", date.toString());
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot find movie session with movie id " + movieId
                    + " and date " + date, e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return super.add(movieSession);
    }

    @Override
    public Optional<MovieSession> getById(Long id) {
        return super.getById(MovieSession.class, id);
    }

    @Override
    public MovieSession delete(MovieSession movieSession) {
        return super.delete(movieSession);
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        return super.update(movieSession);
    }
}
