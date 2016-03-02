package dao;

import dto.UserDto;

public interface UserDao {

  UserDto findByUserName(String username);

  boolean createUser();

  void read();

  boolean update();

  boolean delete();

}
