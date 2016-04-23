package ucc.interfaces;

import dto.CancelationDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CancelationUcController {

  ArrayList<CancelationDto> getCancelationsReasons() throws SQLException;

}
