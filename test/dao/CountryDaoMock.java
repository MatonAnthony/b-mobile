package dao;

import dao.interfaces.CountryDao;
import dto.CountryDto;
import exceptions.NoCountryException;

import java.sql.SQLException;
import java.util.ArrayList;

public class CountryDaoMock implements CountryDao {

  private ArrayList<CountryDto> Db;

  public CountryDaoMock(ArrayList<CountryDto> listDto) {
    this.Db = listDto;
  }

  @Override
  public ArrayList<CountryDto> getAll() throws SQLException {
    return Db;
  }

  @Override
  public CountryDto getCountryByNameFr(String name) throws NoCountryException {
    for (int i = 0; i < Db.size(); i++) {
      if (Db.get(i).getNameFr().equals(name)) {
        return Db.get(i);
      }
    }
    throw new NoCountryException();
  }

  @Override
  public CountryDto getCountryByIso(String iso) throws NoCountryException {
    for (int i = 0; i < Db.size(); i++) {
      if (Db.get(i).getIso().equals(iso)) {
        return Db.get(i);
      }
    }
    throw new NoCountryException();
  }
}
