package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.PartnerDao;
import dto.CountryDto;
import dto.PartnerDto;
import dto.UserDto;
import exceptions.UnknowErrorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PartnerDaoImpl implements PartnerDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  public PartnerDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  @Override
  public void createPartner(PartnerDto partner) {
    String query =
        "INSERT INTO bmobile.partners VALUES " + "(DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, partner.getUserDto().getId());
      preparedStatement.setString(2, partner.getLegalName());
      preparedStatement.setString(3, partner.getBusiness());
      preparedStatement.setString(4, partner.getFullName());
      preparedStatement.setString(5, partner.getDepartment());
      preparedStatement.setString(6, partner.getType());
      preparedStatement.setInt(7, partner.getNbEmployees());
      preparedStatement.setString(8, partner.getStreet());
      preparedStatement.setString(9, partner.getNumber());
      preparedStatement.setString(10, partner.getMailbox());
      preparedStatement.setString(11, partner.getZip());
      preparedStatement.setString(12, partner.getCity());
      preparedStatement.setString(13, partner.getState());
      preparedStatement.setString(14, partner.getCountryDto().getIso());
      preparedStatement.setString(15, partner.getTel());
      preparedStatement.setString(16, partner.getEmail());
      preparedStatement.setString(17, partner.getWebsite());
      preparedStatement.setBoolean(18, partner.isExists());
      preparedStatement.setInt(19, partner.getVerNr());
      dalBackendServices.executeUpdate(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la création du partenaire.");
    }
  }

  @Override
  public ArrayList<PartnerDto> getPartnersMin(int userId, String permission) {

    // language=PostgreSQL
    String query = "SELECT p.id, p.legal_name FROM bmobile.partners p "
        + "LEFT JOIN bmobile.mobilities m ON p.id = m.id_partner "
        + "WHERE m.id_partner IS NULL AND p.id_user = ? AND p.exists = ?";
    PreparedStatement preparedStatement = null;
    ArrayList<PartnerDto> partners = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, userId);

      if (permission.equals("STUDENT")) {
        preparedStatement.setBoolean(2, false);
      }
      if (permission.equals("TEACHER")) {
        preparedStatement.setBoolean(2, true);
      }

      partners = new ArrayList<PartnerDto>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);

      while (resultSet.next()) {
        PartnerDto partnerDto = factory.getPartnerDto();
        partnerDto.setId(resultSet.getInt(1));
        partnerDto.setLegalName(resultSet.getString(2));
        partners.add(partnerDto);
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
    }

    return partners;
  }

  @Override
  public ArrayList<PartnerDto> getAllPartners() {
    String query =
        "SELECT p.id, p.id_user, p.legal_name, p.business_name, p.full_name, p.department, "
            + "p.type, p.nb_employees, p.street, p.number, p.mailbox, p.zip, p.city, p.state, "
            + "p.country, p.tel, p.email, p.website, p.exists, p.ver_nr FROM bmobile.partners p";
    ArrayList<PartnerDto> partners = null;
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);

      partners = fillDtoArray(preparedStatement);

    } catch (SQLException exc) {
      exc.printStackTrace();
    }

    return partners;

  }


  @Override
  public PartnerDto getPartnerById(int id) {
    String query = "SELECT par.id, par.id_user, par.legal_name, par.business_name,"
        + " par.full_name, par.department,par.type, par.nb_employees, par.street,"
        + " par.number, par.mailbox, par.zip, par.city, par.state, par.country, par.email,"
        + " par.website, par.exists, par.tel, par.ver_nr,"
        + " u.id, u.id_department, u.pseudo, u.password, u.name, u.firstname, u.email, "
        + " u.registration_date, u.permissions, u.birth_date, u.street, u.citizenship,"
        + " u.house_number, u.mailbox, u.zip, u.city, u.country, u.tel, u.gender,"
        + " u.successfull_year_in_college, u.iban, u.bic, u.account_holder, u.bank_name, u.ver_nr,"
        + " co.iso, co.name_en, co.name_fr, co.id_program"
        + " FROM bmobile.partners par LEFT OUTER JOIN bmobile.users u ON par.id_user = u.id,"
        + " bmobile.countries co WHERE par.country = co.iso AND par.id = ?";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, id);
      return fillDtoFull(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la recherche du partenaire.");
    }
  }

  /**
   * Execute a preparedStatement an return an ArrayList of PartnerDto.
   * 
   * @param preparedStatement The PreparedStatement to execute.
   * @return An ArrayList of PartnerDto
   * @throws SQLException If an error occured in the database.
   */
  private ArrayList<PartnerDto> fillDtoArray(PreparedStatement preparedStatement)
      throws SQLException {
    ArrayList<PartnerDto> partners = new ArrayList<PartnerDto>();
    ResultSet rs = dalBackendServices.executeQuery(preparedStatement);
    while (rs.next()) {
      PartnerDto partner = completeDto(rs);
      partners.add(partner);
    }
    return partners;
  }


  private PartnerDto fillDtoFull(PreparedStatement preparedStatement) throws SQLException {
    PartnerDto partner = factory.getPartnerDto();
    ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
    if (resultSet.next()) {
      completeDtoFull(partner, resultSet);
    } else {
      throw new NoSuchElementException("Ce partenaire n'existe pas.");
    }
    return partner;
  }


  private PartnerDto completeDto(ResultSet resultSet) throws SQLException {
    PartnerDto partner = factory.getPartnerDto();
    partner.setId(resultSet.getInt(1));
    partner.setIdUser(resultSet.getInt(2));
    partner.setLegalName(resultSet.getString(3));
    partner.setBusiness(resultSet.getString(4));
    partner.setFullName(resultSet.getString(5));
    partner.setDepartment(resultSet.getString(6));
    partner.setType(resultSet.getString(7));
    partner.setNbEmployees(resultSet.getInt(8));
    partner.setStreet(resultSet.getString(9));
    partner.setNumber(resultSet.getString(10));
    partner.setMailbox(resultSet.getString(11));
    partner.setZip(resultSet.getString(12));
    partner.setCity(resultSet.getString(13));
    partner.setState(resultSet.getString(14));
    partner.setCountry(resultSet.getString(15));
    partner.setTel(resultSet.getString(16));
    partner.setEmail(resultSet.getString(17));
    partner.setWebsite(resultSet.getString(18));
    partner.setExists(resultSet.getBoolean(19));
    partner.setVerNr(resultSet.getInt(20));
    return partner;
  }

  private PartnerDto completeDtoFull(PartnerDto partner, ResultSet resultSet) throws SQLException {
    partner.setId(resultSet.getInt(1));
    partner.setIdUser(resultSet.getInt(2));
    partner.setLegalName(resultSet.getString(3));
    partner.setBusiness(resultSet.getString(4));
    partner.setFullName(resultSet.getString(5));
    partner.setDepartment(resultSet.getString(6));
    partner.setType(resultSet.getString(7));
    partner.setNbEmployees(resultSet.getInt(8));
    partner.setStreet(resultSet.getString(9));
    partner.setNumber(resultSet.getString(10));
    partner.setMailbox(resultSet.getString(11));
    partner.setZip(resultSet.getString(12));
    partner.setCity(resultSet.getString(13));
    partner.setState(resultSet.getString(14));
    partner.setCountry(resultSet.getString(15));
    partner.setEmail(resultSet.getString(16));
    partner.setWebsite(resultSet.getString(17));
    partner.setExists(resultSet.getBoolean(18));
    partner.setTel(resultSet.getString(19));
    partner.setVerNr(resultSet.getInt(20));

    UserDto user = factory.getUserDto();
    user.setId(resultSet.getInt(21));
    user.setIdDepartment(resultSet.getInt(22));
    user.setPseudo(resultSet.getString(23));
    user.setPassword(resultSet.getString(24));
    user.setName(resultSet.getString(25));
    user.setFirstname(resultSet.getString(26));
    user.setEmail(resultSet.getString(27));
    Timestamp registrationDate = resultSet.getTimestamp(28);
    if (null != registrationDate) {
      user.setRegistrationDate(registrationDate.toLocalDateTime().toLocalDate());
    }
    user.setPermissions(resultSet.getString(29));
    Timestamp birthdate = resultSet.getTimestamp(30);
    if (null != birthdate) {
      user.setBirthDate(birthdate.toLocalDateTime().toLocalDate());
    }
    user.setCitizenship(resultSet.getString("citizenship"));
    user.setStreet(resultSet.getString("street"));
    user.setHouseNumber(resultSet.getString(33));
    user.setMailBox(resultSet.getString(34));
    user.setZip(resultSet.getString(35));
    user.setCity(resultSet.getString(36));
    user.setCountry(resultSet.getString("country"));
    user.setTel(resultSet.getString(38));
    user.setGender(resultSet.getString(39));
    user.setSuccessfullYearInCollege(resultSet.getInt(40));
    user.setIban(resultSet.getString(41));
    user.setBic(resultSet.getString(42));
    user.setAccountHolder(resultSet.getString(43));
    user.setBankName(resultSet.getString(44));
    user.setVerNr(resultSet.getInt(45));
    partner.setUserDto(user);

    CountryDto countryDto = factory.getCountryDto();
    countryDto.setIdProgram(resultSet.getInt("id_program"));
    countryDto.setIso(resultSet.getString("iso"));
    countryDto.setNameEn(resultSet.getString("name_en"));
    countryDto.setNameFr(resultSet.getString("name_fr"));
    partner.setCountryDto(countryDto);

    return partner;
  }

}
