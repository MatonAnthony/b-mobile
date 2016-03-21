package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.CancelationDao;

public class CancelationDaoImpl implements CancelationDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;


  public CancelationDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

}
