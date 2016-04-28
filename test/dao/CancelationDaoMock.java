package dao;

import dao.interfaces.CancelationDao;
import dto.CancelationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class CancelationDaoMock implements CancelationDao {

  private ArrayList<CancelationDto> db;

  public CancelationDaoMock(ArrayList<CancelationDto> list) {
    this.db = list;
  }

  @Override
  public ArrayList<CancelationDto> getAllReasonsOfTeacher() {
    return db;
  }

  @Override
  public int insertCancelation(CancelationDto cancelationDto) throws SQLException {
    return 2;
  }

}
