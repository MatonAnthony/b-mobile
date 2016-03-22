package bizz;

import static org.junit.Assert.assertEquals;

import bizz.implementations.BizzFactoryImpl;
import bizz.implementations.CancelationImpl;
import bizz.implementations.CountryImpl;
import bizz.implementations.DepartmentImpl;
import bizz.implementations.MobilityImpl;
import bizz.implementations.PartnerImpl;
import bizz.implementations.ProgramImpl;
import bizz.implementations.UserImpl;
import bizz.interfaces.BizzFactory;
import dto.CancelationDto;
import dto.CountryDto;
import dto.DepartmentDto;
import dto.MobilityDto;
import dto.PartnerDto;
import dto.ProgramDto;
import dto.UserDto;

import org.junit.Before;
import org.junit.Test;

public class BizzFactoryImplTest {

  private BizzFactory bizzFactory;
  private UserDto userDto;
  private CountryDto countryDto;
  private PartnerDto partnerDto;
  private MobilityDto mobilityDto;
  private CancelationDto cancelationDto;
  private DepartmentDto departmentDto;
  private ProgramDto programDto;


  @Before
  public void setUp() {
    bizzFactory = new BizzFactoryImpl();
    userDto = new UserImpl();
    countryDto = new CountryImpl();
    partnerDto = new PartnerImpl();
    mobilityDto = new MobilityImpl();
    cancelationDto = new CancelationImpl();
    departmentDto = new DepartmentImpl();
    programDto = new ProgramImpl();

  }

  @Test
  public void testGetUserDto() {
    assertEquals(userDto, bizzFactory.getUserDto());
  }

  @Test
  public void testGetCountryDto() {
    assertEquals(countryDto, bizzFactory.getCountryDto());
  }

  @Test
  public void testGetPartnerDto() {
    assertEquals(partnerDto, bizzFactory.getPartnerDto());
  }

  @Test
  public void testGetMobilityDto() {
    assertEquals(mobilityDto, bizzFactory.getMobilityDto());
  }

  @Test
  public void testGetCancelationDto() {
    assertEquals(cancelationDto, bizzFactory.getCancelationDto());
  }

  @Test
  public void testGetDepartmentDto() {
    assertEquals(departmentDto, bizzFactory.getDepartmentDto());
  }

  @Test
  public void testGetProgramDto() {
    assertEquals(programDto, bizzFactory.getProgramDto());
  }
}
