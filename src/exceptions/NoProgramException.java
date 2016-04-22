package exceptions;

@SuppressWarnings("serial")
public class NoProgramException extends Exception {

  public NoProgramException(String message) {
    super(message);
  }

  public NoProgramException() {
    super();
  }

}
