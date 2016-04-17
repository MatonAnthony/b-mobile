package ucc.interfaces;

import dto.CountryDto;
import exceptions.NoCountryException;

import java.sql.SQLException;
import java.util.ArrayList;


public interface CountryUcController {
  /**
   * Get all the countries of the database.
   * 
   * @return an ArrayList wich contains the countries of the database.
   */
  ArrayList<CountryDto> getAllCountries() throws NoCountryException, SQLException;


  /**
   * Get a country from the database.
   *
   * @param name The french name of the country.
   * @return CountryDto a CountryDto Object fill with the country information.
   */
  CountryDto getCountryByNameFr(String name) throws NoCountryException, SQLException;



}
