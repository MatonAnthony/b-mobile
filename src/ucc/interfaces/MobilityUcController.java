package ucc.interfaces;

import dto.MobilityDto;
import exceptions.BadMobilityStatusException;
import exceptions.MalformedIbanException;
import exceptions.NoMobilityException;
import exceptions.OptimisticLockException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MobilityUcController {

  /**
   * Return an ArrayList containing all the mobilities
   *
   * @return an ArrayList of MobilityDto.
   * @throws SQLException If there is a problem.
   */
  ArrayList<MobilityDto> getAllMobilities() throws SQLException;

  /**
   * Return an ArrayList containing all the mobilities of one departements stored in database.
   *
   * @return an ArrayList of MobilityDto.
   * @throws SQLException If there is a problem.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  ArrayList<MobilityDto> getMobilities() throws SQLException, MalformedIbanException;

  /**
   * Return an ArrayList containing all the confirmed mobilities stored in database.
   *
   * @return an ArrayList of MobilityDto.
   * @throws SQLException If there is a problem.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  ArrayList<MobilityDto> getConfirmedMobilities() throws SQLException, MalformedIbanException;

  /**
   * Return an ArrayList containing all the mobilities of one user stored in database.
   *
   * @param user pseudo of user who want to see his mobilities
   * @return an ArrayList of MobilityDto.
   * @throws SQLException If there is a problem.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  ArrayList<MobilityDto> getMyMobilities(String user) throws SQLException, MalformedIbanException;

  /**
   * Add a mobility to the database.
   *
   * @param mobility The mobilityDto to add.
   * @throws SQLException If there is a problem.
   */
  void addMobility(MobilityDto mobility);

  /**
   * Return an ArrayList containing all the academic years stored in database.
   *
   * @return an ArrayList with the academic years.
   * @throws SQLException If there is a problem.
   */
  ArrayList<String> getAcademicYears() throws SQLException;

  /**
   * Return an ArrayList containing all the payments matching with the academicYear.
   *
   * @return an ArrayList with the academic years.
   * @throws SQLException If there is a problem.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  ArrayList<MobilityDto> getFullPayments() throws SQLException, MalformedIbanException;

  /**
   * Return basic information of Mobility DTO based on an id.
   *
   * @param id Id of the mobility.
   * @return Mobility DTO.
   * @throws SQLException there is a problem.
   * @throws NoMobilityException If no mobility is matching with the id.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  MobilityDto getMobilityById(int id)
      throws SQLException, NoMobilityException, MalformedIbanException;

  /**
   * Cancels the mobility matching with the id.
   * 
   * @param idMobility the id of the mobility.
   * @param idCancelation the id of the cancellation.
   * @param verNr The version number before cancelation.
   * @throws SQLException if an error occurred with the database.
   * @throws OptimisticLockException if the mobility had been modified since the selection
   */
  void cancelMobility(int idMobility, int idCancelation, int verNr)
      throws SQLException, OptimisticLockException;

  /**
   * Update mobility for confirm partner.
   * 
   * @param mobilityDto mobility DTO.
   * @throws OptimisticLockException if the mobility had been modified since the selection
   */
  void confirmPartner(MobilityDto mobilityDto) throws OptimisticLockException;

  /**
   * Update the details for a mobility.
   * 
   * @param mobility the mobilityDto with the informations.
   * @throws BadMobilityStatusException if the status of the mobility is not correct
   * @throws OptimisticLockException if the mobility had been modified since the selection
   */
  void updateMobilityDetails(MobilityDto mobility)
      throws BadMobilityStatusException, OptimisticLockException;
}
