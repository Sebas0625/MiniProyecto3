package project.miniproject3.model.Exception;

/**
 * Custom exception class to handle game-specific errors.
 * This class extends {@link Exception} and provides three constructors
 * for creating instances of the exception with various levels of detail.
 */
public class GameException extends Exception {

    /**
     * Default constructor for the {@link GameException} class.
     * Calls the superclass constructor with no message or cause.
     */
    public GameException() {
        super();
    }

    /**
     * Constructor for the {@link GameException} class with a specified error message.
     *
     * @param message the error message explaining the cause of the exception
     */
    public GameException(String message) {
        super(message);
    }

    /**
     * Constructor for the {@link GameException} class with a specified error message
     * and the underlying cause of the exception.
     *
     * @param message the error message explaining the cause of the exception
     * @param cause the underlying cause of the exception, which can be another throwable
     */
    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
}
