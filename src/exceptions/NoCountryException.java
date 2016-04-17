package exceptions;

public class NoCountryException extends Exception {

  public NoCountryException(String message) {
    super(message);
  }

  public NoCountryException(){
    super();
  }
}
