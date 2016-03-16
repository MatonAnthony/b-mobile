/**
 * 
 */
package ucc;

import dal.DalServices;
import dao.CountryDao;
import dto.CountryDto;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Martin
 * @since 16 mars 2016
 */
public class CountryUcControllerImpl implements CountryUcController {

  private CountryDao countryDao;
  DalServices dalServices;


  public CountryUcControllerImpl(DalServices dalServices, CountryDao countryDao) {

    this.countryDao = countryDao;
    this.dalServices = dalServices;

  }

  @Override
  public ArrayList<CountryDto> getAllCountries() {
    ArrayList<CountryDto> countries = new ArrayList<CountryDto>();
    try {
      dalServices.startTransaction();
      countries = countryDao.getAll();
      dalServices.commitTransaction();
    } catch (Exception exc) {
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc1) {
        exc1.printStackTrace();
      }
    }
    return countries;
  }

}
