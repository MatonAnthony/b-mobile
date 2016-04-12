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
   * @param departements name of departements for witch you want his mobilities
   * 
   * @return an ArrayList of MobilityDto.
   */
  ArrayList<MobilityDto> getMobilitiesDepartements(String departements);

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

}
