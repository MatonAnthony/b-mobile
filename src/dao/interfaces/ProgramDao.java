package dao.interfaces;

import dto.ProgramDto;
import exceptions.NoProgramException;

import java.util.ArrayList;

/**
 * The interface Program dao.
 */
public interface ProgramDao {

  /**
   * Gets all program.
   *
   * @return the all program
   */
  ArrayList<ProgramDto> getAllProgram();

  /**
   * Find by name a program dto.
   *
   * @param name the name
   * @return the program dto
   * @throws NoProgramException if no program is matching with the name.
   */
  ProgramDto findByName(String name) throws NoProgramException;

}
