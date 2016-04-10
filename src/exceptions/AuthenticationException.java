package exceptions;

/**
 * The AuthenticationException is used to manage all errors during the authentication process.
 */
@SuppressWarnings("serial")
public class AuthenticationException extends Exception {

  /**
   * Instantiates a new Authentication exception.
   */
  public AuthenticationException() {
    super();
  }

  /**
   * Instantiates a new Authentication exception.
   *
   * @param message the message to display
   */
  public AuthenticationException(String message) {
    super(message);
  }

}
