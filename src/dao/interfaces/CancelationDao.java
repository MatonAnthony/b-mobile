package dao.interfaces;

import dto.CancelationDto;

import java.util.ArrayList;

public interface CancelationDao {

  /**
   * Gets all the previous entered reasons of the teacher for a cancellation.
   * 
   * @return an Array list with all the reasons of the teacher.
   */
  public ArrayList<CancelationDto> getAllReasonsOfTeacher();

}
