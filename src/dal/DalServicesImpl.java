package dal;

import ihm.Main;
import utils.ContextManager;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DalServicesImpl implements DalServices, DalBackendServices {

  private DataSource dataSource;
  private ThreadLocal<Connection> threadLocal;

  /**
   * Constructor for our DalServices.
   */
  public DalServicesImpl() {
    threadLocal = new ThreadLocal<Connection>();

    String url =
        ContextManager.getProperty("urlDB") + "?user=" + ContextManager.getProperty("userDB")
            + "&password=" + ContextManager.getProperty("passwordDB");

    ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, null);
    PoolableConnectionFactory poolableConnectionFactory =
        new PoolableConnectionFactory(connectionFactory, null);
    ObjectPool<PoolableConnection> connectionPool =
        new GenericObjectPool<>(poolableConnectionFactory);
    poolableConnectionFactory.setPool(connectionPool);
    dataSource = new PoolingDataSource<>(connectionPool);
    /*
     * connectionPool.setUsername(ContextManager.getProperty("userDB"));
     * connectionPool.setPassword(ContextManager.getProperty("passwordDB"));
     * connectionPool.setDriverClassName("org.postgresql.Driver"); connectionPool.setUrl(url);
     * connectionPool.setInitialSize(10);
     */
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
      threadLocal.set(dataSource.getConnection());
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
