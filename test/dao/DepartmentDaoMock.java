package dao;

import dao.interfaces.DepartmentDao;
import dto.DepartmentDto;
import exceptions.NoDepartmentException;

import java.util.ArrayList;

public class DepartmentDaoMock implements DepartmentDao {

  private ArrayList<DepartmentDto> db;

  public DepartmentDaoMock(ArrayList<DepartmentDto> db) {
    this.db = db;
  }

  @Override
  public ArrayList<DepartmentDto> getAllDepartments() {
    return db;
  }

  @Override
  public DepartmentDto getDepartementById(String id) throws NoDepartmentException {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getId().equals(id)) {
        return db.get(i);
      }
    }
    throw new NoDepartmentException();
  }

  @Override
  public DepartmentDto getDepartmentByLabel(String label) throws NoDepartmentException {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getLabel().equals(label)) {
        return db.get(i);
      }
    }
    throw new NoDepartmentException();
  }

}
