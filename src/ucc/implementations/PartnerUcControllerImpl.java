package ucc.implementations;

import dal.DalServices;
import dao.interfaces.PartnerDao;
import ucc.interfaces.PartnerUcController;

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

}
