package bizz;

import dto.UserDto;

public class BizzFactoryImpl implements BizzFactory {

  @Override
  /**
   * Return an empty UserDto.
   * 
   * @return a new UserDto.
   */
  public UserDto getUserDto() {
    return new UserImpl();
  }

}
