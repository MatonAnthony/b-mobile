package ucc.implementations;

import dal.DalServices;
import dao.interfaces.CountryDao;
import dto.CountryDto;
import exceptions.NoCountryException;
import ucc.interfaces.CountryUcController;

import java.sql.SQLException;
import java.util.ArrayList;

public class CountryUcControllerImpl implements CountryUcController {

  private CountryDao countryDao;
  private DalServices dalServices;

  /**
   * The constructor of the Country use case controller.
   * 
   * @param dalServices The dalServices that the ucc will use.
   * @param countryDao The dao that the ucc will use.
   */
  public CountryUcControllerImpl(DalServices dalServices, CountryDao countryDao) {
    this.countryDao = countryDao;
    this.dalServices = dalServices;

  }

  @Override
  public ArrayList<CountryDto> getAllCountries() throws SQLException {
    dalServices.openConnection();
    ArrayList<CountryDto> countries = countryDao.getAll();
    dalServices.closeConnection();
    return countries;
  }

  @Override
  public CountryDto getCountryByNameFr(String name) throws NoCountryException, SQLException {
    dalServices.openConnection();
    CountryDto country = countryDao.getCountryByNameFr(name);
    dalServices.closeConnection();
    return country;
  }

  @Override
  public CountryDto getCountryByIso(String iso) throws NoCountryException, SQLException {
    dalServices.openConnection();
    CountryDto country = countryDao.getCountryByIso(iso);
    dalServices.closeConnection();
    return country;
  }
}
