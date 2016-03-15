package bizz;

import dto.MobilityDto;
import dto.UserDto;

public interface BizzFactory {

  UserDto getUserDto();

  MobilityDto getMobilityDto();

}
