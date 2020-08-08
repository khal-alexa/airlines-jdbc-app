package dao;

import domain.Crew;
import exceptions.DaoException;
import exceptions.NoSuchIdException;

public interface CrewDao {
    void save(Crew crew) throws DaoException;

    Crew findById(int id) throws NoSuchIdException;

    void linkCrewToAirplane(int airplaneId, int crewId) throws DaoException;
}
