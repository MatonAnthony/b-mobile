package ucc;

import dal.DalServices;
import dao.UserDao;
import dto.UserDto;

public interface UserUcController {

  UserDto login(String login, String password);

  void setUserDao(UserDao userDao);

  void setDalServices(DalServices dalServices);

}
