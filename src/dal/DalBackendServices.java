package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

  /**
   * Executes an insert, update or delete statement by our Database.
   *
   * @param statement Prepared statement to execute.
   * @return int The row count for statement
   */
  int executeUpdate(PreparedStatement statement);

  /**
   * Executes a select statement by our Database.
   *
   * @param statement Prepared statement to execute.
   * @return ResultSet The result set for statement
   */
  ResultSet executeQuery(PreparedStatement statement) throws SQLException;

}
