package bizz.implementations;

import static org.junit.Assert.assertEquals;

import bizz.interfaces.BizzFactory;

import org.junit.Before;
import org.junit.Test;


public class PartnerImplTest {

  private PartnerImpl puppet;
  private BizzFactory bizz;

  @Before
  public void setUp() throws Exception {
    puppet = new PartnerImpl();
    bizz = new BizzFactoryImpl();
    puppet.setId(0);
    puppet.setIdUser(0);
    puppet.setUserDto(null); // Todo with Mock
    puppet.setLegalName("legal name");
    puppet.setBusiness("business");
    puppet.setFullName("full name");
    puppet.setDepartment("BIN");
    puppet.setType("Erabel");
    puppet.setNbEmployees(0);
    puppet.setStreet("street");
    puppet.setNumber("0");
    puppet.setMailbox("0");
    puppet.setZip("0");
    puppet.setCity("city");
    puppet.setState("state");
    puppet.setTel("0");
    puppet.setCountry("country");
    puppet.setCountryDto(null); // TODO with Mock
    puppet.setEmail("email@email.be");
    puppet.setWebsite("http://www.web.com");
    puppet.setExists(false);
    puppet.setVerNr(0);
    puppet.setCountryDto(bizz.getCountryDto());
    puppet.setUserDto(bizz.getUserDto());
  }

  @Test
  public void testGetId() throws Exception {
    assertEquals(puppet.getId(), 0);
  }

  @Test
  public void testSetId() throws Exception {
    puppet.setId(1);
    assertEquals(puppet.getId(), 1);
  }

  @Test
  public void testGetIdUser() throws Exception {
    assertEquals(puppet.getIdUser(), 0);
  }

  @Test
  public void testSetIdUser() throws Exception {
    puppet.setIdUser(1);
    assertEquals(puppet.getIdUser(), 1);
  }

  @Test
  public void testGetLegalName() throws Exception {
    assertEquals(puppet.getLegalName(), "legal name");
  }

