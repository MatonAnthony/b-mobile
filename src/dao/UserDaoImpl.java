package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.DalBackendServices;
import dto.UserDto;

public class UserDaoImpl implements UserDao {

  private DalBackendServices dalBackendServices;

  public UserDaoImpl(DalBackendServices dalBackendServices) {
    this.dalBackendServices = dalBackendServices;
  }

  @Override
  public boolean createUser() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void read() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean update() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delete() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Cherche et renvoie l'utilisateur sur base du pseudo.
   * 
   * @param username Le pseudo de l'utilisateur a rechercher.
   * @return null si l'utilisateur n'est pas enregistre dans le BDD. Un dto avec les informations de
   *         l'utilisateur si l'utilisateur est enregistre dans la BDD.
   */
  @Override
  public UserDto findByUserName(String username) {
    String query = "SELECT pseudo, mdp, droits FROM bmobile.utilisateurs WHERE pseudo=?";
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, username);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        resultSet.getString(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }


    return null;
  }

}
