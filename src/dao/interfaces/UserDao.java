package dao.interfaces;

import dto.UserDto;

import java.util.ArrayList;

/**
 * The interface User dao.
 */
public interface UserDao {
  /**
   * Gets users by his username.
   *
   * @param username username to base our research on.
   * @return null if the user is not registered, an UserDto filled with his data otherwise.
   */
  UserDto getUserByUserName(String username);

  /**
   * To add a user in the data base.
   *
   * @param userdto is the user to add.
   * @return true if the user is added. False is there was a error.
   */
  boolean createUser(UserDto userdto);

  /**
   * Gets user by id.
   *
   * @param id the id
   * @return the user by id
   */
  UserDto getUserById(int id);

  /**
   * Gets all users.
   *
   * @return the all users
   */
  ArrayList<UserDto> getAllUsers();

  /**
   * Change permissions for user by id.
   *
   * @param user the user
   */
  void changePermissionsForUserById(UserDto user);

}
