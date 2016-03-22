package dao.interfaces;

import dto.MobilityDto;

import java.util.ArrayList;

public interface MobilityDao {

  void createMobility(MobilityDto mobilityDto);

  void read();

  boolean update();

  boolean delete();

  /**
   * Return an arraylist of all the mobilities in the database.
   * 
   * @return an arrayList of MobilityDto.
   */
  ArrayList<MobilityDto> getAllMobilities();

  /**
   * Return an arraylist of all the confirmed mobilities in the database.
   * 
   * @return an arraylist of MobilityDto.
   */
  ArrayList<MobilityDto> getFullConfirmedMobilities();

}
