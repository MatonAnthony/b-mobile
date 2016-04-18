package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.CountryDao;
import dto.CountryDto;
import exceptions.NoCountryException;

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
   * @param bizzFactory        the bizz factory
   */
  public CountryDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  /**
   * Return all countries registered in our database.
   * @return a list all countries registered in our database.
   */
  @Override
  public ArrayList<CountryDto> getAll() throws SQLException, NoCountryException {
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
      throw new SQLException();
    }

  }

  /**
   * Get a Country by his french name.
   *
   * @param name French name of the country.
   * @return Informations relative to the country you asked about.
   */
  @Override
  public CountryDto getCountryByNameFr(String name) throws SQLException, NoCountryException {
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
      throw new SQLException();
    }
  }

  private CountryDto fillDto(ResultSet resultSet) throws NoCountryException {
    CountryDto countryDto = factory.getCountryDto();
    try {
      if (resultSet.next()) {
        countryDto.setIso(resultSet.getString("iso"));
        countryDto.setNameEn(resultSet.getString("name_en"));
        countryDto.setNameFr(resultSet.getString("name_fr"));
        countryDto.setIdProgram(resultSet.getInt("id_program"));
      } else {
        throw new NoCountryException("Ce pays n'existe pas");
      }
      return countryDto;
    } catch (SQLException exc2) {
      exc2.printStackTrace();
      throw new NoCountryException("Ce pays n'existe pas");
    }
  }

  private ArrayList<CountryDto> fillDtoArray(ResultSet resultSet)
      throws NoCountryException {
    ArrayList<CountryDto> countries = new ArrayList<CountryDto>();
    try {
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
      throw new NoCountryException("Une erreur est survenue");
    }
  }

}
