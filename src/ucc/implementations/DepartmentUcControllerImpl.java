
package ucc.implementations;

import dal.DalServices;
import dao.interfaces.DepartmentDao;
import dto.DepartmentDto;
import exceptions.NoDepartmentException;
import ucc.interfaces.DepartmentUcController;

import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentUcControllerImpl implements DepartmentUcController {

  private DepartmentDao departmentDao;
  private DalServices dalServices;

  public DepartmentUcControllerImpl(DalServices dalServices, DepartmentDao departmentDao) {
    this.departmentDao = departmentDao;
    this.dalServices = dalServices;
  }


  @Override
  public ArrayList<DepartmentDto> getAllDepartments() throws NoDepartmentException, SQLException {
    dalServices.openConnection();
    ArrayList<DepartmentDto> departments = departmentDao.getAllDepartments();
    dalServices.closeConnection();
    return departments;
  }

  public DepartmentDto getDepartementsById(String id) throws NoDepartmentException, SQLException {
    dalServices.openConnection();
    DepartmentDto department = departmentDao.getDepartementById(id);
    dalServices.closeConnection();
    return department;
  }

  @Override
  public DepartmentDto getDepartmentByLabel(String label)
      throws NoDepartmentException, SQLException {
    dalServices.openConnection();
    DepartmentDto department = departmentDao.getDepartmentByLabel(label);
    dalServices.closeConnection();
    return department;
  }

}
