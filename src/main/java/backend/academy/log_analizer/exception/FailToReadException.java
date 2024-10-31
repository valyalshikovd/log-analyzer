package backend.academy.log_analizer.exception;

public class FailToReadException extends RuntimeException {
    public FailToReadException(String message) {
        super(message);
    }
}
