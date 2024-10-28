package backend.academy.log_analizer.exception;

public class InvalidLogString extends RuntimeException {
    public InvalidLogString(String message) {
        super(message);
    }
}
