package ucc.interfaces;

import dto.UserDto;

import java.util.ArrayList;

public interface UserUcController {

  UserDto login(String login, String password);

  UserDto register(UserDto userdto);

  UserDto getUserById(int id);

  ArrayList<UserDto> getAllUsers();
}
