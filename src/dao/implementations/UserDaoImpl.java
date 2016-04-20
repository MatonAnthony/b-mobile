package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.UserDao;
import dto.UserDto;
import exceptions.NoCountryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserDaoImpl implements UserDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;


  public UserDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  @Override
  public boolean createUser(UserDto userdto) {
    // TODO(fany) values en fonction de la db
    String query = "INSERT INTO bmobile.users VALUES (DEFAULT,NULL,?,?,?,?,?,NULL,?,NULL,NULL,NULL"
        + ",NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0)";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, userdto.getPseudo());
      preparedStatement.setString(2, userdto.getPassword());
      preparedStatement.setString(3, userdto.getName());
      preparedStatement.setString(4, userdto.getFirstname());
      preparedStatement.setString(5, userdto.getEmail());
      preparedStatement.setString(6, userdto.getPermissions());

      dalBackendServices.executeUpdate(preparedStatement);
      return true;

    } catch (SQLException exc) {
      exc.printStackTrace();
      return false;
    }
  }

  @Override
  public UserDto getUserByUserName(String username)
      throws NoSuchElementException, NoCountryException {
    String query = "SELECT id, id_department, pseudo, password, name, firstname, email, "
        + "registration_date, permissions, birth_date, street, citizenship, "
        + "house_number, mailbox, zip, city, country, tel, gender, successfull_year_in_college, "
        + "iban, bic, account_holder, bank_name, ver_nr FROM bmobile.users WHERE pseudo=?";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, username);
      return fillDto(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }
  }

  @Override
  public UserDto getUserById(int id) throws NoSuchElementException, NoCountryException {
    String query = "SELECT id, id_department, pseudo, password, name, firstname, email, "
        + "registration_date, permissions, birth_date, citizenship, street, "
        + "house_number, mailbox, zip, city, country, tel, gender, successfull_year_in_college, "
        + "iban, bic, account_holder, bank_name, ver_nr FROM bmobile.users WHERE id=?";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, id);
      return fillDto(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }
  }


  @Override
  public ArrayList<UserDto> getAllUsers() throws NoCountryException {
    String query = "SELECT id, id_department, pseudo, password, name, firstname, email, "
        + "registration_date, permissions, birth_date, citizenship, street, "
        + "house_number, mailbox, zip, city, country, tel, gender, successfull_year_in_college, "
        + "iban, bic, account_holder, bank_name, ver_nr FROM bmobile.users ORDER BY id";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      return fillDtoArray(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      return null;
    }
  }


  @Override
  public void changePermissionsForUserById(UserDto user) {
    String query = "UPDATE bmobile.users SET permissions='TEACHER' WHERE id=?";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, user.getId());
      dalBackendServices.executeUpdate(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  @Override
  public void updateUser(UserDto userEdited) {
    String query = "UPDATE bmobile.users SET name = ?, firstname = ?, gender = ?, citizenship = ?,"
        + "street = ?, house_number = ?, mailbox = ?, zip = ?, city = ?, tel = ?, email = ?,"
        + "successfull_year_in_college = ?, bic = ?, account_holder = ?, bank_name = ?, "
        + "country = ?, iban = ?, birth_date = ?" + "WHERE id = ?";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, userEdited.getName());
      preparedStatement.setString(2, userEdited.getFirstname());
      preparedStatement.setString(3, userEdited.getGender());
      preparedStatement.setString(4, userEdited.getCitizenship());
      preparedStatement.setString(5, userEdited.getStreet());
      preparedStatement.setString(6, userEdited.getHouseNumber());
      preparedStatement.setString(7, userEdited.getMailBox());
      preparedStatement.setString(8, userEdited.getZip());
      preparedStatement.setString(9, userEdited.getCity());
      preparedStatement.setString(10, userEdited.getTel());
      preparedStatement.setString(11, userEdited.getEmail());
      preparedStatement.setInt(12, userEdited.getSuccessfullYearInCollege());
      preparedStatement.setString(13, userEdited.getBic());
      preparedStatement.setString(14, userEdited.getAccountHolder());
      preparedStatement.setString(15, userEdited.getBankName());
      preparedStatement.setString(16, userEdited.getCountryDto().getIso());
      preparedStatement.setString(17, userEdited.getIban());
      try {
        preparedStatement.setTimestamp(18,
            Timestamp.valueOf(userEdited.getBirthDate().atStartOfDay()));
      } catch (NullPointerException exc) {
        preparedStatement.setTimestamp(18, null);
      }
      preparedStatement.setInt(19, userEdited.getId());
      dalBackendServices.executeUpdate(preparedStatement);


    } catch (SQLException exc) {
      exc.printStackTrace();
    }
  }

  private UserDto fillDto(PreparedStatement preparedStatement) throws NoCountryException {
    UserDto user = factory.getUserDto();
    try (ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement)) {
      if (resultSet.next()) {
        completeDto(user, resultSet);
      } else {
        throw new NoSuchElementException("Cet utilisateur n'existe pas.");
      }
      return user;
    } catch (SQLException exc2) {
      exc2.printStackTrace();
      return null;
    }
  }

  private ArrayList<UserDto> fillDtoArray(PreparedStatement preparedStatement)
      throws NoCountryException {
    ArrayList<UserDto> users = new ArrayList<UserDto>();
    try (ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement)) {
      while (resultSet.next()) {
        UserDto user = factory.getUserDto();
        user = completeDto(user, resultSet);
        users.add(user);
      }
      return users;
    } catch (SQLException exc2) {
      exc2.printStackTrace();
      return null;
    }
  }

  private UserDto completeDto(UserDto user, ResultSet resultSet)
      throws SQLException, NoCountryException {
    user.setId(resultSet.getInt(1));
    user.setIdDepartment(resultSet.getInt(2));
    user.setPseudo(resultSet.getString(3));
    user.setPassword(resultSet.getString(4));
    user.setName(resultSet.getString(5));
    user.setFirstname(resultSet.getString(6));
    user.setEmail(resultSet.getString(7));
    Timestamp registrationDate = resultSet.getTimestamp(8);
    if (null != registrationDate) {
      user.setRegistrationDate(registrationDate.toLocalDateTime().toLocalDate());
    }
    user.setPermissions(resultSet.getString(9));
    Timestamp birthdate = resultSet.getTimestamp(10);
    if (null != birthdate) {
      user.setBirthDate(birthdate.toLocalDateTime().toLocalDate());
    }
    user.setCitizenship(resultSet.getString(11));
    user.setStreet(resultSet.getString(12));
    user.setHouseNumber(resultSet.getString(13));
    user.setMailBox(resultSet.getString(14));
    user.setZip(resultSet.getString(15));
    user.setCity(resultSet.getString(16));
    user.setCountry(resultSet.getString(17));
    user.setTel(resultSet.getString(18));
    user.setGender(resultSet.getString(19));
    user.setSuccessfullYearInCollege(resultSet.getInt(20));
    user.setIban(resultSet.getString(21));
    user.setBic(resultSet.getString(22));
    user.setAccountHolder(resultSet.getString(23));
    user.setBankName(resultSet.getString(24));
    user.setVerNr(resultSet.getInt(25));
    /*
     * try {
     * 
     * // TODO : (Antho)Vérifier si c'est bien la bonne façon de faire et éventuellement
     * refactoriser CountryDto country = new CountryDaoImpl(this.dalBackendServices, this.factory)
     * .getCountryByIso(user.getCountry());
     * 
     * } catch (SQLException exc) { throw new NoCountryException("Le pays n'a pas été trouvé"); }
     */

    return user;
  }

  @Override
  public boolean userExists(String username) {
    try {
      if (getUserByUserName(username) != null) {
        return true;
      }
    } catch (Exception exc) {
      return false;
    }
    return false;
  }
}
