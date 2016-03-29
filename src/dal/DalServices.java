package dal;

import java.sql.SQLException;

public interface DalServices {

  /**
   * Open a SQL transaction.
   *
   * @throws SQLException if the SQL server cannot open a transaction.
   */
  void startTransaction() throws SQLException;

  /**
   * Commit a SQL transaction.
   * @throws SQLException if the SQL server cannot commit the transaction.
   */
  void commitTransaction() throws SQLException;

  /**
   * Rollback the current SQL transaction.
   * @throws SQLException if the SQL cannot rollback the transaction.
   */
  void rollbackTransaction() throws SQLException;


}
