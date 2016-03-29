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

  CountryDto getCountryByNameFr(String name);

}
