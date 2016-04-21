package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.ProgramDao;
import dto.ProgramDto;
import exceptions.UnknowErrorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramDaoImpl implements ProgramDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  public ProgramDaoImpl(DalBackendServices dalBackendServices, BizzFactory factory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = factory;
  }


  @Override
  public ArrayList<ProgramDto> getAllProgram() {
    String query = "SELECT id, name, description, ver_nr FROM bmobile.programs";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      return fillDtoArray(dalBackendServices.executeQuery(preparedStatement));

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la recherche des programmes.");
    }

  }

  @Override
  public ProgramDto findByName(String name) {
    String query = "SELECT id, name, description, ver_nr FROM bmobile.programs WHERE name=?";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, name);
      return fillDto(dalBackendServices.executeQuery(preparedStatement));

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la recherche du programme.");
    }
  }

  private ProgramDto fillDto(ResultSet resultSet) throws SQLException {
    ProgramDto programDto = factory.getProgramDto();
    if (resultSet.next()) {
      programDto.setId(resultSet.getInt(1));
      programDto.setName(resultSet.getString(2));
      programDto.setDescription(resultSet.getString(3));
      programDto.setVerNr(resultSet.getInt(4));
    } else {
      return null;
    }
    return programDto;


  }

  private ArrayList<ProgramDto> fillDtoArray(ResultSet resultSet) throws SQLException {
    ArrayList<ProgramDto> programs = new ArrayList<ProgramDto>();
    while (resultSet.next()) {
      ProgramDto programDto = factory.getProgramDto();
      programDto.setId(resultSet.getInt(1));
      programDto.setName(resultSet.getString(2));
      programDto.setDescription(resultSet.getString(3));
      programDto.setVerNr(resultSet.getInt(4));
      programs.add(programDto);
    }
    return programs;
  }

}
