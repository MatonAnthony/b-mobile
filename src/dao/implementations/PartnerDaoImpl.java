package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.PartnerDao;
import dto.PartnerDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartnerDaoImpl implements PartnerDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  public PartnerDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  /**
   * Add partner to DB.
   *
   * @param partner DTO partner
   */
  public void createPartner(PartnerDto partner) {
    String query = "INSERT INTO bmobile.partners VALUES "
        + "(DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NULL,0)";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, partner.getUserDto().getId());
      preparedStatement.setString(2, partner.getLegalName());
      preparedStatement.setString(3, partner.getBusiness());
      preparedStatement.setString(4, partner.getFullName());
      preparedStatement.setString(5, partner.getDepartment());
      preparedStatement.setString(6, partner.getType());
      preparedStatement.setInt(7, partner.getNbEmployees());
      preparedStatement.setString(8, partner.getStreet());
      preparedStatement.setString(9, partner.getNumber());
      preparedStatement.setString(10, partner.getMailbox());
      preparedStatement.setString(11, partner.getZip());
      preparedStatement.setString(12, partner.getCity());
      preparedStatement.setString(13, partner.getState());
      preparedStatement.setString(14, partner.getCountryDto().getIso());
      preparedStatement.setString(15, partner.getTel());
      preparedStatement.setString(16, partner.getEmail());
      preparedStatement.setString(17, partner.getWebsite());
      dalBackendServices.executeUpdate(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }
}