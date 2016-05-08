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
    threadLocal.get().setAutoCommit(false);
  }

  @Override
  public void commitTransaction() throws SQLException {
    threadLocal.get().commit();
    threadLocal.get().setAutoCommit(true);
  }

  @Override
  public void rollbackTransaction() throws SQLException {
    threadLocal.get().rollback();
    threadLocal.get().setAutoCommit(true);
  }

  @Override
  public PreparedStatement prepare(String query) throws SQLException {
    return threadLocal.get().prepareStatement(query);
  }

  @Override
  public void openConnection() throws SQLException {
    threadLocal.set(connectionPool.getConnection());
  }

  @Override
  public int executeUpdate(PreparedStatement statement) throws SQLException {
    try {
      if (ContextManager.env == "debug") {
        Main.LOGGER.finest(statement.toString());
      }
      return statement.executeUpdate();
    } catch (SQLException exc) {
      Main.LOGGER.severe(exc.getMessage());
      exc.printStackTrace();
      throw exc;
    }
  }

  @Override
  public ResultSet executeQuery(PreparedStatement statement) throws SQLException {
    try {
      if (ContextManager.env == "debug") {
        Main.LOGGER.finest(statement.toString());
      }
      return statement.executeQuery();
    } catch (SQLException exc) {
      Main.LOGGER.severe(exc.getMessage());
      exc.printStackTrace();
      throw exc;
    }
  }


  @Override
  public void closeConnection() throws SQLException {
    Connection connex = threadLocal.get();
    try {
      threadLocal.remove();
      connex.close();
    } catch (SQLException exc) {
      exc.printStackTrace();
    }

  }

}
