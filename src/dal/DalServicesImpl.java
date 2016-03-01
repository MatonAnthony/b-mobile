package dal;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DalServicesImpl implements DalServices, DalBackendServices {

  private Connection connection;
  private Properties properties;

  public DalServicesImpl() {

    properties = new Properties();

    try {
      FileInputStream file = new FileInputStream("src/prod.properties");
      properties.load(file);
      file.close();
    } catch (Throwable exc) {
      throw new RuntimeException(exc);
    }

    String url = properties.getProperty("urlDB") + "?user=" + properties.getProperty("userDB")
        + "&password=" + properties.getProperty("passwordDB");

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

  /**
   * Demarre une nouvelle transaction sur la connexion.
   */
  @Override
  public void startTransaction() throws SQLException {
    connection.setAutoCommit(false);
  }

  /**
   * Execute tous les changements effectues depuis le debut de la transaction.
   */
  @Override
  public void commitTransaction() throws SQLException {
    connection.commit();
    connection.setAutoCommit(true);

  }

  /**
   * Annule toutes les instructions executees depuis le debut de la transaction.
   */
  @Override
  public void rollbackTransaction() throws SQLException {
    connection.rollback();
    connection.setAutoCommit(true);
  }

  /**
   * Prepare le query sur la connexion.
   * 
   * @param query Le query qui sera prepare.
   * @return PreparedStatement
   */
  @Override
  public PreparedStatement prepare(String query) throws SQLException {
    return connection.prepareStatement(query);

  }

}
