
package ucc;

import dto.DepartmentDto;

import java.util.ArrayList;

public interface DepartmentUcController {

  /**
   * Return all the departments.
   * 
   * @return An arrayList with dto of all the departments.
   */
  public ArrayList<DepartmentDto> getAllDepartments();

  public DepartmentDto getDepartementsById(String id);

}
