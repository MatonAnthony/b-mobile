
package dao.interfaces;

import dto.DepartmentDto;
import exceptions.NoDepartmentException;

import java.util.ArrayList;

/**
 * The interface Department dao.
 */
public interface DepartmentDao {

  /**
   * Return a list of all the departments.
   *
   * @return a list containing all departments.
   */
  ArrayList<DepartmentDto> getAllDepartments();

  /**
   * Gets departement by id.
   *
   * @param id the id
   * @return the departement by id
   * @throws NoDepartmentException If there is no department matching with the id.
   */
  DepartmentDto getDepartementById(String id) throws NoDepartmentException;

  /**
   * Gets department by label.
   *
   * @param label the label
   * @return the department by label
   * @throws NoDepartmentException If there is no department matching with the label.
   */
  DepartmentDto getDepartmentByLabel(String label) throws NoDepartmentException;
}
