package ucc;

import bizz.implementations.BizzFactoryImpl;
import dal.DalBackendServices;
import dal.DalServices;
import dal.DalServicesImpl;
import dao.implementations.UserDaoImpl;
import dao.interfaces.UserDao;
import dto.UserDto;
import exceptions.AuthenticationException;
import ucc.implementations.UserUcControllerImpl;

import org.junit.Before;
import org.junit.Test;


/**
 * Beware that class require to use a DEBUG Database, implying that build.properties status set to
 * debug
 */
public class UserUcControllerImplTest {

  private UserUcControllerImpl userUcc;
  private UserDto userdto;
  private UserDto empty;

  @Before
  public void setUp() throws Exception {
    DalServices dal = new DalServicesImpl();
    BizzFactoryImpl bizz = new BizzFactoryImpl();
    UserDao user = new UserDaoImpl((DalBackendServices) dal, bizz);
    userUcc = new UserUcControllerImpl(dal, user);
    userdto = bizz.getUserDto();
    userdto.setPseudo("pseudo");
    userdto.setPassword("password");
    userdto.setFirstname("firstname");
    userdto.setName("name");
    userdto.setEmail("email@email.email");
    userdto.setPermissions("STUDENT");
    // user.createUser(userdto);

    empty = bizz.getUserDto();
  }

  // Test login with a valid username - password
  /*
   * @Test public void testLogin() throws Exception { UserDto compare = userUcc.login("pseudo",
   * "password"); assertEquals(compare, userdto); }
   */


  // Test login with an invalid username-password
  @Test(expected = AuthenticationException.class)
  public void testLogin1() throws Exception {
    userUcc.login("pp", "jj");
  }

  // Test register with a valid new user.
  /*
   * @Test public void testRegister() throws Exception { empty.setPseudo("empty");
   * empty.setPassword("empty"); empty.setFirstname("empty"); empty.setName("empty");
   * empty.setEmail("empty"); empty.setPermissions("STUDENT");
   * 
   * userUcc.register(empty); }
   */

  // Test register with an already existing user
  // @Test
  // public void testRegister1() throws Exception {
  // assertNull(userUcc.register(userdto));
  // }

}
