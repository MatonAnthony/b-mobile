package bizz;

import persistance.DalServices;
import persistance.UserDao;

class UserUcControllerImpl implements UserUcController {

  private UserDao userDao = null;
  private DalServices dalServices = null;

  @Override
  public UserDto login(UserDto userDto) {

    UserBizz user = (UserBizz) userDto;
    user.cryptPassword();
    // Récupérer les données du DAL


    // if valide
    // return userDto

    // else
    return null;
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
