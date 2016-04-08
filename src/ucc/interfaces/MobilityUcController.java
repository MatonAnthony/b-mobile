package ucc.interfaces;

import dto.MobilityDto;

import java.util.ArrayList;

public interface MobilityUcController {

  ArrayList<MobilityDto> getAllMobilities();

  ArrayList<MobilityDto> getConfirmedMobilities();

  /**
   * Return an ArrayList containing all the mobilities of one user stored in database.
   * 
   * @param pseudo of user who want to see his mobilities
   * 
   * @return an ArrayList of MobilityDto.
   */
  ArrayList<MobilityDto> getMyMobilities(String user);

  void addMobility(MobilityDto mobility);

}
