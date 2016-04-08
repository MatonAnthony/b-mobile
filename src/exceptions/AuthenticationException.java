package exceptions;

@SuppressWarnings("serial")
public class AuthenticationException extends Exception {

  public AuthenticationException() {
    super();
  }

  public AuthenticationException(String message) {
    super(message);
  }

}
