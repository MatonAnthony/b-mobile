
package ucc;

import dto.DepartmentDto;

import java.util.ArrayList;

public interface DepartmentUcController {

  /**
   * Return all the departments.
   * 
   * @return An arrayList with dto of all the departments.
   */
  ArrayList<DepartmentDto> getAllDepartments();

}
