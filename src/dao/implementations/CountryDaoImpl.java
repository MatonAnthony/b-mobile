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
    String query =
        "SELECT iso, name_en, name_fr, id_program FROM bmobile.countries ORDER BY name_fr";
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
  public CountryDto getCountryByNameFr(String name) {
    String query =
        "SELECT iso, name_en, name_fr, id_program FROM bmobile.countries WHERE name_fr=?";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, name);
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

  private CountryDto fillDto(PreparedStatement preparedStatement) {
    CountryDto countryDto = factory.getCountryDto();
    try (ResultSet resultSet = preparedStatement.executeQuery()) {
      if (resultSet.next()) {
        countryDto.setIso(resultSet.getString("iso"));
        countryDto.setNameEn(resultSet.getString("name_en"));
        countryDto.setNameFr(resultSet.getString("name_fr"));
        countryDto.setIdProgram(resultSet.getInt("id_program"));
      } else {
        return null;
      }
      return countryDto;
    } catch (SQLException exc2) {
      exc2.printStackTrace();
      return null;
    }
  }

  private ArrayList<CountryDto> fillDtoArray(PreparedStatement preparedStatement) {
    ArrayList<CountryDto> countries = new ArrayList<CountryDto>();
    try (ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        CountryDto countryDto = factory.getCountryDto();
        countryDto.setIso(resultSet.getString("iso"));
        countryDto.setNameEn(resultSet.getString("name_en"));
        countryDto.setNameFr(resultSet.getString("name_fr"));
        countryDto.setIdProgram(resultSet.getInt("id_program"));
        countries.add(countryDto);
      }
      return countries;
    } catch (SQLException exc2) {
      exc2.printStackTrace();
      return null;
    }
  }

}
