package utils;

import org.junit.Test;

public class UtilsTest {

  @Test
  public void testCheckObjects() {
    String string = "string";
    Utils.checkObjects(string, String.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckObjects1() {
    Integer number = Integer.valueOf(1);
    Utils.checkObjects(number, String.class);
  }
}
