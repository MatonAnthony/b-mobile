package dao;

import bizz.BizzFactory;
import dal.DalBackendServices;
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
    String query = "SELECT * FROM bmobile.programs";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      ArrayList<ProgramDto> programs = new ArrayList<ProgramDto>();
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          ProgramDto programDto = factory.getProgramDto();
          programDto.setId(resultSet.getInt(1));
          programDto.setName(resultSet.getString(2));
          programDto.setDescription(resultSet.getString(3));
          programs.add(programDto);
        }
        return programs;
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
