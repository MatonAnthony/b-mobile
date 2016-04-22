package ucc.interfaces;

import dto.ProgramDto;
import exceptions.NoProgramException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProgramUcController {
  /**
   * Return the list of Programs.
   * 
   * @return An ArrayList whith the programs.
   * @throws SQLException If there is an error.
   */
  ArrayList<ProgramDto> getAllPrograms() throws SQLException;

  /**
   * Return a program dto for the name "name".
   * 
   * @param name The name of the program to return.
   * @return a programDto.
   * @throws NoProgramException If there is no program matching with the name.
   * @throws SQLException If there is an error.
   */
  ProgramDto getProgramByName(String name) throws SQLException, NoProgramException;
}
