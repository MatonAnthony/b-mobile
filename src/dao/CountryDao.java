/**
 * 
 */
package dao;

import dto.CountryDto;

import java.util.ArrayList;

/**
 * @author Martin
 * @since 16 mars 2016
 */
public interface CountryDao {
  /**
   * Return all the countries of the database
   * 
   * @return an arraylist wich contains all the countries.
   */
  ArrayList<CountryDto> getAll();

}
