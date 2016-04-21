package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.DepartmentDao;
import dto.DepartmentDto;
import exceptions.NoDepartmentException;
import exceptions.UnknowErrorException;

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
   * @param factory the factory
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
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chagement des départements.");
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
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chagement du département.");
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
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chagement du département.");
    }

  }

  private DepartmentDto fillDto(ResultSet resultSet) throws NoDepartmentException, SQLException {
    DepartmentDto departmentDto = factory.getDepartmentDto();
    if (resultSet.next()) {
      departmentDto.setId(resultSet.getString(1));
      departmentDto.setLabel(resultSet.getString(2));
      departmentDto.setVerNr(resultSet.getInt(3));
    } else {
      throw new NoDepartmentException("Ce département n'existe pas");
    }
    return departmentDto;


  }

  private ArrayList<DepartmentDto> fillDtoArray(ResultSet resultSet)
      throws NoDepartmentException, SQLException {
    ArrayList<DepartmentDto> departments = new ArrayList<DepartmentDto>();
    while (resultSet.next()) {
      DepartmentDto departmentDto = factory.getDepartmentDto();
      departmentDto.setId(resultSet.getString(1));
      departmentDto.setLabel(resultSet.getString(2));
      departmentDto.setVerNr(resultSet.getInt(3));
      departments.add(departmentDto);
    }
    return departments;


  }

}
