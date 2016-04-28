package exceptions;

/**
 * @author Martin
 * @since 28 avr. 2016
 */
@SuppressWarnings("serial")
public class OptimisticLockException extends Exception {

  public OptimisticLockException() {
    super();
  }

  public OptimisticLockException(String message) {
    super(message);
  }
}
