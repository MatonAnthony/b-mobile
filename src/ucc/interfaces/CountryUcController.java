package ucc.interfaces;

import dto.CountryDto;

import java.util.ArrayList;


public interface CountryUcController {
  /**
   * Get all the countries of the database.
   * 
   * @return an ArrayList wich contains the countries of the database.
   */
  ArrayList<CountryDto> getAllCountries();


  /**
   * Get a country from the database.
   *
   * @param name The french name of the country.
   * @return CountryDto a CountryDto Object fill with the country information.
   */
  CountryDto getCountryByNameFr(String name);



}
