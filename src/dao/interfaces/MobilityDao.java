package dao.interfaces;

import dto.MobilityDto;

import java.util.ArrayList;

public interface MobilityDao {

  void createMobility(MobilityDto mobilityDto);

  void read();

  boolean update();

  boolean delete();

  ArrayList<MobilityDto> getAllMobilities();

  ArrayList<MobilityDto> getFullConfirmedMobilities();

}
