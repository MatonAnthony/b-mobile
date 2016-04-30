package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.MobilityDao;
import dto.MobilityDto;
import exceptions.NoMobilityException;
import exceptions.UnknowErrorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

public class MobilityDaoImpl implements MobilityDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  // query for select mobilities
  // language=PostgreSQL
  private String query =
      "SELECT id, id_student, id_program, id_partner, type, preference_order, country,"
          + " id_department, quadrimester, status, canceled, departure_grant_contract,"
          + " departure_convention_internship_schoolarship, departure_student_convention,"
          + " departure_erasmus_language_test, departure_doc_aggreement,"
          + " depart_doc_sent_highschool, software_proeco, software_mobility_tools,"
          + " software_mobi, return_residence_cert, return_transcript, return_internship_cert,"
          + " return_final_report, return_erasmus_language_test, return_doc_sent_highschool, "
          + "cancelation_reason, academic_year, ver_nr, amount, first_payment_date, "
          + "second_payment_date FROM bmobile.mobilities";

  // query for select mobilities full

  // language=PostgreSQL
  private String queryFull =
      "SELECT m.id, m.id_student, m.id_program, m.id_partner, m.type, m.preference_order,"
          + " m.country, m.id_department, m.quadrimester, m.status, m.canceled,"
          + " m.departure_grant_contract, m.departure_convention_internship_schoolarship, "
          + "m.departure_student_convention, m.departure_erasmus_language_test, "
          + "m.departure_doc_aggreement, m.depart_doc_sent_highschool, m.software_proeco,"
          + " m.software_mobility_tools, m.software_mobi, m.return_residence_cert, "
          + "m.return_transcript, m.return_internship_cert, m.return_final_report, "
          + "m.return_erasmus_language_test, m.return_doc_sent_highschool, m.cancelation_reason, "
          + "m.academic_year, m.ver_nr, amount, first_payment_date, second_payment_date,  " // 32

          + "u.id, u.id_department, u.pseudo, u.name, u.firstname, u.email, u.registration_date,"
          + " u.permissions, u.birth_date, u.street, u.house_number, u.mailbox, u.zip, u.city, " // 46
          + "u.country, u.tel, u.gender, u.successfull_year_in_college, u.iban, u.bic, "
          + "u.account_holder, u.bank_name, u.citizenship, u.ver_nr, " // 56

          + "pro.id, pro.name, pro.description, pro.ver_nr, "
          + "par.id, par.id_user, par.legal_name, par.business_name, par.full_name, par.department,"
          + "par.type, par.nb_employees, par.street, par.number, par.mailbox, par.zip, par.city,"
          + "par.state,par.country, par.email, par.website, par.exists, par.ver_nr,"


          + "co.iso, co.name_en, co.name_fr, co.id_program," + "d.id, d.label, d.ver_nr "
          + ",c.id, c.reason, c.responsible, c.ver_nr "

          + "FROM bmobile.mobilities m LEFT OUTER JOIN bmobile.partners par "
          + "ON m.id_partner = par.id LEFT OUTER JOIN bmobile.cancelations c "
          + "ON m.cancelation_reason = c.id, bmobile.users u, bmobile.programs pro, "
          + "bmobile.countries co, bmobile.departments d "

          + "WHERE (m.id_student = u.id) AND (m.id_program = pro.id) "
          + "AND (m.country = co.iso) AND (m.id_department = d.id)";


  public MobilityDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  @Override
  public void createMobility(MobilityDto mobilityDto) {
    // language=PostgreSQL
    String query = "INSERT INTO bmobile.mobilities VALUES (DEFAULT,?,?,?,?,?,?,?,?,'En attente',0,"
        + "FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,"
        + "FALSE,FALSE,FALSE,FALSE,FALSE,NULL,?,0)";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, mobilityDto.getStudentDto().getId());
      preparedStatement.setInt(2, mobilityDto.getProgramDto().getId());

      if (mobilityDto.getPartnerDto() == null) {
        preparedStatement.setNull(3, Types.INTEGER);
      } else {
        preparedStatement.setInt(3, mobilityDto.getPartnerDto().getId());
      }

      preparedStatement.setString(4, mobilityDto.getType());
      preparedStatement.setInt(5, mobilityDto.getPreferenceOrder());
      preparedStatement.setString(6, mobilityDto.getCountryDto().getIso());
      preparedStatement.setString(7, mobilityDto.getDepartmentDto().getId());
      preparedStatement.setInt(8, mobilityDto.getQuadrimester());
      preparedStatement.setString(9, mobilityDto.getAcademicYear());

      dalBackendServices.executeUpdate(preparedStatement);

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la création de la mobilité.");
    }

  }

  @Override
  public ArrayList<MobilityDto> getAllMobilities() {

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      ArrayList<MobilityDto> mobilities = new ArrayList<MobilityDto>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      while (resultSet.next()) {
        mobilities.add(fillDto(resultSet));
      }
      return mobilities;

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement des mobilités.");
    }
  }

  @Override
  public ArrayList<MobilityDto> getFullMobilities() {
    String queryTemp = queryFull + "ORDER BY m.id ASC";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryTemp);
      ArrayList<MobilityDto> mobilities = new ArrayList<MobilityDto>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      while (resultSet.next()) {
        mobilities.add(fillFullDto(resultSet));
      }
      return mobilities;

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement des mobilités (complètes).");
    }
  }

  @Override
  public ArrayList<MobilityDto> getFullConfirmedMobilities() {

    String queryTemp =
        queryFull + " AND m.status != 'En attente' AND m.canceled = 'false' ORDER BY m.id ASC";

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryTemp);
      ArrayList<MobilityDto> mobilities = new ArrayList<MobilityDto>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      while (resultSet.next()) {
        mobilities.add(fillFullDto(resultSet));
      }
      return mobilities;

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement des mobilités confirmées.");
    }

  }

  @Override
  public ArrayList<MobilityDto> getFullMyMobilities(String pseudo) {

    String queryTemp = queryFull + " AND (u.pseudo = '" + pseudo + "' ) ORDER BY m.id ASC";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryTemp);
      ArrayList<MobilityDto> mobilities = new ArrayList<MobilityDto>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      while (resultSet.next()) {
        mobilities.add(fillFullDto(resultSet));
      }
      return mobilities;

    } catch (SQLException exc) {
      exc.printStackTrace();

      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement des mobilités "
              + "de l'utilisateur (complètes).");
    }
  }


  @Override
  public ArrayList<String> getAllAcademicYears() {
    String queryAcademicYears =
        "SELECT DISTINCT academic_year FROM bmobile.mobilities ORDER BY academic_year DESC";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryAcademicYears);
      ArrayList<String> academicYears = new ArrayList<String>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      while (resultSet.next()) {
        academicYears.add(resultSet.getString(1));
      }
      return academicYears;

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement des années académiques.");
    }
  }

  @Override
  public ArrayList<MobilityDto> getFullPayments(String academicYear) {
    String queryPayment = queryFull + "AND m.academic_year = ? ORDER BY m.id ASC";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryPayment);
      preparedStatement.setString(1, academicYear);
      ArrayList<MobilityDto> payments = new ArrayList<MobilityDto>();
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      while (resultSet.next()) {
        payments.add(fillFullDto(resultSet));
      }
      return payments;

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement des paiements.");
    }
  }

  @Override
  public MobilityDto getMobilityById(int id) throws NoMobilityException {
    String queryById = queryFull + " AND m.id = ?";
    PreparedStatement preparedStatement;
    MobilityDto mobilityDto = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryById);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = dalBackendServices.executeQuery(preparedStatement);
      if (resultSet.next()) {
        mobilityDto = fillFullDto(resultSet);
      } else {
        throw new NoMobilityException("Cette mobilité n'existe pas");
      }

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors du chargement de la mobilite.");
    }
    return mobilityDto;
  }

  @Override
  public void cancelMobility(int idMobility, int idCancelation, int verNr) {
    // TODO (Jonathan) Gérer le ver_nr !!
    String queryUpdate =
        "UPDATE bmobile.mobilities SET cancelation_reason=?, canceled=TRUE, status='Annulee', ver_nr=? WHERE id=? AND ver_nr=?";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(queryUpdate);
      preparedStatement.setInt(1, idCancelation);
      preparedStatement.setInt(2, verNr + 1);
      preparedStatement.setInt(3, idMobility);
      preparedStatement.setInt(4, verNr);

      dalBackendServices.executeUpdate(preparedStatement);
    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de l'annulation de la mobilité.");
    }
  }

  @Override
  public void confirmPartner(MobilityDto mobilityDto) {
    // language=PostgreSQL
    String query = "UPDATE bmobile.mobilities SET id_partner = ?, status = ? "
        + "WHERE id = ? AND id_partner IS NULL ";

    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, mobilityDto.getIdPartner());
      preparedStatement.setString(2, mobilityDto.getStatus());
      preparedStatement.setInt(3, mobilityDto.getId());
      dalBackendServices.executeUpdate(preparedStatement);

    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException(
          "Une erreur inconnue s'est produite lors de la mise à jour de l'utilisateur.");
    }

  }

  @Override
  public int updateMobilityDetails(MobilityDto mobility) {
    String query = "UPDATE bmobile.mobilities SET status=?, amount=?, first_payment_date=?, "
        + "second_payment_date=?, departure_grant_contract=?, "
        + "departure_convention_internship_schoolarship=?, departure_student_convention=?, "
        + "departure_erasmus_language_test=?, departure_doc_aggreement=?,"
        + "depart_doc_sent_highschool=?, software_proeco=?, software_mobility_tools=?, "
        + "software_mobi= ?, return_residence_cert=?, return_transcript=?, "
        + "return_internship_cert=?,return_final_report=?, return_erasmus_language_test=?,"
        + "return_doc_sent_highschool=?, ver_nr=? WHERE id = ? AND ver_nr=?";

    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setString(1, mobility.getStatus());
      preparedStatement.setDouble(2, mobility.getAmount());
      preparedStatement.setBoolean(3, mobility.getPaymentDate1());
      preparedStatement.setBoolean(4, mobility.getPaymentDate2());
      preparedStatement.setBoolean(5, mobility.isDepartureGrantContract());
      preparedStatement.setBoolean(6, mobility.isDepartureConventionInternshipSchoolarship());
      preparedStatement.setBoolean(7, mobility.isDepartureStudentConvention());
      preparedStatement.setBoolean(8, mobility.isDepartureErasmusLanguageTest());
      preparedStatement.setBoolean(9, mobility.isDepartureDocAggreement());
      preparedStatement.setBoolean(10, mobility.isDepartDocSentHighschool());
      preparedStatement.setBoolean(11, mobility.isSoftwareProeco());
      preparedStatement.setBoolean(12, mobility.isSoftwareMobilityTools());
      preparedStatement.setBoolean(13, mobility.isSoftwareMobi());
      preparedStatement.setBoolean(14, mobility.isReturnResidenceCert());
      preparedStatement.setBoolean(15, mobility.isReturnTranscript());
      preparedStatement.setBoolean(16, mobility.isReturnInternshipCert());
      preparedStatement.setBoolean(17, mobility.isReturnFinalReport());
      preparedStatement.setBoolean(18, mobility.isReturnErasmusLanguageTest());
      preparedStatement.setBoolean(19, mobility.isReturnDocSentHighschool());
      preparedStatement.setInt(20, mobility.getVerNr() + 1);

      preparedStatement.setInt(21, mobility.getId());
      preparedStatement.setInt(22, mobility.getVerNr());

      return dalBackendServices.executeUpdate(preparedStatement);


    } catch (SQLException exc) {
      exc.printStackTrace();
      throw new UnknowErrorException();
    }
  }



  // ***********************************************//

  private MobilityDto fillDto(ResultSet resultSet) throws SQLException {
    MobilityDto mobilitydto = factory.getMobilityDto();
    mobilitydto.setId(resultSet.getInt(1));
    mobilitydto.setIdStudent(resultSet.getInt(2));
    mobilitydto.setIdProgram(resultSet.getInt(3));
    mobilitydto.setIdPartner(resultSet.getInt(4));
    mobilitydto.setType(resultSet.getString(5));
    mobilitydto.setPreferenceOrder(resultSet.getInt(6));
    mobilitydto.setIsoCountry(resultSet.getString(7));
    mobilitydto.setIdDepartment(resultSet.getString(8));
    mobilitydto.setQuadrimester(resultSet.getInt(9));
    mobilitydto.setStatus(resultSet.getString(10));
    mobilitydto.setCanceled(resultSet.getBoolean(11));
    mobilitydto.setDepartureGrantContract(resultSet.getBoolean(12));
    mobilitydto.setDepartureConventionInternshipSchoolarship(resultSet.getBoolean(13));
    mobilitydto.setDepartureStudentConvention(resultSet.getBoolean(14));
    mobilitydto.setDepartureErasmusLanguageTest(resultSet.getBoolean(15));
    mobilitydto.setDepartureDocAggreement(resultSet.getBoolean(16));
    mobilitydto.setDepartDocSentHighschool(resultSet.getBoolean(17));
    mobilitydto.setSoftwareProeco(resultSet.getBoolean(18));
    mobilitydto.setSoftwareMobilityTools(resultSet.getBoolean(19));
    mobilitydto.setSoftwareMobi(resultSet.getBoolean(20));
    mobilitydto.setReturnResidenceCert(resultSet.getBoolean(21));
    mobilitydto.setReturnTranscript(resultSet.getBoolean(22));
    mobilitydto.setReturnInternshipCert(resultSet.getBoolean(23));
    mobilitydto.setReturnFinalReport(resultSet.getBoolean(24));
    mobilitydto.setReturnErasmusLanguageTest(resultSet.getBoolean(25));
    mobilitydto.setReturnDocSentHighschool(resultSet.getBoolean(26));
    mobilitydto.setCancelationReason(resultSet.getInt(27));
    mobilitydto.setAcademicYear(resultSet.getString(28));
    mobilitydto.setVerNr(resultSet.getInt(29));
    mobilitydto.setAmount(resultSet.getDouble(30));
    mobilitydto.setPaymentDate1(resultSet.getBoolean(31));
    mobilitydto.setPaymentDate2(resultSet.getBoolean(32));

    return mobilitydto;
  }

  private MobilityDto fillFullDto(ResultSet resultSet) throws SQLException {

    MobilityDto mobilitydto = fillDto(resultSet);
    mobilitydto.setStudentDto(factory.getUserDto());
    mobilitydto.getStudentDto().setId(resultSet.getInt(33));
    mobilitydto.getStudentDto().setIdDepartment(resultSet.getString(34));
    mobilitydto.getStudentDto().setPseudo(resultSet.getString(35));
    // mobilitydto.getStudentDto().setPassword(resultSet.getString(33));
    mobilitydto.getStudentDto().setName(resultSet.getString(36));
    mobilitydto.getStudentDto().setFirstname(resultSet.getString(37));
    mobilitydto.getStudentDto().setEmail(resultSet.getString(38));

    Timestamp registrationDate = resultSet.getTimestamp(39);
    if (null != registrationDate) {
      mobilitydto.getStudentDto()
          .setRegistrationDate(registrationDate.toLocalDateTime().toLocalDate());
    }
    mobilitydto.getStudentDto().setPermissions(resultSet.getString(40));
    Timestamp birthdate = resultSet.getTimestamp(41);
    if (null != birthdate) {
      mobilitydto.getStudentDto().setBirthDate(birthdate.toLocalDateTime().toLocalDate());
    }
    mobilitydto.getStudentDto().setStreet(resultSet.getString(42));
    mobilitydto.getStudentDto().setHouseNumber(resultSet.getString(43));
    mobilitydto.getStudentDto().setMailBox(resultSet.getString(44));
    mobilitydto.getStudentDto().setZip(resultSet.getString(45));
    mobilitydto.getStudentDto().setCity(resultSet.getString(46));
    mobilitydto.getStudentDto().setCountry(resultSet.getString(47));
    mobilitydto.getStudentDto().setTel(resultSet.getString(48));
    mobilitydto.getStudentDto().setGender(resultSet.getString(49));
    mobilitydto.getStudentDto().setSuccessfullYearInCollege(resultSet.getInt(50));
    mobilitydto.getStudentDto().setIban(resultSet.getString(51));
    mobilitydto.getStudentDto().setBic(resultSet.getString(52));
    mobilitydto.getStudentDto().setAccountHolder(resultSet.getString(53));
    mobilitydto.getStudentDto().setBankName(resultSet.getString(54));
    mobilitydto.getStudentDto().setCitizenship(resultSet.getString(55));
    mobilitydto.getStudentDto().setVerNr(resultSet.getInt(56));

    mobilitydto.setProgramDto(factory.getProgramDto());
    mobilitydto.getProgramDto().setId(resultSet.getInt(57));
    mobilitydto.getProgramDto().setName(resultSet.getString(58));
    mobilitydto.getProgramDto().setDescription(resultSet.getString(59));
    mobilitydto.getProgramDto().setVerNr(resultSet.getInt(60));

    mobilitydto.setPartnerDto(factory.getPartnerDto());
    mobilitydto.getPartnerDto().setId(resultSet.getInt(61));
    mobilitydto.getPartnerDto().setIdUser(resultSet.getInt(62));
    mobilitydto.getPartnerDto().setLegalName(resultSet.getString(63));
    mobilitydto.getPartnerDto().setBusiness(resultSet.getString(64));
    mobilitydto.getPartnerDto().setFullName(resultSet.getString(65));
    mobilitydto.getPartnerDto().setDepartment(resultSet.getString(66));
    mobilitydto.getPartnerDto().setType(resultSet.getString(67));
    mobilitydto.getPartnerDto().setNbEmployees(resultSet.getInt(68));
    mobilitydto.getPartnerDto().setStreet(resultSet.getString(69));
    mobilitydto.getPartnerDto().setNumber(resultSet.getString(70));
    mobilitydto.getPartnerDto().setMailbox(resultSet.getString(71));
    mobilitydto.getPartnerDto().setZip(resultSet.getString(72));
    mobilitydto.getPartnerDto().setCity(resultSet.getString(73));
    mobilitydto.getPartnerDto().setState(resultSet.getString(74));
    mobilitydto.getPartnerDto().setCountry(resultSet.getString(75));
    mobilitydto.getPartnerDto().setEmail(resultSet.getString(76));
    mobilitydto.getPartnerDto().setWebsite(resultSet.getString(77));
    mobilitydto.getPartnerDto().setExists(resultSet.getBoolean(78));
    mobilitydto.getPartnerDto().setVerNr(resultSet.getInt(79));

    mobilitydto.setCountryDto(factory.getCountryDto());
    mobilitydto.getCountryDto().setIso(resultSet.getString(80));
    mobilitydto.getCountryDto().setNameEn(resultSet.getString(81));
    mobilitydto.getCountryDto().setNameFr(resultSet.getString(82));
    mobilitydto.getCountryDto().setIdProgram(resultSet.getInt(83));

    mobilitydto.setDepartmentDto(factory.getDepartmentDto());
    mobilitydto.getDepartmentDto().setId(resultSet.getString(84));
    mobilitydto.getDepartmentDto().setLabel(resultSet.getString(85));
    mobilitydto.getDepartmentDto().setVerNr(resultSet.getInt(86));

    mobilitydto.setCancelationDto(factory.getCancelationDto());
    mobilitydto.getCancelationDto().setId(resultSet.getInt(87));
    mobilitydto.getCancelationDto().setReason(resultSet.getString(88));
    mobilitydto.getCancelationDto().setResponsible(resultSet.getString(89));
    mobilitydto.getCancelationDto().setVerNr(resultSet.getInt(90));



    return mobilitydto;
  }

}
