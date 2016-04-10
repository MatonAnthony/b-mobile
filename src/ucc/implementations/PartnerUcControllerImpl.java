package ucc.implementations;

import dal.DalServices;
import dao.interfaces.PartnerDao;
import dto.PartnerDto;
import ucc.interfaces.PartnerUcController;

import java.sql.SQLException;

public class PartnerUcControllerImpl implements PartnerUcController {

  private PartnerDao partnerDao;
  private DalServices dalServices;

  /**
   * The constructor of the Partner use case controller.
   * 
   * @param dalServices The dalServices that the ucc will use.
   * @param partnerDao The dao that the ucc will use.
   */
  public PartnerUcControllerImpl(DalServices dalServices, PartnerDao partnerDao) {

    this.partnerDao = partnerDao;
    this.dalServices = dalServices;

  }

  @Override
  public void addPartner(PartnerDto partner) {
    try {
      dalServices.startTransaction();
      partnerDao.createPartner(partner);
      dalServices.commitTransaction();
    } catch (SQLException exc1) {
      exc1.printStackTrace();
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc2) {
        exc2.printStackTrace();
      }
    }
  }
}
