package dao.interfaces;

import dto.CancelationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CancelationDao {

  /**
   * Gets all the previous entered reasons of the teacher for a cancellation.
   * 
   * @return an Array list with all the reasons of the teacher.
   */
  public ArrayList<CancelationDto> getAllReasonsOfTeacher();

  /**
   * Insert a new cancelation and returns its id.
   * 
   * @param cancelationDto the cancelation to insert in the database.
   * @return The id of the new cancelation in the database.
   * @throws SQLException if an error occured in the database.
   */
  public int insertCancelation(CancelationDto cancelationDto) throws SQLException;

}
