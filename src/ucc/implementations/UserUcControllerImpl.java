package ucc.implementations;

import bizz.interfaces.UserBizz;
import dal.DalServices;
import dao.interfaces.UserDao;
import dto.UserDto;
import exceptions.AuthenticationException;
import ucc.interfaces.UserUcController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class UserUcControllerImpl implements UserUcController {

  private UserDao userDao = null;
  private DalServices dalServices = null;
  private static final Logger LOGGER = Logger.getLogger(UserUcControllerImpl.class.getName());

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

  /**
   * Gere tout le processus de connexion d'un utilisateur;
   * 
   * @param username Le nom d'utilisateur avec lequel la connexion doit être effectuee
   * @param password Le mot de passe avec lequel la connexion doit être effectuee.
   * @throws AuthenticationException if the user doesn't exist or if the password is incorrect.
   */
  @Override
  public UserDto login(String username, String password) throws AuthenticationException {
    UserBizz user;
    try {
      // Récupérer les données du DAL
      user = (UserBizz) userDao.getUserByUserName(username);
    } catch (NoSuchElementException nsee) {
      // L'user est null si aucun utilisateur avec le pseudo entré n'existe
      LOGGER.warning("\"" + username + "\" : username not exist ");
      throw new AuthenticationException("L'utilisateur n'existe pas.");
    }

    if (user.checkPassword(password)) {
      LOGGER.info("[" + user.getPermissions() + "] \"" + username + "\" connected");
      return user;
    } else {
      LOGGER.warning("\"" + username + "\" : bad password ");
      throw new AuthenticationException("Le mot de passe indiqué est incorrect.");
    }
  }

  /**
   * The function register new user in the data base.
   * 
   * @param userdto is the user to register.
   * @return a userDto. It is the user added. Null if there was a error."
   * @throws AuthenticationException if an error happen between register and login.
   */

  public UserDto register(UserDto userdto) throws AuthenticationException {
    String password = userdto.getPassword();
    UserBizz userBizz = (UserBizz) userdto;
    userBizz.cryptPassword();

    try {
      dalServices.startTransaction();
      if (userDao.getUserByUserName(userBizz.getPseudo()) != null) {
        return null;
      }
      if (!userDao.createUser(userBizz)) {
        return null;
      }
      dalServices.commitTransaction();
      userBizz = (UserBizz) login(userBizz.getPseudo(), password);
      return userBizz;
    } catch (SQLException exc) {
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc2) {
        exc2.printStackTrace();
      }
    }

    return null;
  }

  @Override
  public UserDto getUserById(int id) {
    return userDao.getUserById(id);
  }

  @Override
  public ArrayList<UserDto> getAllUsers() {
    return userDao.getAllUsers();
  }

  @Override
  public void changePermissions(int id) {
    try {
      dalServices.startTransaction();
      UserDto user = userDao.getUserById(id);
      if (user.getPermissions().equals("STUDENT")) {
        userDao.changePermissionsForUserById(user);
      } else {
        // TODO (Martin) Throw une exeption personnalisée si l'utilisateur n'est pas dans la BDD.
      }

      dalServices.commitTransaction();
    } catch (Exception e) {
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc) {
        exc.printStackTrace();
      }
    }
  }

  @Override
  public void updateUser(UserDto userEdited) {
    try {
      dalServices.startTransaction();
      userDao.updateUser(userEdited);
      dalServices.commitTransaction();
    } catch (SQLException exc1) {
      exc1.printStackTrace();
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc2) {
        exc2.printStackTrace();
      }
    }
  }

}
