package bizz;

import dto.CountryDto;
import dto.DepartmentDto;
import dto.MobilityDto;
import dto.PartnerDto;
import dto.ProgramDto;
import dto.UserDto;

public interface BizzFactory {
  /**
   * Return an empty UserDto.
   * 
   * @return a new UserDto.
   */
  UserDto getUserDto();

  /**
   * Return an empty MobilityDto.
   * 
   * @return a new MobilityDto.
   */
  MobilityDto getMobilityDto();

  /**
   * Return an empty CountryDto.
   * 
   * @return a new CountryDto.
   */
  CountryDto getCountryDto();

  /**
   * Return an empty DepartmentDto
   * 
   * @return a new DepartmentDto
   */
  DepartmentDto getDepartmentDto();

  /**
   * Return an empty ProgramDto
   * 
   * @return a new ProgramDto
   */
  ProgramDto getProgramDto();


  /**
   * Return an empty PartnerDto
   * 
   * @return a new PartnerDto
   */
  PartnerDto getPartnerDto();

}
