package bizz.enumeration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by anthony on 10/04/16.
 */
public class PermissionsTest {

  @Test
  public void testValueOf() {
    assertEquals(Permissions.valueOf("STUDENT"), Permissions.STUDENT);
    assertEquals(Permissions.valueOf("TEACHER"), Permissions.TEACHER);
  }
}
