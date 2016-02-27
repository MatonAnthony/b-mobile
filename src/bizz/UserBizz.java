package bizz;

interface UserBizz extends UserDto {

  // ?
  void cryptPassword();

  boolean checkPassword(String passwordToCheck);

}
