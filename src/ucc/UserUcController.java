package ucc;

import dto.UserDto;

public interface UserUcController {

  UserDto login(String login, String password);

}
