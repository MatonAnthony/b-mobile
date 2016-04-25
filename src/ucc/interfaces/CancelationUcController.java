package ucc.interfaces;

import dto.CancelationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CancelationUcController {

  /**
   * Gets all the previous entered reasons of the teacher for a cancellation.
   * 
   * @return ArrayList an Array list with all the reasons of the teacher.
   * @throws SQLException if an error occured in the database.
   */
  ArrayList<CancelationDto> getAllReasonsOfTeacher() throws SQLException;

  /**
   * Insert a new cancelation and returns its id.
   * 
   * @param cancelationDto the cancelation to insert in the database.
   * @return int The id of the new cancelation in the database.
   * @throws SQLException
   */
  int insertCancelation(CancelationDto cancelationDto) throws SQLException;

}
