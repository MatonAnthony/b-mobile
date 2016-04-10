package dao.interfaces;

import dto.CountryDto;

import java.util.ArrayList;

public interface CountryDao {
  /**
   * Return all the countries of the database
   *
   * @return an ArrayList containing all the countries.
   */
  ArrayList<CountryDto> getAll();

  /**
   * Get a Country by his french name.
   *
   * @param name French name of the country.
   * @return Informations relative to the country you asked about.
   */
  CountryDto getCountryByNameFr(String name);

}
