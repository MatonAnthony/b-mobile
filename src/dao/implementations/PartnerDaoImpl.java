package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.PartnerDao;
import dto.CountryDto;
import dto.PartnerDto;
import dto.UserDto;
import exceptions.MalformedIbanException;
import exceptions.UnknowErrorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PartnerDaoImpl implements PartnerDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;
  // language=PostgreSQL
  private String queryFull = "SELECT par.id, par.id_user, par.legal_name, par.business_name,"
      + " par.full_name, par.department,par.type, par.nb_employees, par.street,"
      + " par.number, par.mailbox, par.zip, par.city, par.state, par.country, par.email,"
      + " par.website, par.exists, par.deleted, par.tel, par.ver_nr,"
      + " u.id, u.id_department, u.pseudo, u.name, u.firstname, u.email, "
      + " u.registration_date, u.permissions, u.birth_date, u.street, u.citizenship,"
      + " u.house_number, u.mailbox, u.zip, u.city, u.country, u.tel, u.gender,"
      + " u.successfull_year_in_college, u.iban, u.bic, u.account_holder, u.bank_name, u.ver_nr,"
      + " co.iso, co.name_en, co.name_fr, co.id_program"
      + " FROM bmobile.partners par LEFT OUTER JOIN bmobile.users u ON par.id_user = u.id,"
      + " bmobile.countries co WHERE par.country = co.iso ";

  public PartnerDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  @Override
  public void createPartner(PartnerDto partner) {
    // language=PostgreSQL
    String query = "INSERT INTO bmobile.partners VALUES "
        + "(DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,FALSE,?) RETURNING id ";

    // language=PostgreSQL
    String partnerDepartment = "INSERT INTO bmobile.partners_departments VALUES (?,?,?)";

    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement2 = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement2 = dalBackendServices.prepare(partnerDepartment);
      preparedStatement.setInt(1, partner.getUserDto().getId());
      preparedStatement.setString(2, partner.getLegalName());

      if (partner.getBusiness() == null || partner.getBusiness().equals("")) {
        preparedStatement.setNull(3, Types.INTEGER);
      } else {
        preparedStatement.setString(3, partner.getBusiness());
      }

      if (partner.getFullName() == null || partner.getFullName().equals("")) {
        preparedStatement.setNull(4, Types.INTEGER);
      } else {
        preparedStatement.setString(4, partner.getFullName());
      }

      if (partner.getFullName() == null || partner.getFullName().equals("")) {
        preparedStatement.setNull(4, Types.INTEGER);
      } else {
        preparedStatement.setString(4, partner.getFullName());
      }

      if (partner.getDepartment() == null || partner.getDepartment().equals("")) {
        preparedStatement.setNull(5, Types.INTEGER);
      } else {
        preparedStatement.setString(5, partner.getDepartment());
      }

      if (partner.getType() == null || partner.getType().equals("")) {
        preparedStatement.setNull(6, Types.INTEGER);
      } else {
        preparedStatement.setString(6, partner.getType());
      }

      preparedStatement.setInt(7, partner.getNbEmployees());

      if (partner.getStreet() == null || partner.getStreet().equals("")) {
        preparedStatement.setNull(8, Types.INTEGER);
      } else {
        preparedStatement.setString(8, partner.getStreet());
      }

      if (partner.getNumber() == null || partner.getNumber().equals("")) {
        preparedStatement.setNull(9, Types.INTEGER);
      } else {
        preparedStatement.setString(9, partner.getNumber());
      }

      if (partner.getMailbox() == null || partner.getMailbox().equals("")) {
        preparedStatement.setNull(10, Types.INTEGER);
      } else {
        preparedStatement.setString(10, partner.getMailbox());
      }

      if (partner.getZip() == null || partner.getZip().equals("")) {
        preparedStatement.setNull(11, Types.INTEGER);
      } else {
        preparedStatement.setString(11, partner.getZip());
      }

      if (partner.getCity() == null || partner.getCity().equals("")) {
        preparedStatement.setNull(12, Types.INTEGER);
      } else {
        preparedStatement.setString(12, partner.getCity());
      }

      if (partner.getState() == null || partner.getState().equals("")) {
        preparedStatement.setNull(13, Types.INTEGER);
      } else {
        preparedStatement.setString(13, partner.getState());
      }

      if (partner.getCountryDto().getIso() == null || partner.getCountryDto().getIso().equals("")) {
        preparedStatement.setNull(14, Types.INTEGER);
      } else {
        preparedStatement.setString(14, partner.getCountryDto().getIso());
      }

      if (partner.getTel() == null || partner.getTel().equals("")) {
        preparedStatement.setNull(15, Types.INTEGER);
      } else {
        preparedStatement.setString(15, partner.getTel());
      }

      if (partner.getEmail() == null || partner.getEmail().equals("")) {
        preparedStatement.setNull(16, Types.INTEGER);
      } else {
        preparedStatement.setString(16, partner.getEmail());
      }

      if (partner.getWebsite() == null || partner.getWebsite().equals("")) {
        preparedStatement.setNull(17, Types.INTEGER);
      } else {
        preparedStatement.setString(17, partner.getWebsite());
      }

      preparedStatement.setBoolean(18, partner.isExists());
      preparedStatement.setInt(19, partner.getVerNr());
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);

      if (resultSet.next()) {
        preparedStatement2.setInt(1, resultSet.getInt(1));
      }
      preparedStatement2.setString(2, partner.getUserDto().getIdDepartment());
      preparedStatement2.setInt(3, 0);
      dalBackendServices.executeUpdate(preparedStatement2);
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la création du partenaire.");
    }
  }

  @Override
  public ArrayList<PartnerDto> getPartnersMin(int userId, String permission) {

    // language=PostgreSQL
    String query =
        "SELECT DISTINCT p.id, p.legal_name, p.exists, p.country FROM bmobile.partners p "
            + "LEFT JOIN bmobile.mobilities m ON p.id = m.id_partner WHERE";

    if (permission.equals("STUDENT")) {
      query += " m.id_partner IS NULL AND p.id_user = ? AND p.exists = ? AND p.deleted = false";
    }
    if (permission.equals("TEACHER")) {
      query += " m.status = 'En attente' OR  m.id_partner IS NULL AND p.deleted = false";
    }

    PreparedStatement preparedStatement = null;
    ArrayList<PartnerDto> partners = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);

      if (permission.equals("STUDENT")) {
        preparedStatement.setInt(1, userId);
        preparedStatement.setBoolean(2, false);
      }
      partners = new ArrayList<PartnerDto>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);

      while (resultSet.next()) {
        PartnerDto partnerDto = factory.getPartnerDto();
        partnerDto.setId(resultSet.getInt(1));
        partnerDto.setLegalName(resultSet.getString(2));
        partnerDto.setExists(resultSet.getBoolean(3));
        partnerDto.setCountry(resultSet.getString(4));
        partners.add(partnerDto);
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
    }

    return partners;
  }

  @Override
  public ArrayList<PartnerDto> getAllPartners() throws MalformedIbanException {

    String query = queryFull + " ORDER BY par.id";
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
  public ArrayList<PartnerDto> getTeacherPartners() throws MalformedIbanException {
    String queryTemp = queryFull + " AND exists=TRUE";

    ArrayList<PartnerDto> partners = null;
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryTemp);

      partners = fillDtoArray(preparedStatement);

    } catch (SQLException exc) {
      exc.printStackTrace();
    }

    return partners;
  }

  @Override
  public ArrayList<PartnerDto> getPartnersForStudentList(int userId) {
    String query = "SELECT DISTINCT p.id, p.legal_name, p.city, p.department, c.name_fr "
        + " FROM bmobile.countries c, bmobile.partners p "
        + " WHERE p.country = c.iso AND (p.id_user = ? OR p.exists = TRUE) AND p.deleted = false";


    PreparedStatement preparedStatement = null;
    ArrayList<PartnerDto> partners = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);

      preparedStatement.setInt(1, userId);
      partners = new ArrayList<PartnerDto>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);

      while (resultSet.next()) {
        PartnerDto partnerDto = factory.getPartnerDto();
        partnerDto.setId(resultSet.getInt(1));
        partnerDto.setLegalName(resultSet.getString(2));
        partnerDto.setCity(resultSet.getString(3));
        partnerDto.setDepartment(resultSet.getString(4));

        CountryDto countryDto = factory.getCountryDto();
        countryDto.setNameFr(resultSet.getString("name_fr"));
        partnerDto.setCountryDto(countryDto);

        partners.add(partnerDto);
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
    }

    return partners;
  }

  @Override
  public ArrayList<PartnerDto> getPartnersWithoutMobility() {
    String query = "SELECT DISTINCT id_partner FROM bmobile.mobilities WHERE id_partner NOTNULL";
    ArrayList<PartnerDto> partners = new ArrayList<PartnerDto>();
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        PartnerDto partnerDto = factory.getPartnerDto();
        partnerDto.setId(resultSet.getInt(1));
        partners.add(partnerDto);
      }
    } catch (SQLException exc) {
      exc.printStackTrace();
    }
    return partners;
  }


  @Override
  public PartnerDto getPartnerById(int id) throws MalformedIbanException {
    String query = queryFull + "AND par.id = ?";
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


  @Override
  public ArrayList<PartnerDto> getDeletedPartners() throws MalformedIbanException {
    String queryTemp = queryFull + " AND deleted=TRUE";

    ArrayList<PartnerDto> partners = null;
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryTemp);
      partners = fillDtoArray(preparedStatement);

    } catch (SQLException exc) {
      exc.printStackTrace();
    }

    return partners;
  }

  @Override
  public int updatePartner(PartnerDto partner) {

    String query = "UPDATE bmobile.partners SET legal_name=?, business_name=?,"
        + " full_name=?, department=?, type=?, nb_employees=?, street=?, number=?, mailbox=?,"
        + " zip=?, city=?, state=?, country=?, email=?, website=?, exists=?, tel=?, ver_nr=?"
        + " WHERE id=? AND ver_nr=?";

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);

      preparedStatement.setString(1, partner.getLegalName());
      preparedStatement.setString(2, partner.getBusiness());
      preparedStatement.setString(3, partner.getFullName());
      preparedStatement.setString(4, partner.getDepartment());
      preparedStatement.setString(5, partner.getType());
      preparedStatement.setInt(6, partner.getNbEmployees());
      preparedStatement.setString(7, partner.getStreet());
      preparedStatement.setString(8, partner.getNumber());
      preparedStatement.setString(9, partner.getMailbox());
      preparedStatement.setString(10, partner.getZip());
      preparedStatement.setString(11, partner.getCity());
      preparedStatement.setString(12, partner.getState());
      preparedStatement.setString(13, partner.getCountryDto().getIso());
      preparedStatement.setString(14, partner.getEmail());
      preparedStatement.setString(15, partner.getWebsite());
      preparedStatement.setBoolean(16, partner.isExists());
      preparedStatement.setString(17, partner.getTel());
      preparedStatement.setInt(18, partner.getVerNr() + 1);
      preparedStatement.setInt(19, partner.getId());
      preparedStatement.setInt(20, partner.getVerNr());

      return dalBackendServices.executeUpdate(preparedStatement);

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la mise à jour du partneraire.");
    }
  }

  @Override
  public int setDeleted(PartnerDto partner) {
    String query = "UPDATE bmobile.partners SET deleted=?, ver_nr=?, id_user=? WHERE id=? AND ver_nr=?";

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);

      preparedStatement.setBoolean(1, partner.isDeleted());
      preparedStatement.setInt(2, partner.getVerNr() + 1);
      preparedStatement.setInt(3, partner.getIdUser());
      preparedStatement.setInt(4, partner.getId());
      preparedStatement.setInt(5, partner.getVerNr());

      return dalBackendServices.executeUpdate(preparedStatement);

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la mise à jour d'un partenaire (deleted).");
    }
  }


  /**
   * Execute a preparedStatement an return an ArrayList of PartnerDto.
   * 
   * @param preparedStatement The PreparedStatement to execute.
   * @return An ArrayList of PartnerDto
   * @throws SQLException If an error occured in the database.
   * @throws MalformedIbanException If the Iban is malformed.
   */
  private ArrayList<PartnerDto> fillDtoArray(PreparedStatement preparedStatement)
      throws SQLException, MalformedIbanException {
    ArrayList<PartnerDto> partners = new ArrayList<PartnerDto>();
    ResultSet rs = dalBackendServices.executeQuery(preparedStatement);
    while (rs.next()) {
      PartnerDto partner = completeDtoFull(rs);
      partners.add(partner);
    }
    return partners;
  }


  private PartnerDto fillDtoFull(PreparedStatement preparedStatement)
      throws SQLException, MalformedIbanException {
    PartnerDto partner;
    ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
    if (resultSet.next()) {
      partner = completeDtoFull(resultSet);
    } else {
      throw new NoSuchElementException("Ce partenaire n'existe pas.");
    }
    return partner;
  }

  private PartnerDto completeDtoFull(ResultSet resultSet)
      throws SQLException, MalformedIbanException {
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
    partner.setEmail(resultSet.getString(16));
    partner.setWebsite(resultSet.getString(17));
    partner.setExists(resultSet.getBoolean(18));
    partner.setDeleted(resultSet.getBoolean(19));
    partner.setTel(resultSet.getString(20));
    partner.setVerNr(resultSet.getInt(21));

    UserDto user = factory.getUserDto();
    user.setId(resultSet.getInt(22));
    user.setIdDepartment(resultSet.getString(23));
    user.setPseudo(resultSet.getString(24));
    // user.setPassword(resultSet.getString(24));
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
