package dao.interfaces;

import dto.UserDto;

import java.util.ArrayList;

public interface UserDao {
  /**
   * Cherche et renvoie l'utilisateur sur base du pseudo.
   * 
   * @param username Le pseudo de l'utilisateur a rechercher.
   * @return null si l'utilisateur n'est pas enregistre dans la BDD. Un dto avec les informations de
   *         l'utilisateur si l'utilisateur est enregistre dans la BDD.
   */
  UserDto getUserByUserName(String username);

  /**
   * To add a user in the data base.
   * 
   * @param userdto is the user to add.
   * @return true if the user is added. False is there was a error.
   */
  boolean createUser(UserDto userdto);

  UserDto getUserById(int id);

  ArrayList<UserDto> getAllUsers();

}
