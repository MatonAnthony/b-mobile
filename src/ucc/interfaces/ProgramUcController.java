package ucc.interfaces;

import dto.ProgramDto;

import java.util.ArrayList;

public interface ProgramUcController {

  ArrayList<ProgramDto> getAllPrograms();

  ProgramDto getProgramByName(String name);
}
