package dao.interfaces;

import dto.CountryDto;
import exceptions.NoCountryException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CountryDao {
  /**
   * Return all countries registered in our database.
   * 
   * @return a list all countries registered in our database.
   */
  ArrayList<CountryDto> getAll() throws SQLException;

  /**
   * Get a Country by his French name.
   *
   * @param name French name of the country.
   * @return Informations relative to the country you asked about.
   */
  CountryDto getCountryByNameFr(String name) throws SQLException, NoCountryException;

  /**
   * Get a Country by his ISO code.
   *
   * @param iso ISO Code of the country.
   * @return Informations relative to the country you asked about.
   */
  CountryDto getCountryByIso(String iso) throws SQLException, NoCountryException;
}
