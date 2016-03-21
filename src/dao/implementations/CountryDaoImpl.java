package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.CountryDao;
import dto.CountryDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryDaoImpl implements CountryDao {


  private DalBackendServices dalBackendServices;
  private BizzFactory factory;


  public CountryDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }


  @Override
  public ArrayList<CountryDto> getAll() {
    String query = "SELECT * FROM bmobile.countries c ORDER BY c.name_fr";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      ArrayList<CountryDto> countries = new ArrayList<CountryDto>();
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          CountryDto countryDto = factory.getCountryDto();
          countryDto.setIso(resultSet.getString(1));
          countryDto.setNameEn(resultSet.getString(2));
          countryDto.setNameFr(resultSet.getString(3));
          countryDto.setIdProgram(resultSet.getInt(4));
          countries.add(countryDto);
        }
        return countries;
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
