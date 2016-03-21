package ucc.implementations;

import dal.DalServices;
import dao.interfaces.MobilityDao;
import dto.MobilityDto;
import ucc.interfaces.MobilityUcController;

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
    ArrayList<MobilityDto> mobilities = mobilityDao.findFullConfirmedMobility();
    return mobilities;
  }

}
