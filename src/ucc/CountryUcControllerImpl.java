package ucc;

import dal.DalServices;
import dao.CountryDao;
import dto.CountryDto;

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
  public ArrayList<CountryDto> getAllCountries() {
    ArrayList<CountryDto> countries = new ArrayList<CountryDto>();
    countries = countryDao.getAll();
    return countries;
  }

}
