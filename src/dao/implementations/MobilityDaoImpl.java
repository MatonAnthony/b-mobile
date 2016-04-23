package dao.implementations;

import bizz.interfaces.BizzFactory;
import dal.DalBackendServices;
import dao.interfaces.MobilityDao;
import dto.MobilityDto;
import exceptions.UnknowErrorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MobilityDaoImpl implements MobilityDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  // query for select mobilities
  private String query =
      "SELECT id, id_student, id_program, id_partner, type, preference_order, country,"
          + " id_department, quadrimester, status, canceled, departure_grant_contract,"
          + " departure_convention_internship_schoolarship, departure_student_convention,"
          + " departure_erasmus_language_test, departure_doc_aggreement,"
          + " depart_doc_sent_highschool, software_proeco, software_mobility_tools,"
          + " software_mobi, return_residence_cert, return_transcript, return_internship_cert,"
          + " return_final_report, return_erasmus_language_test, return_doc_sent_highschool, "
          + "cancelation_reason, academic_year, ver_nr, amount, first_payment_date, second_payment_date "
          + "FROM bmobile.mobilities";

  // query for select mobilities full
  private String queryFull =
      "SELECT m.id, m.id_student, m.id_program, m.id_partner, m.type, m.preference_order,"
          + " m.country, m.id_department, m.quadrimester, m.status, m.canceled,"
          + " m.departure_grant_contract, m.departure_convention_internship_schoolarship, "
          + "m.departure_student_convention, m.departure_erasmus_language_test, "
          + "m.departure_doc_aggreement, m.depart_doc_sent_highschool, m.software_proeco,"
          + " m.software_mobility_tools, m.software_mobi, m.return_residence_cert, "
          + "m.return_transcript, m.return_internship_cert, m.return_final_report, "
          + "m.return_erasmus_language_test, m.return_doc_sent_highschool, m.cancelation_reason, "
          + "m.academic_year, m.ver_nr, amount, first_payment_date, second_payment_date,  "

          + "u.id, u.id_department, u.pseudo, u.name, u.firstname, u.email, u.registration_date,"
          + " u.permissions, u.birth_date, u.street, u.house_number, u.mailbox, u.zip, u.city, "
          + "u.country, u.tel, u.gender, u.successfull_year_in_college, u.iban, u.bic, "
          + "u.account_holder, u.bank_name, u.ver_nr, "

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
    // TODO (Martin) Comment gérer l'année académique
    String query =
        "INSERT INTO bmobile.mobilities VALUES (DEFAULT,?,?,NULL,?,?,?,?,?,'En attente',0,NULL,NULL,"
            + "FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,"
            + "FALSE,FALSE,FALSE,NULL,?,0)";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      preparedStatement.setInt(1, mobilityDto.getStudentDto().getId());
      preparedStatement.setInt(2, mobilityDto.getProgramDto().getId());
      preparedStatement.setString(3, mobilityDto.getType());
      preparedStatement.setInt(4, mobilityDto.getPreferenceOrder());
      preparedStatement.setString(5, mobilityDto.getCountryDto().getIso());
      preparedStatement.setString(6, mobilityDto.getDepartementDto().getId());
      preparedStatement.setInt(7, mobilityDto.getQuadrimester());
      preparedStatement.setString(8, mobilityDto.getAcademicYear());

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
          "Une erreur inconnue s'est produite lors du chargement des mobilités de l'utilisateur (complètes).");
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
    Timestamp paymentDate1 = resultSet.getTimestamp(31);
    if (null != paymentDate1) {
      mobilitydto.setPaymentDate1(paymentDate1.toLocalDateTime().toLocalDate());
    }
    Timestamp paymentDate2 = resultSet.getTimestamp(32);
    if (null != paymentDate2) {
      mobilitydto.setPaymentDate2(paymentDate2.toLocalDateTime().toLocalDate());
    }
    return mobilitydto;
  }

  private MobilityDto fillFullDto(ResultSet resultSet) throws SQLException {

    MobilityDto mobilitydto = fillDto(resultSet);
    mobilitydto.setStudentDto(factory.getUserDto());
    mobilitydto.getStudentDto().setId(resultSet.getInt(33));
    mobilitydto.getStudentDto().setIdDepartment(resultSet.getInt(34));
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
    mobilitydto.getStudentDto().setVerNr(resultSet.getInt(55));

    mobilitydto.setProgramDto(factory.getProgramDto());
    mobilitydto.getProgramDto().setId(resultSet.getInt(56));
    mobilitydto.getProgramDto().setName(resultSet.getString(57));
    mobilitydto.getProgramDto().setDescription(resultSet.getString(58));
    mobilitydto.getProgramDto().setVerNr(resultSet.getInt(59));

    mobilitydto.setPartnerDto(factory.getPartnerDto());
    mobilitydto.getPartnerDto().setId(resultSet.getInt(60));
    mobilitydto.getPartnerDto().setIdUser(resultSet.getInt(61));
    mobilitydto.getPartnerDto().setLegalName(resultSet.getString(62));
    mobilitydto.getPartnerDto().setBusiness(resultSet.getString(63));
    mobilitydto.getPartnerDto().setFullName(resultSet.getString(64));
    mobilitydto.getPartnerDto().setDepartment(resultSet.getString(65));
    mobilitydto.getPartnerDto().setType(resultSet.getString(66));
    mobilitydto.getPartnerDto().setNbEmployees(resultSet.getInt(67));
    mobilitydto.getPartnerDto().setStreet(resultSet.getString(68));
    mobilitydto.getPartnerDto().setNumber(resultSet.getString(69));
    mobilitydto.getPartnerDto().setMailbox(resultSet.getString(70));
    mobilitydto.getPartnerDto().setZip(resultSet.getString(71));
    mobilitydto.getPartnerDto().setCity(resultSet.getString(72));
    mobilitydto.getPartnerDto().setState(resultSet.getString(73));
    mobilitydto.getPartnerDto().setCountry(resultSet.getString(74));
    mobilitydto.getPartnerDto().setEmail(resultSet.getString(75));
    mobilitydto.getPartnerDto().setWebsite(resultSet.getString(76));
    mobilitydto.getPartnerDto().setExists(resultSet.getBoolean(77));
    mobilitydto.getPartnerDto().setVerNr(resultSet.getInt(78));

    mobilitydto.setCountryDto(factory.getCountryDto());
    mobilitydto.getCountryDto().setIso(resultSet.getString(79));
    mobilitydto.getCountryDto().setNameEn(resultSet.getString(80));
    mobilitydto.getCountryDto().setNameFr(resultSet.getString(81));
    mobilitydto.getCountryDto().setIdProgram(resultSet.getInt(82));

    mobilitydto.setDepartementDto(factory.getDepartmentDto());
    mobilitydto.getDepartementDto().setId(resultSet.getString(83));
    mobilitydto.getDepartementDto().setLabel(resultSet.getString(84));
    mobilitydto.getDepartementDto().setVerNr(resultSet.getInt(85));

    mobilitydto.setCancelationDto(factory.getCancelationDto());
    mobilitydto.getCancelationDto().setId(resultSet.getInt(86));
    mobilitydto.getCancelationDto().setReason(resultSet.getString(87));
    mobilitydto.getCancelationDto().setResponsible(resultSet.getString(88));
    mobilitydto.getCancelationDto().setVerNr(resultSet.getInt(89));

    return mobilitydto;
  }

}
