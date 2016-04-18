package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.DepartmentDao;
import dto.DepartmentDto;
import exceptions.NoDepartmentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentDaoImpl implements DepartmentDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  /**
   * Instantiates a new Department dao.
   *
   * @param dalBackendServices the dal backend services
   * @param factory            the factory
   */
  public DepartmentDaoImpl(DalBackendServices dalBackendServices, BizzFactory factory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = factory;
  }

  @Override
  public ArrayList<DepartmentDto> getAllDepartments() throws NoDepartmentException {
    String query = "SELECT id, label, ver_nr FROM bmobile.departments";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      return fillDtoArray(resultSet);

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new NoDepartmentException("An SQL Error happened");
    }

  }

  @Override
  public DepartmentDto getDepartementById(String id) throws NoDepartmentException {
    String query = "SELECT id, label, ver_nr FROM bmobile.departments WHERE id = ?";

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, id);
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      return fillDto(resultSet);

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new NoDepartmentException("An SQL Error happened");
    }

  }

  @Override
  public DepartmentDto getDepartmentByLabel(String label) throws NoDepartmentException {
    String query = "SELECT id, label, ver_nr FROM bmobile.departments WHERE label=?";

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, label);
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      return fillDto(resultSet);

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new NoDepartmentException("An SQL Error happened");
    }

  }

  private DepartmentDto fillDto(ResultSet resultSet) throws NoDepartmentException {
    DepartmentDto departmentDto = factory.getDepartmentDto();
    try {
      if (resultSet.next()) {
        departmentDto.setId(resultSet.getString(1));
        departmentDto.setLabel(resultSet.getString(2));
        departmentDto.setVerNr(resultSet.getInt(3));
      } else {
        throw new NoDepartmentException("Ce département n'existe pas");
      }
      return departmentDto;
    } catch (SQLException exc2) {
      throw new NoDepartmentException("Une erreur est survenue");
    }


  }

  private ArrayList<DepartmentDto> fillDtoArray(ResultSet resultSet) throws NoDepartmentException {
    ArrayList<DepartmentDto> departments = new ArrayList<DepartmentDto>();
    try {
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
      throw new NoDepartmentException("La liste des départements n'a pu être chargée");
    }


  }

}
