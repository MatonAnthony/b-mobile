package exceptions;

public class NoDepartmentException extends Exception {

  public NoDepartmentException(String message) {
    super(message);
  }

  public NoDepartmentException() {
    super();
  }
}
