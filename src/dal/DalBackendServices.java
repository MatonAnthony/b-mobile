package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DalBackendServices {
  /**
   * Prepare a statement to be executed by our Database.
   *
   * @param query SQL query to prepare.
   * @return PreparedStatement the answer provided by the Database.
   * @throws SQLException when the SQL syntax is invalid.
   */
  PreparedStatement prepare(String query) throws SQLException;

}
