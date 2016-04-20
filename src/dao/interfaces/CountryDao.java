package dao.interfaces;

import dto.CountryDto;
import exceptions.NoCountryException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CountryDao {
  /**
   * Return all the countries of the database
   *
   * @return an ArrayList containing all the countries.
   */
  ArrayList<CountryDto> getAll() throws SQLException, NoCountryException;

  /**
   * Get a Country by his french name.
   *
   * @param name French name of the country.
   * @return Informations relative to the country you asked about.
   */
  CountryDto getCountryByNameFr(String name) throws SQLException, NoCountryException;

  CountryDto getCountryByIso(String iso) throws SQLException, NoCountryException;
}
