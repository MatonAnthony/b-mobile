/**
 * 
 */
package ucc;

import dto.CountryDto;

import java.util.ArrayList;

/**
 * @author Martin
 * @since 16 mars 2016
 */
public interface CountryUcController {
  /**
   * Get all the countries of the database.
   * 
   * @return an ArrayList wich contains the countries of the database.
   */
  ArrayList<CountryDto> getAllCountries();



}
