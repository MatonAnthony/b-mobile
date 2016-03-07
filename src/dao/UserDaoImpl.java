package dao;

import bizz.BizzFactory;
import dal.DalBackendServices;
import dto.UserDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  public UserDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
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
   * @return null si l'utilisateur n'est pas enregistre dans la BDD. Un dto avec les informations de
   *         l'utilisateur si l'utilisateur est enregistre dans la BDD.
   */
  @Override
  public UserDto findByUserName(String username) {
    String query = "SELECT pseudo, mdp, droits FROM bmobile.utilisateurs WHERE pseudo=?";
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    UserDto user = factory.getUserDto();
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, username);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        user.setPseudo(resultSet.getString(1));
        user.setMdp(resultSet.getString(2));
        user.setDroits(resultSet.getString(3));
      } else {
        return null;
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }

    return user;
  }

}
