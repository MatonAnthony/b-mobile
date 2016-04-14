package dal;

import utils.ContextManager;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    connectionPool.setInitialSize(10);

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

}
