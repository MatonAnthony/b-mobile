package bizz;

import dto.CountryDto;
import dto.DepartmentDto;
import dto.MobilityDto;
import dto.PartnerDto;
import dto.ProgramDto;
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


  @Override
  public DepartmentDto getDepartmentDto() {
    return new DepartmentImpl();
  }


  @Override
  public ProgramDto getProgramDto() {
    return new ProgramImpl();
  }


  @Override
  public PartnerDto getPartnerDto() {
    return new PartnerImpl();
  }

}
