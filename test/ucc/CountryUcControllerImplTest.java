
package ucc;

import static org.junit.Assert.assertEquals;

import bizz.implementations.BizzFactoryImpl;
import bizz.interfaces.BizzFactory;
import dao.CountryDaoMock;
import dto.CountryDto;
import exceptions.NoCountryException;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class CountryUcControllerImplTest {

  private CountryDaoMock countryDao;
  private ArrayList<CountryDto> listPays;
  CountryDto countryDto;

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
  }

  @Test
  public void testGetAllCountries() throws SQLException {
    assertEquals(listPays, countryDao.getAll());
  }

  @Test
  public void testGetCountryByNameFr() throws NoCountryException {
    assertEquals(countryDto, countryDao.getCountryByNameFr(countryDto.getNameFr()));
  }

  @Test(expected = NoCountryException.class)
  public void testGetCountryByNameFr2() throws NoCountryException {
    countryDao.getCountryByNameFr("Luxembourg");
  }

  @Test
  public void testGetCountryByIso() throws NoCountryException {
    assertEquals(countryDto, countryDao.getCountryByIso(countryDto.getIso()));
  }

  @Test(expected = NoCountryException.class)
  public void testGetCountryByIso2() throws NoCountryException {
    countryDao.getCountryByIso("GT");
  }



}
