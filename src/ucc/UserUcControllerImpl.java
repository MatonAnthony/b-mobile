package ucc;

import bizz.UserBizz;
import dal.DalServices;
import dao.UserDao;
import dto.UserDto;

class UserUcControllerImpl implements UserUcController {

  private UserDao userDao = null;
  private DalServices dalServices = null;

  public UserUcControllerImpl(DalServices dalServices, UserDao userDao) {

    this.userDao = userDao;
    this.dalServices = dalServices;

  }

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