  @Test
  public void testSetLegalName() throws Exception {
    puppet.setLegalName("--");
    assertEquals(puppet.getLegalName(), "--");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetLegalName2() throws Exception {
    puppet.setLegalName("--@");
    assertEquals(puppet.getLegalName(), "--@");
  }

  @Test
  public void testSetLegalName3() throws Exception {
    puppet.setLegalName(null);
    assertEquals(puppet.getLegalName(), null);
  }

  @Test
  public void testGetBusiness() throws Exception {
    assertEquals(puppet.getBusiness(), "business");
  }

  @Test
  public void testSetBusiness() throws Exception {
    puppet.setBusiness("-");
    assertEquals(puppet.getBusiness(), "-");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBusiness2() throws Exception {
    puppet.setBusiness("-@");
    assertEquals(puppet.getBusiness(), "-@");
  }

  @Test
  public void testSetBusiness3() throws Exception {
    puppet.setBusiness(null);
    assertEquals(puppet.getBusiness(), null);
  }


  @Test
  public void testGetFullName() throws Exception {
    assertEquals(puppet.getFullName(), "full name");
  }

  @Test
  public void testSetFullName() throws Exception {
    puppet.setFullName("-");
    assertEquals(puppet.getFullName(), "-");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFullName2() throws Exception {
    puppet.setFullName("-@");
    assertEquals(puppet.getFullName(), "-@");
  }

  @Test
  public void testSetFullName3() throws Exception {
    puppet.setFullName(null);
    assertEquals(puppet.getFullName(), null);
  }

  @Test
  public void testGetDepartment() throws Exception {
    assertEquals(puppet.getDepartment(), "BIN");
  }

  @Test
  public void testSetDepartment() throws Exception {
    puppet.setDepartment("BIM");
    assertEquals(puppet.getDepartment(), "BIM");
  }

  @Test
  public void testGetType() throws Exception {
    assertEquals(puppet.getType(), "Erabel");
  }

  @Test
  public void testSetType() throws Exception {
    puppet.setType("Erasmus");
    assertEquals(puppet.getType(), "Erasmus");
  }

  @Test
  public void testGetNbEmployees() throws Exception {
    assertEquals(puppet.getNbEmployees(), 0);
  }

  @Test
  public void testSetNbEmployees() throws Exception {
    puppet.setNbEmployees(1);
    assertEquals(puppet.getNbEmployees(), 1);
  }

  @Test
  public void testGetStreet() throws Exception {
    assertEquals(puppet.getStreet(), "street");
  }

  @Test
  public void testSetStreet() throws Exception {
    puppet.setStreet("fighter");
    assertEquals(puppet.getStreet(), "fighter");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStreet2() throws Exception {
    puppet.setStreet("fighter@");
    assertEquals(puppet.getStreet(), "fighter@");
  }

  @Test
  public void testGetNumber() throws Exception {
    assertEquals(puppet.getNumber(), "0");
  }

  @Test
  public void testSetNumber() throws Exception {
    puppet.setNumber("3A");
    assertEquals(puppet.getNumber(), "3A");
  }

  @Test
  public void testGetMailbox() throws Exception {
    assertEquals(puppet.getMailbox(), "0");
  }

  @Test
  public void testSetMailbox() throws Exception {
    puppet.setMailbox("A");
    assertEquals(puppet.getMailbox(), "A");
  }

  @Test
  public void testGetZip() throws Exception {
    assertEquals(puppet.getZip(), "0");
  }

  @Test
  public void testSetZip() throws Exception {
    puppet.setZip("13000 CEDEX");
    assertEquals(puppet.getZip(), "13000 CEDEX");
  }

  @Test
  public void testGetCity() throws Exception {
    assertEquals(puppet.getCity(), "city");
  }

  @Test
  public void testSetCity() throws Exception {
    puppet.setCity("hunter");
    assertEquals(puppet.getCity(), "hunter");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCity2() throws Exception {
    puppet.setCity("hunter@");
    assertEquals(puppet.getCity(), "hunter@");
  }

  @Test
  public void testSetCity3() throws Exception {
    puppet.setCity(null);
    assertEquals(puppet.getCity(), null);
  }

  @Test
  public void testGetState() throws Exception {
    assertEquals(puppet.getState(), "state");
  }

  @Test
  public void testSetState() throws Exception {
    puppet.setState("California");
    assertEquals(puppet.getState(), "California");
  }

  @Test
  public void testGetTel() throws Exception {
    assertEquals(puppet.getTel(), "0");
  }

  @Test
  public void testSetTel() throws Exception {
    puppet.setTel("1");
    assertEquals(puppet.getTel(), "1");
  }

  @Test
  public void testGetCountryDto() throws Exception {
    assertEquals(bizz.getCountryDto(), puppet.getCountryDto());
  }

  @Test
  public void testSetCountryDto() throws Exception {
    puppet.setCountryDto(bizz.getCountryDto());
  }

  @Test
  public void testGetCountry() throws Exception {
    assertEquals(puppet.getCountry(), "country");
  }

  @Test
  public void testSetCountry() throws Exception {
    puppet.setCountry("Nashville");
    assertEquals(puppet.getCountry(), "Nashville");
  }

  @Test
  public void testGetEmail() throws Exception {
    assertEquals(puppet.getEmail(), "email@email.be");
  }

  @Test
  public void testSetEmail() throws Exception {
    puppet.setEmail("xxx@yyy.zzz");
    assertEquals(puppet.getEmail(), "xxx@yyy.zzz");
  }

  @Test
  public void testSetEmail3() throws Exception {
    puppet.setEmail(null);
    assertEquals(puppet.getEmail(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetEmail2() throws Exception {
    puppet.setEmail("xxx@.zzz");
    assertEquals(puppet.getEmail(), "xxx@.zzz");
  }

  @Test
  public void testGetWebsite() throws Exception {
    assertEquals(puppet.getWebsite(), "http://www.web.com");
  }

  @Test
  public void testSetWebsite() throws Exception {
    puppet.setWebsite("studentittools.ipl.be");
    assertEquals(puppet.getWebsite(), "studentittools.ipl.be");
  }

  @Test
  public void testSetWebsite2() throws Exception {
    puppet.setWebsite(null);
    assertEquals(null, puppet.getWebsite());
  }

  @Test
  public void testIsExists() throws Exception {
    assertEquals(puppet.isExists(), false);
  }

  @Test
  public void testSetExists() throws Exception {
    puppet.setExists(true);
    assertEquals(puppet.isExists(), true);
  }

  @Test
  public void testGetVerNr() throws Exception {
    assertEquals(puppet.getVerNr(), 0);
  }

  @Test
  public void testSetVerNr() throws Exception {
    puppet.setVerNr(1);
    assertEquals(puppet.getVerNr(), 1);
  }

  @Test
  public void testGetUserDto() {
    assertEquals(bizz.getUserDto(), puppet.getUserDto());
  }

  @Test
  public void testSetUserDto() throws Exception {
    puppet.setUserDto(bizz.getUserDto());
  }

}
