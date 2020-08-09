package dao;

import domain.Airplane;
import domain.Crew;
import exceptions.DaoException;
import exceptions.NoSuchIdException;

import java.util.List;

public interface AirplaneDao {
    void save(Airplane airplane) throws DaoException;

    Airplane findByCode(String code) throws DaoException;

    List<Airplane> findAll() throws DaoException;

    void delete(int id) throws NoSuchIdException;

    List<Airplane> findByCrew(Crew crew) throws DaoException;
}
