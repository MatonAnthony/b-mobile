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
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }

    String url = properties.getProperty("urlDB") + "?user=" + properties.getProperty("userDB")
        + "&password=" + properties.getProperty("passwordDB");

    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Driver PostgreSQL manquant !");
      System.exit(1);
    }

    try {
      connection = DriverManager.getConnection(url);
    } catch (SQLException e) {
      System.out.println("Impossible de joindre le seveur");
      System.exit(1);
    }

    try {
      connection.setAutoCommit(false);
    } catch (SQLException e) {
      System.out.println("Erreur lors du set de l'autoCommit");
      System.exit(1);
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
