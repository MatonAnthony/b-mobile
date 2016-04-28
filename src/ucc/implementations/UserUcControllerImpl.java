package ucc.implementations;

import bizz.interfaces.UserBizz;
import dal.DalServices;
import dao.interfaces.UserDao;
import dto.UserDto;
import exceptions.AuthenticationException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import ihm.Main;
import ucc.interfaces.UserUcController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserUcControllerImpl implements UserUcController {

  private UserDao userDao = null;
  private DalServices dalServices = null;

  /**
   * Constructeur d'un userUcController.
   * 
   * @param dalServices Le dalServices necessaire.
   * @param userDao Le userDao necessaire
   */
  public UserUcControllerImpl(DalServices dalServices, UserDao userDao) {

    this.userDao = userDao;
    this.dalServices = dalServices;

  }

  @Override
  public UserDto login(String login, String password) throws AuthenticationException, SQLException {
    UserBizz user;
    try {
      // Récupérer les données du DAL
      dalServices.openConnection();
      user = (UserBizz) userDao.getUserByUserName(login);
    } catch (NoSuchElementException nsee) {
      Main.LOGGER.warning("\"" + login + "\" : username not exist ");
      dalServices.closeConnection();
      throw new AuthenticationException("L'utilisateur n'existe pas.");
    }

    if (user.checkPassword(password)) {
      Main.LOGGER.info("[" + user.getPermissions() + "] \"" + login + "\" connected");
      dalServices.closeConnection();
      return user;
    } else {
      Main.LOGGER.warning("\"" + login + "\" : bad password ");
      dalServices.closeConnection();
      throw new AuthenticationException("Le mot de passe indiqué est incorrect.");
    }
  }

  @Override
  public UserDto register(UserDto userdto)
      throws AuthenticationException, UserAlreadyExistsException {

    String password = userdto.getPassword();
    UserBizz userBizz = (UserBizz) userdto;
    userBizz.cryptPassword();

    try {
      dalServices.openConnection();
      dalServices.startTransaction();
      if (userDao.userExists(userdto.getPseudo())) {
        throw new UserAlreadyExistsException("Un utilisateur existe déjà sous ce nom.");
      }
      if (userDao.countUser() == 0) {
        userBizz.setPermissions("TEACHER");
      } else {
        userBizz.setPermissions("STUDENT");
      }
      userDao.createUser(userBizz);
      dalServices.commitTransaction();
      dalServices.closeConnection();
      userBizz = (UserBizz) login(userBizz.getPseudo(), password);
      return userBizz;
    } catch (SQLException exc) {
      try {
        dalServices.rollbackTransaction();
        dalServices.closeConnection();
      } catch (SQLException exc2) {
        exc2.printStackTrace();
      }
    }

    return null;
  }

  @Override
  public UserDto getUserById(int id) throws SQLException {
    dalServices.openConnection();
    UserDto user = userDao.getUserById(id);
    dalServices.closeConnection();
    return user;
  }

  @Override
  public ArrayList<UserDto> getAllUsers() throws SQLException {
    dalServices.openConnection();
    ArrayList<UserDto> users = userDao.getAllUsers();
    dalServices.closeConnection();
    return users;
  }

  @Override
  public void changePermissions(int id) throws UserNotFoundException {
    try {
      dalServices.openConnection();
      dalServices.startTransaction();
      try {
        UserDto user = userDao.getUserById(id);
        if (user.getPermissions().equals("STUDENT")) {
          userDao.changePermissionsForUserById(user);
        } else {
          throw new UserNotFoundException(
              "Cet utilisateur est un professeur et ne peut être promu.");
        }
      } catch (NoSuchElementException ex) {
        throw new UserNotFoundException("Cet utilisateur n'existe pas");
      }

      dalServices.commitTransaction();
      dalServices.closeConnection();
    } catch (SQLException exc1) {
      try {
        dalServices.rollbackTransaction();
        dalServices.closeConnection();
      } catch (SQLException exc2) {
        exc2.printStackTrace();
      }
    }
  }

  @Override
  public void updateUser(UserDto userEdited) {
    try {
      dalServices.openConnection();
      dalServices.startTransaction();
      userDao.updateUser(userEdited);
      dalServices.commitTransaction();
      dalServices.closeConnection();
    } catch (SQLException exc1) {
      exc1.printStackTrace();
      try {
        dalServices.rollbackTransaction();
        dalServices.closeConnection();
      } catch (SQLException exc2) {
        exc2.printStackTrace();
      }
    }
  }

}
