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



}
