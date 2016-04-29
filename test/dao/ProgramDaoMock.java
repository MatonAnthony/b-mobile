package dao;

import dao.interfaces.ProgramDao;
import dto.ProgramDto;
import exceptions.NoProgramException;

import java.util.ArrayList;

public class ProgramDaoMock implements ProgramDao {

  private ArrayList<ProgramDto> db;

  public ProgramDaoMock(ArrayList<ProgramDto> db) {
    this.db = db;
  }

  @Override
  public ArrayList<ProgramDto> getAllProgram() {
    return db;
  }

  @Override
  public ProgramDto findByName(String name) throws NoProgramException {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getName().equals(name)) {
        return db.get(i);
      }
    }
    throw new NoProgramException();
  }

}
