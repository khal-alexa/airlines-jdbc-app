package util;

import exceptions.DaoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class SQLFileUtil {
    public static String getStringWithSQLQueryFromFile(String filePath) {
        Path path = Paths.get(filePath);
        return convertSQLFileToString(path);
    }

    private static String convertSQLFileToString(Path path) {
        String query = "";
        try {
            query = String.join("\n", Files.readAllLines(path));
        } catch (IOException e) {
            throw new DaoException(String.format("Could not read lines from file with path: %s", path), e);
        }
        return query;
    }
}
