
package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.DepartmentDao;
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
    String query = "SELECT id, label, ver_nr FROM bmobile.departments";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return fillDtoArray(preparedStatement);
      } catch (SQLException exc2) {
        exc2.printStackTrace();
        return null;
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }

  }

  @Override
  public DepartmentDto getDepartementById(String id) {
    String query = "SELECT id, label, ver_nr FROM bmobile.departments WHERE id = ?";

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, id);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return fillDto(preparedStatement);
      } catch (SQLException exc2) {
        exc2.printStackTrace();
        return null;
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }

  }

  @Override
  public DepartmentDto getDepartmentByLabel(String label) {
    String query = "SELECT id, label, ver_nr FROM bmobile.departments WHERE label=?";

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, label);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        return fillDto(preparedStatement);
      } catch (SQLException exc2) {
        exc2.printStackTrace();
        return null;
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }

  }

  private DepartmentDto fillDto(PreparedStatement preparedStatement) {
    DepartmentDto departmentDto = factory.getDepartmentDto();
    try (ResultSet resultSet = preparedStatement.executeQuery()) {
      if (resultSet.next()) {
        departmentDto.setId(resultSet.getString(1));
        departmentDto.setLabel(resultSet.getString(2));
        departmentDto.setVerNr(resultSet.getInt(3));
      } else {
        return null;
      }
      return departmentDto;
    } catch (SQLException exc2) {
      exc2.printStackTrace();
      return null;
    }


  }

  private ArrayList<DepartmentDto> fillDtoArray(PreparedStatement preparedStatement) {
    ArrayList<DepartmentDto> departments = new ArrayList<DepartmentDto>();
    try (ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        DepartmentDto departmentDto = factory.getDepartmentDto();
        departmentDto.setId(resultSet.getString(1));
        departmentDto.setLabel(resultSet.getString(2));
        departmentDto.setVerNr(resultSet.getInt(3));
        departments.add(departmentDto);
      }
      return departments;
    } catch (SQLException exc2) {
      exc2.printStackTrace();
      return null;
    }


  }

}
