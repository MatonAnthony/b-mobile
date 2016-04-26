package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.UserDao;
import dto.CountryDto;
import dto.UserDto;
import exceptions.UnknowErrorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserDaoImpl implements UserDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  private String getUserQuery = "SELECT u.id, u.id_department, u.pseudo, u.password,"
      + " u.name, u.firstname, u.email, u.registration_date, u.permissions, u.birth_date, u.street,"
      + " u.citizenship, u.house_number, u.mailbox, u.zip, u.city, u.country, u.tel, u.gender,"
      + " u.successfull_year_in_college, u.iban, u.bic, u.account_holder, u.bank_name, u.ver_nr, "

      + "co.iso, co.name_en, co.name_fr, co.id_program "
      + "FROM bmobile.users u LEFT OUTER JOIN bmobile.countries co ON u.country = co.iso ";

  public UserDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  @Override
  public void createUser(UserDto userdto) {
    // TODO(fany) values en fonction de la db
    String query = "INSERT INTO bmobile.users VALUES (DEFAULT,NULL,?,?,?,?,?,?,?,NULL,NULL,NULL"
        + ",NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0)";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, userdto.getPseudo());
      preparedStatement.setString(2, userdto.getPassword());
      preparedStatement.setString(3, userdto.getName());
      preparedStatement.setString(4, userdto.getFirstname());
      preparedStatement.setString(5, userdto.getEmail());
      preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
      preparedStatement.setString(7, userdto.getPermissions());

      dalBackendServices.executeUpdate(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException("Une erreur inconnue s'est produite lors de l'inscription.");
    }
  }

  @Override
  public UserDto getUserByUserName(String username) throws NoSuchElementException {
    String query = this.getUserQuery + "WHERE pseudo=?";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, username);
      return fillDto(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la recherche de l'utilisateurs.");
    }
  }

  @Override
  public UserDto getUserById(int id) throws NoSuchElementException {
    String query = this.getUserQuery + "WHERE id=?";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, id);
      return fillDto(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la recherche de l'utilisateur.");
    }
  }


  @Override
  public ArrayList<UserDto> getAllUsers() {
    String query = this.getUserQuery + "ORDER BY id";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      return fillDtoArray(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la recherche des utilisateurs.");
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
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du changement des permissions.");
    }
  }

  @Override
  public void updateUser(UserDto userEdited) {
    // TODO (Jonathan) Rajouter la gestion de l'optimistic lock.
    // language=PostgreSQL
    String query = "UPDATE bmobile.users SET name = ?, firstname = ?, gender = ?, citizenship = ?,"
        + "street = ?, house_number = ?, mailbox = ?, zip = ?, city = ?, tel = ?, email = ?,"
        + "successfull_year_in_college = ?, bic = ?, account_holder = ?, bank_name = ?, "
        + "country = ?, iban = ?, birth_date = ?" + "WHERE id = ?";
    PreparedStatement preparedStatement = null;
    System.out.println(userEdited.getCitizenship());
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
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la mise à jour de l'utilisateur.");
    }
  }

  private UserDto fillDto(PreparedStatement preparedStatement) throws SQLException {
    UserDto user = factory.getUserDto();
    ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
    if (resultSet.next()) {
      completeDto(user, resultSet);
    } else {
      throw new NoSuchElementException("Cet utilisateur n'existe pas.");
    }
    return user;
  }

  private ArrayList<UserDto> fillDtoArray(PreparedStatement preparedStatement) throws SQLException {
    ArrayList<UserDto> users = new ArrayList<UserDto>();
    ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
    while (resultSet.next()) {
      UserDto user = factory.getUserDto();
      user = completeDto(user, resultSet);
      users.add(user);
    }
    return users;
  }

  private UserDto completeDto(UserDto user, ResultSet resultSet) throws SQLException {
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
    user.setCitizenship(resultSet.getString("citizenship"));
    user.setStreet(resultSet.getString("street"));
    user.setHouseNumber(resultSet.getString(13));
    user.setMailBox(resultSet.getString(14));
    user.setZip(resultSet.getString(15));
    user.setCity(resultSet.getString(16));
    user.setCountry(resultSet.getString("country"));
    user.setTel(resultSet.getString(18));
    user.setGender(resultSet.getString(19));
    user.setSuccessfullYearInCollege(resultSet.getInt(20));
    user.setIban(resultSet.getString(21));
    user.setBic(resultSet.getString(22));
    user.setAccountHolder(resultSet.getString(23));
    user.setBankName(resultSet.getString(24));
    user.setVerNr(resultSet.getInt(25));

    CountryDto countryDto = factory.getCountryDto();
    countryDto.setIdProgram(resultSet.getInt("id_program"));
    countryDto.setIso(resultSet.getString("iso"));
    countryDto.setNameEn(resultSet.getString("name_en"));
    countryDto.setNameFr(resultSet.getString("name_fr"));
    user.setCountryDto(countryDto);

    return user;
  }

  @Override
  public boolean userExists(String username) {
    try {
      getUserByUserName(username);
      return true;
    } catch (NoSuchElementException exc) {
      return false;
    }
  }

  @Override
  public int countUser() {
    String queryCount = "SELECT count(u.id) FROM bmobile.users u";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryCount);
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      if (resultSet.next()) {
        return resultSet.getInt(1);
      } else {
        return -1;
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la recherche du nombre d'utilisateur.");
    }
  }
}
