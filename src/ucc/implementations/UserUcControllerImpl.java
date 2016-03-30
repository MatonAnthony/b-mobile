package ucc.implementations;

import bizz.interfaces.UserBizz;
import dal.DalServices;
import dao.interfaces.UserDao;
import dto.UserDto;
import ucc.interfaces.UserUcController;

import java.sql.SQLException;
import java.util.ArrayList;
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
   * @return null si l'utilisateur n'a pas été trouvé dans la base de donnée ou si le mot de passe
   *         entre n'est pas identique au mot de passe de la base de donnee.
   */
  @Override
  public UserDto login(String username, String password) {

    // Récupérer les données du DAL
    UserBizz user = (UserBizz) userDao.getUserByUserName(username);

    // L'user est null si aucun utilisateur avec le pseudo entré n'existe
    if (null == user) {
      LOGGER.warning("\"" + username + "\" : username not exist ");
      return null;
    }

    if (user.checkPassword(password)) {
      LOGGER.info("[" + user.getPermissions() + "] \"" + username + "\" connected");
      return user;
    } else {
      LOGGER.warning("\"" + username + "\" : bad password ");
      return null;
    }
  }

  /**
   * The function register new user in the data base.
   * 
   * @param userdto is the user to register.
   * @return a userDto. It is the user added. Null if there was a error."
   */

  public UserDto register(UserDto userdto) {
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

}
