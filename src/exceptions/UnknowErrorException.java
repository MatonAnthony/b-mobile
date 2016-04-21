package exceptions;

@SuppressWarnings("serial")
public class UnknowErrorException extends RuntimeException {

  public UnknowErrorException(String message) {
    super(message);
  }

  public UnknowErrorException() {
    super();
  }
}
