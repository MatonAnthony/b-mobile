package ucc.interfaces;

import dto.MobilityDto;

import java.util.ArrayList;

public interface MobilityUcController {

  ArrayList<MobilityDto> getAllMobilities();

  ArrayList<MobilityDto> getConfirmedMobilities();

  void addMobility(MobilityDto mobility);

}
