package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.ProgramDao;
import dto.ProgramDto;

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
  public ProgramDto findByName(String name) {
    String query = "SELECT id, name, description, ver_nr FROM bmobile.programs WHERE name=?";
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

  private ProgramDto fillDto(PreparedStatement preparedStatement) {
    ProgramDto programDto = factory.getProgramDto();
    try (ResultSet resultSet = preparedStatement.executeQuery()) {
      if (resultSet.next()) {
        programDto.setId(resultSet.getInt(1));
        programDto.setName(resultSet.getString(2));
        programDto.setDescription(resultSet.getString(3));
        programDto.setVerNr(resultSet.getInt(4));
      } else {
        return null;
      }
      return programDto;
    } catch (SQLException exc2) {
      exc2.printStackTrace();
      return null;
    }


  }

  private ArrayList<ProgramDto> fillDtoArray(PreparedStatement preparedStatement) {
    ArrayList<ProgramDto> programs = new ArrayList<ProgramDto>();
    try (ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        ProgramDto programDto = factory.getProgramDto();
        programDto.setId(resultSet.getInt(1));
        programDto.setName(resultSet.getString(2));
        programDto.setDescription(resultSet.getString(3));
        programDto.setVerNr(resultSet.getInt(4));
        programs.add(programDto);
      }
      return programs;
    } catch (SQLException exc2) {
      exc2.printStackTrace();
      return null;
    }
  }

}
