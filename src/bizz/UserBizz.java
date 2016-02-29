package bizz;

import dto.UserDto;

public interface UserBizz extends UserDto {

  void cryptPassword();

  boolean checkPassword(String passwordToCheck);

}
