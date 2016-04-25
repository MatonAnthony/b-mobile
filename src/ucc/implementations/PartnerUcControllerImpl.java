package ucc.implementations;

import dal.DalServices;
import dao.interfaces.PartnerDao;
import dto.PartnerDto;
import ucc.interfaces.PartnerUcController;

import java.sql.SQLException;
import java.util.ArrayList;

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
  public void addPartner(PartnerDto partner) throws SQLException {
    // TODO (Kamil) Throw les exceptions custom a la place de les catch
    try {
      dalServices.openConnection();
      dalServices.startTransaction();
      partnerDao.createPartner(partner);
      dalServices.commitTransaction();
      dalServices.closeConnection();
    } catch (SQLException exc1) {
      exc1.printStackTrace();
      try {
        dalServices.rollbackTransaction();
        dalServices.closeConnection();
      } catch (SQLException exc2) {
        exc2.printStackTrace();
      }
    }
  }

  @Override
  public ArrayList<PartnerDto> getPartnerMin(int userId, String permission) throws SQLException {
    dalServices.openConnection();
    ArrayList<PartnerDto> partners = partnerDao.getPartnersMin(userId, permission);
    dalServices.closeConnection();

    return partners;
  }

  @Override
  public PartnerDto getPartnerById(int id) throws SQLException {
    dalServices.openConnection();
    PartnerDto partner = partnerDao.getPartnerById(id);
    dalServices.closeConnection();
    return partner;
  }
}
