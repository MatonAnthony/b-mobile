package exceptions;

/**
 * @author Martin
 * @since 20 avr. 2016
 */
public class UserNotFoundException extends Exception {

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String message) {
    super(message);
  }


}
