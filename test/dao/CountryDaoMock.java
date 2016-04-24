package dao;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.CountryDao;
import dto.CountryDto;
import exceptions.NoCountryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryDaoMock implements CountryDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  /**
   * Instantiates a new Country dao.
   *
   * @param dalBackendServices the dal backend services
   * @param bizzFactory the bizz factory
   */
  public CountryDaoMock(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  /**
   * Return all countries registered in our database.
   * 
   * @return a list all countries registered in our database.
   */
  @Override
  public ArrayList<CountryDto> getAll() throws SQLException {
    ArrayList<CountryDto> countries = new ArrayList<CountryDto>();
    countries.add(factory.getCountryDto());
    countries.add(factory.getCountryDto());
    return countries;
  }

  /**
   * Get a Country by his french name.
   *
   * @param name French name of the country.
   * @return Informations relative to the country you asked about.
   */
  @Override
  public CountryDto getCountryByNameFr(String name) throws SQLException, NoCountryException {
    // Todo
    return null;
  }

  private CountryDto fillDto(ResultSet resultSet) throws NoCountryException {
    // Todo
    return null;
  }

  private ArrayList<CountryDto> fillDtoArray(ResultSet resultSet) {
    // Todo
    return null;
  }

  /**
   * Get a Country by his ISO code.
   *
   * @param iso ISO Code of the country.
   * @return Informations relative to the country you asked about.
   */
  @Override
  public CountryDto getCountryByIso(String iso) throws SQLException, NoCountryException {
    // Todo
    return null;
  }
}
