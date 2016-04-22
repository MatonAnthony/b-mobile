package dao.interfaces;

import dto.UserDto;
import exceptions.NoCountryException;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The interface User dao.
 */
public interface UserDao {
  /**
   * Gets users by his username.
   *
   * @param username username to base our research on.
   * @return An UserDto filled with his data.
   * @throw NoSuchElementException if no user is matching with the username.
   */
  UserDto getUserByUserName(String username) throws NoSuchElementException;

  /**
   * To add a user in the data base.
   *
   * @param userdto is the user to add.
   */
  void createUser(UserDto userdto);

  /**
   * Gets user by id.
   *
   * @param id the id
   * @return the user by id
   * @throw NoSuchElementException if no user is matching with the username.
   */
  UserDto getUserById(int id) throws NoSuchElementException;

  /**
   * Gets all users.
   *
   * @return An ArrayList with all users.
   */
  ArrayList<UserDto> getAllUsers();

  /**
   * Change permissions for user by id.
   *
   * @param user the user
   */
  void changePermissionsForUserById(UserDto user);

  /**
   * Update user profile.
   *
   * @param userEdited The user edited
   */
  void updateUser(UserDto userEdited);

  /**
   * Gets the existence of the username.
   * 
   * @param username to check in the database.
   * @return true if the username exists in the database, else return false
   */
  boolean userExists(String username);
}
