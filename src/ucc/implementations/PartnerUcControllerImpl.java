package ucc.implementations;

import dal.DalServices;
import dao.interfaces.PartnerDao;
import dto.DepartmentDto;
import dto.PartnerDto;
import exceptions.MalformedIbanException;
import jdk.internal.org.xml.sax.SAXException;
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
  public void addPartner(PartnerDto partner, ArrayList<DepartmentDto> departments) throws SQLException {
    // TODO (Kamil) Throw les exceptions custom a la place de les catch
    try {
      dalServices.openConnection();
      dalServices.startTransaction();
      partnerDao.createPartner(partner, departments);
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
  public PartnerDto getPartnerById(int id) throws SQLException, MalformedIbanException {
    dalServices.openConnection();
    PartnerDto partner = partnerDao.getPartnerById(id);
    dalServices.closeConnection();
    return partner;
  }

  @Override
  public ArrayList<PartnerDto> getAllPartners() throws SQLException, MalformedIbanException {
    dalServices.openConnection();
    ArrayList<PartnerDto> partner = partnerDao.getAllPartners();
    dalServices.closeConnection();
    return partner;

  }

  @Override
  public void updatePartner(PartnerDto partner, ArrayList<DepartmentDto> departments) {

    try {
      dalServices.openConnection();
      dalServices.startTransaction();

      int rowUpdated = partnerDao.updatePartner(partner, departments);

      if (rowUpdated == 1) {
        dalServices.commitTransaction();
      } else {
        dalServices.rollbackTransaction();
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc1) {
        exc1.printStackTrace();
      }
    } finally {
      try {
        dalServices.closeConnection();
      } catch (SQLException exc) {
        exc.printStackTrace();
      }

    }
  }

  @Override
  public ArrayList<PartnerDto> getPartnersWithoutMobility() throws SQLException {
    dalServices.openConnection();
    ArrayList<PartnerDto> partner = partnerDao.getPartnersWithoutMobility();
    dalServices.closeConnection();
    return partner;
  }

  @Override
  public ArrayList<PartnerDto> getTeacherPartners() throws SQLException, MalformedIbanException {
    dalServices.openConnection();
    ArrayList<PartnerDto> partners = partnerDao.getTeacherPartners();
    dalServices.closeConnection();
    return partners;
  }

  @Override
  public ArrayList<PartnerDto> getDeletedPartners() throws SQLException, MalformedIbanException {
    dalServices.openConnection();
    ArrayList<PartnerDto> partners = partnerDao.getDeletedPartners();
    dalServices.closeConnection();
    return partners;
  }

  @Override
  public void changeDeletion(PartnerDto partnerDto) throws SQLException {
    try {
      dalServices.openConnection();
      dalServices.startTransaction();

      int rowUpdated = partnerDao.setDeleted(partnerDto);

      if (rowUpdated == 1) {
        dalServices.commitTransaction();
      } else {
        dalServices.rollbackTransaction();
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      try {
        dalServices.rollbackTransaction();
      } catch (SQLException exc1) {
        exc1.printStackTrace();
      }
    } finally {
      try {
        dalServices.closeConnection();
      } catch (SQLException exc) {
        exc.printStackTrace();
      }

    }

  }

  @Override
  public ArrayList<PartnerDto> getPartnersForStudentList(int userId) throws SQLException {
    dalServices.openConnection();
    ArrayList<PartnerDto> partners = partnerDao.getPartnersForStudentList(userId);
    dalServices.closeConnection();
    return partners;
  }

  @Override
  public ArrayList<DepartmentDto> getAllPartnerDepartments(int partnerId) throws SQLException {
    dalServices.openConnection();
    ArrayList<DepartmentDto> departments = partnerDao.getAllPartnerDepartments(partnerId);
    dalServices.closeConnection();
    return departments;
  }

}
