package bizz;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dto.UserDto;

public class BizzFactoryImplTest {

  private BizzFactory bizzFactory;
  private UserDto userDto;

  @Before
  public void setUp() {
    bizzFactory = new BizzFactoryImpl();
    userDto = new UserImpl();
  }

  @Test
  public void testGetUserDto() {
    assertEquals(userDto, bizzFactory.getUserDto());
  }

}
