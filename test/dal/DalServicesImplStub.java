package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DalServicesImplStub implements DalServices, DalBackendServices {

  public DalServicesImplStub() {}

  @Override
  public PreparedStatement prepare(String query) throws SQLException {
    return null;
  }

  @Override
  public int executeUpdate(PreparedStatement statement) throws SQLException {
    return 1;
  }

  @Override
  public ResultSet executeQuery(PreparedStatement statement) throws SQLException {
    return null;
  }

  @Override
  public void startTransaction() throws SQLException {}

  @Override
  public void commitTransaction() throws SQLException {}

  @Override
  public void rollbackTransaction() throws SQLException {}

  @Override
  public void openConnection() throws SQLException {}

  @Override
  public void closeConnection() throws SQLException {}

}
