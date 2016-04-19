
package ucc.interfaces;

import dto.DepartmentDto;
import exceptions.NoDepartmentException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DepartmentUcController {

  /**
   * Return all the departments.
   * 
   * @return An arrayList with dto of all the departments.
   * @throws SQLException If there is an exception.
   */
  public ArrayList<DepartmentDto> getAllDepartments() throws NoDepartmentException, SQLException;

  /**
   * Get a department from the database.
   *
   * @param id Id of the department.
   * @return DepartmentDto a DepartmentDto Object filled with the department information.
   * @throws SQLException If there is an exception.
   * @throws NoDepartmentException If there is no department for this id.
   */
  public DepartmentDto getDepartementsById(String id) throws NoDepartmentException, SQLException;

  /**
   * Get a department from the database.
   *
   * @param label Name of the department.
   * @return DepartmentDto a DepartmentDto Object filled with the department information.
   * @throws SQLException If there is an exception.
   */
  public DepartmentDto getDepartmentByLabel(String label)
      throws NoDepartmentException, SQLException;

}
