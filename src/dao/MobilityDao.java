package dao;

import dto.MobilityDto;

public interface MobilityDao {

  boolean createMobility(MobilityDto mobilityDto);

  void read();

  boolean update();

  boolean delete();

}
