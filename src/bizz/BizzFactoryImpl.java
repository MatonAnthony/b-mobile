package bizz;

import dto.CountryDto;
import dto.MobilityDto;
import dto.UserDto;

public class BizzFactoryImpl implements BizzFactory {


  @Override
  public UserDto getUserDto() {
    return new UserImpl();
  }


  @Override
  public MobilityDto getMobilityDto() {
    return new MobilityImpl();
  }

  @Override
  public CountryDto getCountryDto() {
    return new CountryImpl();
  }

}
