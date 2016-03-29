
package ucc.interfaces;

import dto.DepartmentDto;

import java.util.ArrayList;

public interface DepartmentUcController {

  /**
   * Return all the departments.
   * 
   * @return An arrayList with dto of all the departments.
   */
  public ArrayList<DepartmentDto> getAllDepartments();

  /**
   * Get a department from the database.
   *
   * @param id Id of the department.
   * @return DepartmentDto a DepartmentDto Object filled with the department information.
   */
  public DepartmentDto getDepartementsById(String id);

  /**
   * Get a department from the database.
   *
   * @param label Name of the department.
   * @return DepartmentDto a DepartmentDto Object filled with the department information.
   */
  public DepartmentDto getDepartmentByLabel(String label);

}
