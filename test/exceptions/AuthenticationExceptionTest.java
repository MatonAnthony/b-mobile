package exceptions;

import org.junit.Test;

public class AuthenticationExceptionTest {

  @Test(expected = AuthenticationException.class)
  public void testThrowException() throws AuthenticationException {
    throw new AuthenticationException();
  }

  @Test(expected = AuthenticationException.class)
  public void testThrowExceptionWithMessage() throws AuthenticationException {
    throw new AuthenticationException("Contains a message");
  }
}
