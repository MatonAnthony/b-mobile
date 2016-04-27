package dao;

import dao.interfaces.DepartmentDao;
import dto.DepartmentDto;
import exceptions.NoDepartmentException;

import java.util.ArrayList;

public class DepartmentDaoMock implements DepartmentDao {

  private ArrayList<DepartmentDto> Db;

  public DepartmentDaoMock(ArrayList<DepartmentDto> Db) {
    this.Db = Db;
  }

  @Override
  public ArrayList<DepartmentDto> getAllDepartments() {
    return Db;
  }

  @Override
  public DepartmentDto getDepartementById(String id) throws NoDepartmentException {
    for (int i = 0; i < Db.size(); i++) {
      if (Db.get(i).getId().equals(id)) {
        return Db.get(i);
      }
    }
    throw new NoDepartmentException();
  }

  @Override
  public DepartmentDto getDepartmentByLabel(String label) throws NoDepartmentException {
    for (int i = 0; i < Db.size(); i++) {
      if (Db.get(i).getLabel().equals(label)) {
        return Db.get(i);
      }
    }
    throw new NoDepartmentException();
  }

}
