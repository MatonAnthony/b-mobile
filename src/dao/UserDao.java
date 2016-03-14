package dao;

import dto.UserDto;

public interface UserDao {

  UserDto findByUserName(String username);

  boolean createUser(UserDto userdto);

  void read();

  boolean update();

  boolean delete();

}
