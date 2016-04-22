package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.CountryDao;
import dto.CountryDto;
import exceptions.NoCountryException;
import exceptions.UnknowErrorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryDaoImpl implements CountryDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  /**
   * Instantiates a new Country dao.
   *
   * @param dalBackendServices the dal backend services
   * @param bizzFactory the bizz factory
   */
  public CountryDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  @Override
  public ArrayList<CountryDto> getAll() throws SQLException {
    String query =
        "SELECT iso, name_en, name_fr, id_program FROM bmobile.countries ORDER BY name_fr";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      try (ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement)) {
        return fillDtoArray(resultSet);
      } catch (SQLException exc2) {
        exc2.printStackTrace();
        throw new SQLException();
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement des pays");
    }

  }

  @Override
  public CountryDto getCountryByNameFr(String name) throws NoCountryException {
    String query =
        "SELECT iso, name_en, name_fr, id_program FROM bmobile.countries WHERE name_fr=?";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, name);
      try (ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement)) {
        return fillDto(resultSet);
      } catch (SQLException exc2) {
        exc2.printStackTrace();
        throw new SQLException();
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement du pays en français.");
    }
  }


  @Override
  public CountryDto getCountryByIso(String iso) throws NoCountryException {
    String query = "SELECT iso, name_en, name_fr, id_program FROM bmobile.countries WHERE iso=?";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, iso);
      try (ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement)) {
        return fillDto(resultSet);
      } catch (SQLException exc2) {
        exc2.printStackTrace();
        throw new SQLException();
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement du pays.");
    }
  }

  private ArrayList<CountryDto> fillDtoArray(ResultSet resultSet) throws SQLException {
    ArrayList<CountryDto> countries = new ArrayList<CountryDto>();
    while (resultSet.next()) {
      CountryDto countryDto = factory.getCountryDto();
      countryDto.setIso(resultSet.getString("iso"));
      countryDto.setNameEn(resultSet.getString("name_en"));
      countryDto.setNameFr(resultSet.getString("name_fr"));
      countryDto.setIdProgram(resultSet.getInt("id_program"));
      countries.add(countryDto);
    }
    return countries;
  }

  private CountryDto fillDto(ResultSet resultSet) throws NoCountryException, SQLException {
    CountryDto countryDto = factory.getCountryDto();
    if (resultSet.next()) {
      countryDto.setIso(resultSet.getString("iso"));
      countryDto.setNameEn(resultSet.getString("name_en"));
      countryDto.setNameFr(resultSet.getString("name_fr"));
      countryDto.setIdProgram(resultSet.getInt("id_program"));
    } else {
      throw new NoCountryException("Ce pays n'existe pas");
    }
    return countryDto;
  }
}
