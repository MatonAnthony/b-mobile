package ucc;

import dto.MobilityDto;

import java.util.ArrayList;

public interface MobilityUcController {

  ArrayList<MobilityDto> getAllMobilities();

  ArrayList<MobilityDto> getconfirmedMobilities();

}
