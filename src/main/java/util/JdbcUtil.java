package util;

import dao.DBConnector;
import exceptions.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtil {
    private static final DBConnector connector = DBConnector.getInstance();
    private static final String QUERY_DELIMITER = "/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;";

    private static final String SCHEMA_PATH = "src/main/resources/db/init_tables.sql";
    private static final String DATA_PATH = "src/main/resources/db/populate_db.sql";

    private static final String CREATE_TABLE_QUERY = SQLFileUtil.getStringWithSQLQueryFromFile(SCHEMA_PATH);
    private static final String GENERATE_DATA_QUERY = SQLFileUtil.getStringWithSQLQueryFromFile(DATA_PATH);

    public static void initTablesInDB() {
        executeQuery(CREATE_TABLE_QUERY);
    }

    public static void populateTablesInDB() {
        executeQuery(GENERATE_DATA_QUERY);
    }

    private static void executeQuery(String query) {
        String[] queries = query.split(QUERY_DELIMITER);
        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {
            for (String queryLine : queries) {
                queryLine = queryLine.replace("\n", "");
                if (!queryLine.isEmpty()) {
                    statement.execute(queryLine.trim());
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Query was not processed", e);
        }
    }

}
