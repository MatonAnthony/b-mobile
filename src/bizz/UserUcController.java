package bizz;

import persistance.DalServices;
import persistance.UserDao;

public interface UserUcController {

  UserDto login(UserDto userDto);

  void setUserDao(UserDao userDao);

  void setDalServices(DalServices dalServices);

}
