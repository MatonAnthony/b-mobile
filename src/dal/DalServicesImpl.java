package dal;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

class DalServicesImpl implements DalServices, DalBackendServices {

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

    try {
      connection.setAutoCommit(false);
    } catch (SQLException exc) {
      System.out.println("Erreur lors du set de l'autoCommit");
    }

  }

  @Override
  public void startTransaction() {
    // TODO Auto-generated method stub

  }

  @Override
  public void commitTransaction() {
    // TODO Auto-generated method stub

  }

  @Override
  public void rollbackTransaction() {
    // TODO Auto-generated method stub

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
