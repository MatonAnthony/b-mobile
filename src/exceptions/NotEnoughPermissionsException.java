package exceptions;

/**
 * @author Martin
 * @since 20 avr. 2016
 */
public class NotEnoughPermissionsException extends Exception {

  public NotEnoughPermissionsException() {
    super();
  }

  public NotEnoughPermissionsException(String message) {
    super(message);
  }

}
