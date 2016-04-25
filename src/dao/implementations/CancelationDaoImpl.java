package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.CancelationDao;
import dto.CancelationDto;
import exceptions.UnknowErrorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CancelationDaoImpl implements CancelationDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  private String query =
      "SELECT c.id, c.reason, c.responsible, c.ver_nr FROM bmobile.cancelations c ";

  /**
   * Instantiates a new Cancelation dao.
   *
   * @param dalBackendServices the dal backend services
   * @param bizzFactory the bizz factory
   */
  public CancelationDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  @Override
  public ArrayList<CancelationDto> getAllReasonsOfTeacher() {
    String queryTemp = query + "WHERE c.responsible = 'TEACHER'";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryTemp);
      ArrayList<CancelationDto> cancelations = new ArrayList<CancelationDto>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      while (resultSet.next()) {
        cancelations.add(fillDto(resultSet));
      }
      return cancelations;

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement des raisons d'annulation.");
    }

  }

  private CancelationDto fillDto(ResultSet resultSet) throws SQLException {
    CancelationDto cancelationDto = factory.getCancelationDto();
    cancelationDto.setId(resultSet.getInt(1));
    cancelationDto.setReason(resultSet.getString(2));
    cancelationDto.setResponsible(resultSet.getString(3));
    cancelationDto.setVerNr(resultSet.getInt(4));
    return cancelationDto;
  }

}
