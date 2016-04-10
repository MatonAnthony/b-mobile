package dal;

import utils.ContextManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DalServicesImpl implements DalServices, DalBackendServices {

  private Connection connection;

  /**
   * Constructor for our DalServices.
   */
  public DalServicesImpl() {
    String url =
        ContextManager.getProperty("urlDB") + "?user=" + ContextManager.getProperty("userDB")
            + "&password=" + ContextManager.getProperty("passwordDB");

    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException exc) {
      System.out.println("Driver PostgreSQL manquant !");
    }

    try {
      connection = DriverManager.getConnection(url);
    } catch (SQLException exc) {
      System.out.println("Impossible de joindre le seveur");
    }

  }

  @Override
  public void startTransaction() throws SQLException {
    connection.setAutoCommit(false);
  }

  @Override
  public void commitTransaction() throws SQLException {
    connection.commit();
    connection.setAutoCommit(true);

  }

  @Override
  public void rollbackTransaction() throws SQLException {
    connection.rollback();
    connection.setAutoCommit(true);
  }

  @Override
  public PreparedStatement prepare(String query) throws SQLException {
    return connection.prepareStatement(query);

  }
}
