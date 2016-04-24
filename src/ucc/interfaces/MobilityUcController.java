package ucc.interfaces;

import dto.MobilityDto;

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
   */
  ArrayList<MobilityDto> getMobilities() throws SQLException;

  /**
   * Return an ArrayList containing all the confirmed mobilities stored in database.
   *
   * @return an ArrayList of MobilityDto.
   * @throws SQLException If there is a problem.
   */
  ArrayList<MobilityDto> getConfirmedMobilities() throws SQLException;

  /**
   * Return an ArrayList containing all the mobilities of one user stored in database.
   *
   * @param user pseudo of user who want to see his mobilities
   * @return an ArrayList of MobilityDto.
   * @throws SQLException If there is a problem.
   */
  ArrayList<MobilityDto> getMyMobilities(String user) throws SQLException;

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
   * @param academicYear the academicYear for the selection of the payment.
   * @return an ArrayList with the academic years.
   * @throws SQLException If there is a problem.
   */
  ArrayList<MobilityDto> getFullPayments(String academicYear) throws SQLException;

  /**
   * Return basic information of Mobility DTO based on an id.
   *
   * @param id Nr Id of mobility.
   * @return Mobility DTO.
   * @throws SQLException Th there is a problem.
   */
  MobilityDto getMobilityById(int id) throws SQLException;

}
