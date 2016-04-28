package dao;

import dao.interfaces.CountryDao;
import dto.CountryDto;
import exceptions.NoCountryException;

import java.sql.SQLException;
import java.util.ArrayList;

public class CountryDaoMock implements CountryDao {

  private ArrayList<CountryDto> db;

  public CountryDaoMock(ArrayList<CountryDto> listDto) {
    this.db = listDto;
  }

  @Override
  public ArrayList<CountryDto> getAll() throws SQLException {
    return db;
  }

  @Override
  public CountryDto getCountryByNameFr(String name) throws NoCountryException {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getNameFr().equals(name)) {
        return db.get(i);
      }
    }
    throw new NoCountryException();
  }

  @Override
  public CountryDto getCountryByIso(String iso) throws NoCountryException {
    for (int i = 0; i < db.size(); i++) {
      if (db.get(i).getIso().equals(iso)) {
        return db.get(i);
      }
    }
    throw new NoCountryException();
  }
}
