package dao;

import domain.Crew;
import exceptions.DaoException;
import exceptions.NoSuchIdException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrewDaoImpl implements CrewDao {
    private DBConnector connector = DBConnector.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO crews (first_name, last_name, position, " +
            "birthday, citizenship) VALUES (?, ?, ?, ?, ?);";

    private static final String GET_BY_ID = "SELECT * FROM crews WHERE id = ?;";

    private static final String LINK = "INSERT INTO airplanes_crews(airplanes_id, crews_id)\n" +
            "VALUES (?, ?);";

    @Override
    public void save(Crew crew) throws DaoException {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, crew.getFirstName());
            statement.setString(2, crew.getLastName());
            statement.setString(3, crew.getPosition());
            statement.setDate(4, Date.valueOf(crew.getBirthday()));
            statement.setString(5, crew.getCitizenship());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                crew.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while trying to save crew", e);
        }
    }

    @Override
    public Crew findById(int id) throws NoSuchIdException {
        Crew crew = null;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                crew = getCrewFromDB(rs);
            }
        } catch (SQLException e) {
            throw new NoSuchIdException("Crew was not found", e);
        }
        return crew;
    }

    @Override
    public void linkCrewToAirplane(int airplaneId, int crewId) throws DaoException {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(LINK)) {
            statement.setInt(1, airplaneId);
            statement.setInt(2, crewId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error occurred while trying to link crew to airplane", e);
        }
    }

    private Crew getCrewFromDB(ResultSet resultSet) throws SQLException {
        return new Crew(resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("position"),
                resultSet.getDate("birthday").toLocalDate(),
                resultSet.getString("citizenship"));
    }

}
