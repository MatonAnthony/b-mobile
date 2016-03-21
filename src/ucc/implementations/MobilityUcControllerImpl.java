package ucc.implementations;

import dal.DalServices;
import dao.interfaces.MobilityDao;
import dto.MobilityDto;
import ucc.interfaces.MobilityUcController;

import java.sql.SQLException;
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

  @Override
  public void addMobility(MobilityDto mobility) {
    try {
      dalServices.startTransaction();
      mobilityDao.createMobility(mobility);

      dalServices.commitTransaction();
    } catch (SQLException exc) {
      exc.printStackTrace();
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc1) {
        exc1.printStackTrace();
      }
    }

  }

}
