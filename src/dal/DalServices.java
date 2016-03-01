package dal;

import java.sql.SQLException;

public interface DalServices {

  void startTransaction() throws SQLException;

  void commitTransaction() throws SQLException;

  void rollbackTransaction() throws SQLException;


}
