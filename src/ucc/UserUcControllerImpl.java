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

    if (user.checkPassword(password)) {
      return user;
    } else {
      return null;
    }
  }

  /**
   * Modifie le userDao.
   * 
   * @param userDao Le Dao à injecter.
   */
  @Override
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * Modifie le dalServices.
   * 
   * @param dalServices Le DalServices à injecter.
   */
  @Override
  public void setDalServices(DalServices dalServices) {
    this.dalServices = dalServices;
  }

}
