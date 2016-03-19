
package dao;

import bizz.BizzFactory;
import dal.DalBackendServices;
import dto.DepartmentDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentDaoImpl implements DepartmentDao {


  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  public DepartmentDaoImpl(DalBackendServices dalBackendServices, BizzFactory factory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = factory;
  }

  @Override
  public ArrayList<DepartmentDto> getAllDepartments() {
    String query = "SELECT * FROM bmobile.departments";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      ArrayList<DepartmentDto> departments = new ArrayList<DepartmentDto>();
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          DepartmentDto departmentDto = factory.getDepartmentDto();
          departmentDto.setId(resultSet.getString(1));
          departmentDto.setLabel(resultSet.getString(2));
          departments.add(departmentDto);
        }
        return departments;
      } catch (SQLException exc2) {
        exc2.printStackTrace();
        return null;
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }

  }

}
