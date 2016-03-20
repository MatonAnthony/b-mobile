package ucc;

import dal.DalServices;
import dao.MobilityDao;
import dto.MobilityDto;

import java.util.ArrayList;

public class MobilityUcControllerImpl implements MobilityUcController {

  private DalServices dalServices = null;
  private MobilityDao mobilityDao = null;

  public MobilityUcControllerImpl(DalServices dalServices, MobilityDao mobilityDao) {

    this.mobilityDao = mobilityDao;
    this.dalServices = dalServices;

  }

  public ArrayList<MobilityDto> getAllMobilities() {
    ArrayList<MobilityDto> mobilities = mobilityDao.findAllMobility();
    return mobilities;
  }

  public ArrayList<MobilityDto> getconfirmedMobilities() {
    ArrayList<MobilityDto> mobilities = mobilityDao.findConfirmedMobility();
    return mobilities;
  }

}
