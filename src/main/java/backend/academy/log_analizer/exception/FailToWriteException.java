package backend.academy.log_analizer.exception;

public class FailToWriteException extends RuntimeException {
  public FailToWriteException(String message) {
    super(message);
  }
}
