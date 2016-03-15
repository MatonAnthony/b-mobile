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

  /**
   * To add a user in the data base.
   * 
   * @param userdto is the user to add.
   * @return true if the user is added. False is there was a error.
   */

  @Override
  public boolean createUser(UserDto userdto) {
    // TODO(fany) values en fonction de la db
    String query = "INSERT INTO bmobile.users VALUES (DEFAULT,?,?,?,?,?)";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, userdto.getPseudo());
      preparedStatement.setString(2, userdto.getPassword());
      preparedStatement.setString(3, userdto.getName());
      preparedStatement.setString(4, userdto.getFirstname());
      preparedStatement.setString(5, userdto.getEmail());
      try (ResultSet res = preparedStatement.executeQuery()) {
        return true;

      } catch (Exception exc) {
        return false;
      }

    } catch (SQLException exc) {
      exc.printStackTrace();
      return false;
    }
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
    // TODO (Martin) Modifier pour que tous les champs soient remplis
    String query = "SELECT pseudo, password, permissions FROM bmobile.users WHERE pseudo=?";
    PreparedStatement preparedStatement = null;
    UserDto user = factory.getUserDto();
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, username);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          user.setPseudo(resultSet.getString(1));
          user.setPassword(resultSet.getString(2));
          user.setPermissions(resultSet.getString(3));
        } else {
          return null;
        }
        return user;
      } catch (SQLException exc2) {
        exc2.printStackTrace();
        return null;
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }

  }

}
