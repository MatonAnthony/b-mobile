package ucc.implementations;

import dal.DalServices;
import dao.interfaces.CancelationDao;
import dto.CancelationDto;
import ucc.interfaces.CancelationUcController;

import java.sql.SQLException;
import java.util.ArrayList;

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

  @Override
  public ArrayList<CancelationDto> getAllReasonsOfTeacher() throws SQLException {
    dalServices.openConnection();
    ArrayList<CancelationDto> cancelations = cancelationDao.getAllReasonsOfTeacher();
    dalServices.closeConnection();
    return cancelations;
  }

  @Override
  public int insertCancelation(CancelationDto cancelationDto) throws SQLException {
    dalServices.openConnection();
    dalServices.startTransaction();
    int cancelationId = cancelationDao.insertCancelation(cancelationDto);
    dalServices.commitTransaction();
    dalServices.closeConnection();
    return cancelationId;
  }

}
