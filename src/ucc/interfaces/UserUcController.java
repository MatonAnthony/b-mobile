package ucc.interfaces;

import dto.UserDto;
import exceptions.AuthenticationException;
import exceptions.UserAlreadyExistsException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserUcController {

  /**
   * Gere tout le processus de connexion d'un utilisateur;
   * 
   * @param login Le nom d'utilisateur avec lequel la connexion doit être effectuee
   * @param password Le mot de passe avec lequel la connexion doit être effectuee.
   * @throws AuthenticationException if the user doesn't exist or if the password is incorrect.
   * @throws SQLException If there is an Exception.
   */
  UserDto login(String login, String password) throws AuthenticationException, SQLException;

  /**
   * The function register new user in the data base.
   * 
   * @param userdto is the user to register.
   * @return a userDto. It is the user added. Null if there was a error."
   * @throws AuthenticationException if an error happen between register and login.
   * @throws UserAlreadyExistsException
   */
  UserDto register(UserDto userdto) throws AuthenticationException, UserAlreadyExistsException;

  UserDto getUserById(int id) throws SQLException;

  ArrayList<UserDto> getAllUsers() throws SQLException;

  /**
   * Change the permission of the user of id "id" and set them to TEACHER.
   * 
   * @param id the id of the user to change.
   */
  void changePermissions(int id);

  void updateUser(UserDto userEdited);
}
