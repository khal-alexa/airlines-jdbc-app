package dao;

import domain.Airplane;
import domain.Crew;
import exceptions.DaoException;
import exceptions.NoSuchIdException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDaoImpl implements AirplaneDao {
    private DBConnector connector = DBConnector.getInstance();

    private static final String INSERT_QUERY = "INSERT INTO airplanes (code_name, model, manufacture_date, " +
            "capacity, flight_range) VALUES (?, ?, ?, ?, ?);";

    private static final String READ_QUERY = "SELECT * FROM airplanes;";

    private static final String GET_BY_CODE_QUERY = "SELECT * FROM airplanes WHERE code_name = ?;";

    private static final String GET_BY_CREW_NAME_QUERY = "SELECT airplanes.* FROM airplanes\n" +
            "    INNER JOIN airplanes_crews ac on airplanes.id = ac.airplanes_id\n" +
            "    INNER JOIN crews c on ac.crews_id = c.id\n" +
            "WHERE c.first_name = ? AND c.last_name = ?;";

    private static final String DELETE_QUERY = "DELETE FROM airplanes WHERE id = ?;";

    @Override
    public void save(Airplane airplane) throws DaoException {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, airplane.getCodeName());
            statement.setString(2, airplane.getModel());
            statement.setDate(3, Date.valueOf(airplane.getManufactureDate()));
            statement.setDouble(4, airplane.getCapacity());
            statement.setDouble(5, airplane.getFlightRange());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                airplane.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new DaoException("Error while trying to save airplane", e);
        }
    }

    @Override
    public Airplane findByCode(String code) throws DaoException {
        Airplane airplane = null;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_CODE_QUERY)) {
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                airplane = getAirplaneFromDB(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("Error occurred while trying to find airplane by code", e);
        }
        return airplane;
    }

    @Override
    public List<Airplane> findAll() throws DaoException {
        List<Airplane> airplanes = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(READ_QUERY);
            while (rs.next()) {
                Airplane airplane = getAirplaneFromDB(rs);
                airplanes.add(airplane);
            }

        } catch (SQLException e) {
            throw new DaoException("Error occurred while trying to get airplanes list", e);
        }
        return airplanes;
    }

    @Override
    public void delete(int id) throws NoSuchIdException {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NoSuchIdException("Airplane was not found", e);
        }
    }

    @Override
    public List<Airplane> findByCrewFullName(Crew crew) throws DaoException {
        List<Airplane> airplanes = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_CREW_NAME_QUERY)) {
            statement.setString(1, crew.getFirstName());
            statement.setString(2, crew.getLastName());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Airplane airplane = getAirplaneFromDB(rs);
                airplanes.add(airplane);
            }

        } catch (SQLException e) {
            throw new DaoException("Error occurred while trying to find airplane by crew name", e);
        }
        return airplanes;
    }

    public Airplane getAirplaneFromDB(ResultSet rs) throws SQLException {
        return new Airplane(rs.getInt("id"),
                rs.getString("code_name"), rs.getString("model"),
                rs.getDate("manufacture_date").toLocalDate(),
                rs.getDouble("capacity"), rs.getDouble("flight_range"));
    }

}
