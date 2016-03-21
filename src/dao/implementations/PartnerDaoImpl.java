package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.PartnerDao;

public class PartnerDaoImpl implements PartnerDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  public PartnerDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

}
