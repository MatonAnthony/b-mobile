
package ucc;

import static org.junit.Assert.assertEquals;

import bizz.implementations.BizzFactoryImpl;
import bizz.interfaces.BizzFactory;
import dal.DalServices;
import dal.DalServicesImpl;
import dao.CountryDaoMock;
import dto.CountryDto;
import exceptions.NoCountryException;
import ucc.implementations.CountryUcControllerImpl;
import ucc.interfaces.CountryUcController;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class CountryUcControllerImplTest {

  private CountryDaoMock countryDao;
  private ArrayList<CountryDto> listPays;
  private CountryDto countryDto;
  private CountryUcController countryUcController;

  @Before
  public void setUp() throws Exception {
    // TODO (Martin) Implementer les tests
    BizzFactory bizzFactory = new BizzFactoryImpl();
    countryDto = bizzFactory.getCountryDto();
    countryDto.setNameFr("Belgique");
    countryDto.setNameEn("Belgium");
    countryDto.setIso("BE");
    countryDto.setIdProgram(1);
    CountryDto countryDto2 = bizzFactory.getCountryDto();
    countryDto2.setNameFr("France");
    countryDto2.setNameEn("France");
    countryDto2.setIso("FR");
    countryDto2.setIdProgram(1);
    listPays = new ArrayList<CountryDto>();
    listPays.add(countryDto);
    listPays.add(countryDto2);

    countryDao = new CountryDaoMock(listPays);
    DalServices dalServices = new DalServicesImpl();
    countryUcController = new CountryUcControllerImpl(dalServices, countryDao);
  }

  @Test
  public void testGetAllCountries() throws SQLException {
    assertEquals(listPays, countryUcController.getAllCountries());
  }

  @Test
  public void testGetCountryByNameFr() throws NoCountryException, SQLException {
    assertEquals(countryDto, countryUcController.getCountryByNameFr(countryDto.getNameFr()));
  }

  @Test(expected = NoCountryException.class)
  public void testGetCountryByNameFr2() throws NoCountryException, SQLException {
    countryUcController.getCountryByNameFr("Luxembourg");
  }

  @Test
  public void testGetCountryByIso() throws NoCountryException, SQLException {
    assertEquals(countryDto, countryUcController.getCountryByIso(countryDto.getIso()));
  }

  @Test(expected = NoCountryException.class)
  public void testGetCountryByIso2() throws NoCountryException, SQLException {
    countryUcController.getCountryByIso("GT");
  }



}
