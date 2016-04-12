package dao.interfaces;

import dto.MobilityDto;

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
   * @param departements Name of departements for witch you want his mobilities
   * 
   * @return an ArrayList of MobilityDto.
   */

  ArrayList<MobilityDto> getFullMobilitiesDepartements(String departements);

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
   * 
   * @return an ArrayList of MobilityDto.
   */

  ArrayList<MobilityDto> getFullMyMobilities(String user);

}
