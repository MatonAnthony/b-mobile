package ucc;

import static org.junit.Assert.assertEquals;

import bizz.implementations.BizzFactoryImpl;
import bizz.implementations.UserImpl;
import dal.DalServices;
import dal.DalServicesImplStub;
import dao.UserDaoMock;
import dao.interfaces.UserDao;
import dto.UserDto;
import exceptions.AuthenticationException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import ucc.implementations.UserUcControllerImpl;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserUcControllerImplTest {

  private UserUcControllerImpl userUcc;
  private UserDto userdto;
  private UserDto empty;
  private ArrayList<UserDto> list;
  private BizzFactoryImpl bizz;

  @Before
  public void setUp() throws Exception {
    DalServices dal = new DalServicesImplStub();
    bizz = new BizzFactoryImpl();
    userdto = bizz.getUserDto();
    userdto.setId(1);
    userdto.setPseudo("Toto");
    userdto.setPassword("password");
    userdto.setFirstname("firstname");
    userdto.setName("name");
    userdto.setEmail("email@email.email");
    userdto.setPermissions("STUDENT");
    UserImpl userImpl = (UserImpl) userdto;
    userImpl.cryptPassword();
    list = new ArrayList<UserDto>();
    list.add(userImpl);

    UserDto prof = bizz.getUserDto();
    prof.setId(3);
    prof.setPseudo("prof");
    prof.setPassword("pass");
    prof.setPermissions("TEACHER");
    UserImpl profImpl = (UserImpl) prof;
    profImpl.cryptPassword();
    list.add(profImpl);

    UserDao user = new UserDaoMock(list);
    userUcc = new UserUcControllerImpl(dal, user);
    empty = bizz.getUserDto();
  }

  @Test
  public void testLogin() throws AuthenticationException, SQLException {
    assertEquals(userdto, userUcc.login("Toto", "password"));
  }

  @Test(expected = AuthenticationException.class)
  public void testLogin2() throws AuthenticationException, SQLException {
    userUcc.login("Toto", "password2");
  }

  @Test(expected = AuthenticationException.class)
  public void testLogin3() throws AuthenticationException, SQLException {
    userUcc.login("Toto2", "password");
  }

  @Test(expected = UserAlreadyExistsException.class)
  public void testRegister() throws AuthenticationException, UserAlreadyExistsException {
    userUcc.register(userdto);
  }

  @Test
  public void testRegister2() throws AuthenticationException, UserAlreadyExistsException {
    UserDto userdto2 = bizz.getUserDto();
    userdto2.setPseudo("Boby");
    userdto2.setPassword("Banane");
    userUcc.register(userdto2);
  }

  @Test
  public void testGetUserById() throws SQLException {
    assertEquals(userdto, userUcc.getUserById(1));
  }

  @Test
  public void testGetAllUsers() throws SQLException {
    assertEquals(list, userUcc.getAllUsers());
  }

  @Test
  public void testChangePermissions() throws UserNotFoundException {
    userUcc.changePermissions(1);
  }

  @Test(expected = UserNotFoundException.class)
  public void testChangePermissions2() throws UserNotFoundException {
    userUcc.changePermissions(5);
  }

  @Test(expected = UserNotFoundException.class)
  public void testChangePermissions3()
      throws UserNotFoundException, AuthenticationException, UserAlreadyExistsException {
    // Test if it is a teacher.
    userUcc.changePermissions(3);
  }

  @Test
  public void testUpdateUser() {
    userdto.setCity("Bruxelles");
    userUcc.updateUser(userdto);
  }

}
