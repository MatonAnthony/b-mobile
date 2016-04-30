package dao.interfaces;

import dto.MobilityDto;
import exceptions.NoMobilityException;

import java.util.ArrayList;

public interface MobilityDao {

  void createMobility(MobilityDto mobilityDto);

  /**
   * Return an ArrayList containing all the mobilities stored in database.
   *
   * @return an ArrayList of MobilityDto.
   */
  ArrayList<MobilityDto> getAllMobilities();

  /**
   * Return an ArrayList containing all the mobilities of one departements stored in database.
   *
   * @return an ArrayList of MobilityDto.
   */

  ArrayList<MobilityDto> getFullMobilities();

  /**
   * Return an ArrayList containing all the confirmed mobilities stored in database.
   *
   * @return an ArrayList of MobilityDto.
   */
  ArrayList<MobilityDto> getFullConfirmedMobilities();

  /**
   * Return an ArrayList containing all the mobilities of one user stored in database.
   *
   * @param user Pseudo of user who want to see his mobilities
   * @return an ArrayList of MobilityDto.
   */

  ArrayList<MobilityDto> getFullMyMobilities(String user);

  /**
   * Return an ArrayList containing all the academic years stored in database.
   *
   * @return an ArrayList with the academic years.
   */
  ArrayList<String> getAllAcademicYears();

  /**
   * Return an ArrayList containing all the payments matching with the academicYear.
   *
   * @return an ArrayList with the academic years.
   */
  ArrayList<MobilityDto> getFullPayments();

  /**
   * Return basic information of Mobility DTO based on an id.
   *
   * @param id Nr Id of mobility.
   * @return Mobility DTO.
   * @throws NoMobilityException If no mobility is matching with the id.
   */
  MobilityDto getMobilityById(int id) throws NoMobilityException;

  /**
   * Cancels the mobility matching with the id.
   * 
   * @param idMobility the id of the mobility.
   * @param verNr The version number before cancelation.
   * @param idCancelation the id of the cancellation.
   */
  void cancelMobility(int idMobility, int idCancelation, int verNr);

  /**
   * Update mobility to join whith the partner id.
   * 
   * @param mobilityDto mobility DTO.
   */
  void confirmPartner(MobilityDto mobilityDto);

  /**
   * Update the mobility details in the Database whith the informations in the dto.
   * 
   * @param mobility the dto with the informations.
   * @return the number of rows modified
   */
  int updateMobilityDetails(MobilityDto mobility);
}
