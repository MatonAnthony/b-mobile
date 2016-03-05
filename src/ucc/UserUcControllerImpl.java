package ucc;

import java.sql.SQLException;

import bizz.UserBizz;
import dal.DalServices;
import dao.UserDao;
import dto.UserDto;

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

    UserBizz user = null;

    // Récupérer les données du DAL
    try {
      dalServices.startTransaction();
      user = (UserBizz) userDao.findByUserName(username);

      dalServices.commitTransaction();
    } catch (SQLException exc) {
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc2) {
        exc2.printStackTrace();
      }
    }
    // L'user est null si aucun utilisateur avec le pseudo entré n'existe
    if (null == user)
      return null;

    if (user.checkPassword(password)) {
      return user;
    } else {
      return null;
    }
  }



}
