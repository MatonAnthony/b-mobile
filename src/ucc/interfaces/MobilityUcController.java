package ucc.interfaces;

import dto.MobilityDto;

import java.util.ArrayList;

public interface MobilityUcController {

  /**
   * Return an ArrayList containing all the mobilities
   * 
   * @return an ArrayList of MobilityDto.
   */
  ArrayList<MobilityDto> getAllMobilities();

  /**
   * Return an ArrayList containing all the mobilities of one departements stored in database.
   * 
   * @return an ArrayList of MobilityDto.
   */
  ArrayList<MobilityDto> getMobilities();

  /**
   * Return an ArrayList containing all the confirmed mobilities stored in database.
   *
   * @return an ArrayList of MobilityDto.
   */
  ArrayList<MobilityDto> getConfirmedMobilities();

  /**
   * Return an ArrayList containing all the mobilities of one user stored in database.
   * 
   * @param user pseudo of user who want to see his mobilities
   * 
   * @return an ArrayList of MobilityDto.
   */
  ArrayList<MobilityDto> getMyMobilities(String user);

  void addMobility(MobilityDto mobility);

  /**
   * Return an ArrayList containing all the academic years stored in database.
   * 
   * @return an ArrayList with the academic years.
   */
  ArrayList<String> getAcademicYears();

  /**
   * Return an ArrayList containing all the payments matching with the academicYear.
   * 
   * @param the academicYear for the selection of the payment.
   * @return an ArrayList with the academic years.
   */
  ArrayList<MobilityDto> getFullPayments(String academicYear);

}
