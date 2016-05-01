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
import exceptions.OptimisticLockException;
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
  private ArrayList<UserDto> list;
  private BizzFactoryImpl bizz;

  @Before
  public void setUp() throws Exception {
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
    prof.setPassword("password");
    prof.setPermissions("TEACHER");
    UserImpl profImpl = (UserImpl) prof;
    profImpl.cryptPassword();
    list.add(profImpl);

    DalServices dal = new DalServicesImplStub();
    UserDao user = new UserDaoMock(list);
    userUcc = new UserUcControllerImpl(dal, user);
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
  public void testChangePermissions() throws UserNotFoundException, OptimisticLockException {
    userUcc.changePermissions(1, 0);
  }

  @Test(expected = UserNotFoundException.class)
  public void testChangePermissions2() throws UserNotFoundException, OptimisticLockException {
    userUcc.changePermissions(5, 0);
  }

  @Test(expected = UserNotFoundException.class)
  public void testChangePermissions3() throws UserNotFoundException, AuthenticationException,
      UserAlreadyExistsException, OptimisticLockException {
    // Test if it is a teacher.
    userUcc.changePermissions(3, 0);
  }

  @Test
  public void testUpdateUser() throws OptimisticLockException {
    userdto.setCity("Bruxelles");
    userUcc.updateUser(userdto);
  }

}
