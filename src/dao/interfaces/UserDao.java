package dao.interfaces;

import dto.UserDto;
import exceptions.MalformedIbanException;

import java.sql.SQLException;
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
   * @throws MalformedIbanException If the Iban is malformed.
   * @throw NoSuchElementException if no user is matching with the username.
   */
  UserDto getUserByUserName(String username) throws NoSuchElementException, MalformedIbanException;

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
   * @throws MalformedIbanException If the Iban is malformed.
   * @throw NoSuchElementException if no user is matching with the username.
   */
  UserDto getUserById(int id) throws NoSuchElementException, SQLException, MalformedIbanException;

  /**
   * Gets all users.
   *
   * @return An ArrayList with all users.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  ArrayList<UserDto> getAllUsers() throws SQLException, MalformedIbanException;

  /**
   * Change permissions for user by id.
   *
   * @param user the user
   * @return the number of rows modified
   */
  int changePermissionsForUserById(UserDto user);

  /**
   * Update user profile.
   *
   * @param userEdited The user edited
   * @return the number of rows modified
   */
  int updateUser(UserDto userEdited);

  /**
   * Gets the existence of the username.
   * 
   * @param username to check in the database.
   * @return true if the username exists in the database, else return false.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  boolean userExists(String username) throws MalformedIbanException;

  /**
   * Gets the number of user in the database.
   * 
   * @return the number of user present in the database. returns -1 if there is an error.
   */
  int countUser();
}
