package exceptions;

import java.sql.SQLException;

public class NoSuchIdException extends RuntimeException {
    public NoSuchIdException(String message) {
        super(message);
    }

    public NoSuchIdException(String message, SQLException cause) {
        super(message, cause);
    }

}
