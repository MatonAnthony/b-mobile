package exceptions;

/**
 * @author Martin
 * @since 28 avr. 2016
 */
@SuppressWarnings("serial")
public class BadMobilityStatusException extends Exception {

  public BadMobilityStatusException() {
    super();
  }

  public BadMobilityStatusException(String message) {
    super(message);
  }

}
