package ucc.implementations;

import dal.DalServices;
import dao.interfaces.CancelationDao;
import ucc.interfaces.CancelationUcController;

public class CancelationUcControllerImpl implements CancelationUcController {

  private CancelationDao cancelationDao;
  private DalServices dalServices;

  /**
   * The constructor of the Cancelation use case controller.
   * 
   * @param dalServices The dalServices that the ucc will use.
   * @param cancelationDao The dao that the ucc will use.
   */
  public CancelationUcControllerImpl(DalServices dalServices, CancelationDao cancelationDao) {

    this.cancelationDao = cancelationDao;
    this.dalServices = dalServices;

  }

}
