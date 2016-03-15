package bizz;

import dto.MobilityDto;
import dto.UserDto;

public class BizzFactoryImpl implements BizzFactory {

  /**
   * Return an empty UserDto.
   * 
   * @return a new UserDto.
   */
  @Override
  public UserDto getUserDto() {
    return new UserImpl();
  }

  /**
   * Return an empty MobilityDto.
   * 
   * @return a new MobilityDto.
   */
  @Override
  public MobilityDto getMobilityDto() {
    return new MobilityImpl();
  }

}
