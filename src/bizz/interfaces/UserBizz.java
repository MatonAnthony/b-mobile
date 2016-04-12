package bizz.interfaces;

import dto.UserDto;

public interface UserBizz extends UserDto {

  /**
   * Encrypt the password contained in the calling UserDto.
   */
  void cryptPassword();

  /**
   * Check if the password matches the one stored.
   *
   * @param passwordToCheck String password to be compared with our BCrypt password
   * @return whether or not the password is matching or not
   */
  boolean checkPassword(String passwordToCheck);

}
