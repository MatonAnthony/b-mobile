package ucc.interfaces;

import dto.UserDto;

import java.util.ArrayList;

public interface UserUcController {

  UserDto login(String login, String password);

  UserDto register(UserDto userdto);

  UserDto getUserById(int id);

  ArrayList<UserDto> getAllUsers();

  /**
   * Change the permission of the user of id "id" and set them to TEACHER.
   * 
   * @param id the id of the user to change.
   */
  void changePermissions(int id);
}
