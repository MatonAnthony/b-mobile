package ucc.implementations;

import dal.DalServices;
import dao.interfaces.ProgramDao;
import dto.ProgramDto;
import ucc.interfaces.ProgramUcController;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramUcControllerImpl implements ProgramUcController {

  private ProgramDao programDao;
  private DalServices dalServices;

  /**
   * Create a new Program Use Case Controller.
   * 
   * @param programDao The dao used by the ucc.
   * @param dalServices The dal used by the ucc.
   */
  public ProgramUcControllerImpl(DalServices dalServices, ProgramDao programDao) {
    this.programDao = programDao;
    this.dalServices = dalServices;
  }

  @Override
  public ArrayList<ProgramDto> getAllPrograms() throws SQLException {
    dalServices.openConnection();
    ArrayList<ProgramDto> programs = programDao.getAllProgram();
    dalServices.closeConnection();
    return programs;
  }

  @Override
  public ProgramDto getProgramByName(String name) throws SQLException {
    dalServices.openConnection();
    ProgramDto program = programDao.findByName(name);
    dalServices.closeConnection();
    return program;
  }

}
