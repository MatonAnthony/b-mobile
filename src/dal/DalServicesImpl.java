package dal;

import ihm.Main;
import utils.ContextManager;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DalServicesImpl implements DalServices, DalBackendServices {

  private BasicDataSource connectionPool;
  private ThreadLocal<Connection> threadLocal;

  /**
   * Constructor for our DalServices.
   */
  public DalServicesImpl() {
    threadLocal = new ThreadLocal<Connection>();
    connectionPool = new BasicDataSource();
    String url = ContextManager.getProperty("urlDB");
    connectionPool.setUsername(ContextManager.getProperty("userDB"));
    connectionPool.setPassword(ContextManager.getProperty("passwordDB"));
    connectionPool.setDriverClassName("org.postgresql.Driver");
    connectionPool.setUrl(url);
    connectionPool.setInitialSize(2);
    connectionPool.setMaxTotal(5);
  }

  @Override
  public void startTransaction() throws SQLException {
    getConnection().setAutoCommit(false);
  }

  @Override
  public void commitTransaction() throws SQLException {
    Connection connection = getConnection();
    connection.commit();
    connection.setAutoCommit(true);
    connection.close();
  }

  @Override
  public void rollbackTransaction() throws SQLException {
    Connection connection = getConnection();
    connection.rollback();
    connection.setAutoCommit(true);
    connection.close();
  }

  @Override
  public PreparedStatement prepare(String query) throws SQLException {
    return getConnection().prepareStatement(query);

  }

  private Connection getConnection() throws SQLException {

    if (threadLocal.get() == null) {
      threadLocal.set(connectionPool.getConnection());
    }
    return threadLocal.get();

  }

  @Override
  public int executeUpdate(PreparedStatement statement) {
    try {
      Main.LOGGER.finest(statement.toString());
      return statement.executeUpdate();
    } catch (SQLException exc) {
      Main.LOGGER.severe(exc.getMessage());
      exc.printStackTrace();
      return 0;
    }
  }

  @Override
  public ResultSet executeQuery(PreparedStatement statement) {
    try {
      Main.LOGGER.finest(statement.toString());
      return statement.executeQuery();
    } catch (SQLException exc) {
      Main.LOGGER.severe(exc.getMessage());
      exc.printStackTrace();
      return null;
    }
  }

}
