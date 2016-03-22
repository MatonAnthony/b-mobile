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

  /**
   * Create a new Use Case Controller for mobilities.
   * 
   * @param dalServices The dalServices used by the Use case controller.
   * @param mobilityDao The dao used by the Use case controller.
   */
  public MobilityUcControllerImpl(DalServices dalServices, MobilityDao mobilityDao) {
    this.mobilityDao = mobilityDao;
    this.dalServices = dalServices;

  }

  public ArrayList<MobilityDto> getAllMobilities() {
    ArrayList<MobilityDto> mobilities = mobilityDao.getAllMobilities();
    return mobilities;
  }

  public ArrayList<MobilityDto> getconfirmedMobilities() {
    ArrayList<MobilityDto> mobilities = mobilityDao.getFullConfirmedMobilities();
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
