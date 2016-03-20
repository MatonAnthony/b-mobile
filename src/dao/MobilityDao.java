package dao;

import dto.MobilityDto;

import java.util.ArrayList;

public interface MobilityDao {

  boolean createMobility(MobilityDto mobilityDto);

  void read();

  boolean update();

  boolean delete();

  ArrayList<MobilityDto> findAllMobility();

  ArrayList<MobilityDto> findConfirmedMobility();

}
