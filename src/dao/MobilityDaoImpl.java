package dao;

import bizz.BizzFactory;
import dal.DalBackendServices;
import dto.MobilityDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MobilityDaoImpl implements MobilityDao {

  private DalBackendServices dalBackendServices;
  private BizzFactory factory;

  public MobilityDaoImpl(DalBackendServices dalBackendServices, BizzFactory bizzFactory) {
    this.dalBackendServices = dalBackendServices;
    this.factory = bizzFactory;
  }

  @Override
  public boolean createMobility(MobilityDto mobilityDto) {
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

  public ArrayList<MobilityDto> findAllMobility() {
    String query =
        "SELECT id, id_student, id_program, id_partner, type, preference_order, country, id_department, "
            + "quadrimester, status, canceled, departure_grant_contract, departure_convention_internship_schoolarship,"
            + " departure_student_convention, departure_erasmus_language_test, departure_doc_aggreement, "
            + "depart_doc_sent_highschool, software_proeco, software_mobility_tools, software_mobi, return_residence_cert, "
            + "return_transcript, return_internship_cert, return_final_report, return_erasmus_language_test, "
            + "return_doc_sent_highschool, cancelation_reason, academic_year, ver_nr "
            + "FROM bmobile.mobilities";
    return fillArrayDto(query);
  }

  public ArrayList<MobilityDto> findConfirmedMobility() {
    String query =
        "SELECT id, id_student, id_program, id_partner, type, preference_order, country, id_department, "
            + "quadrimester, status, canceled, departure_grant_contract, departure_convention_internship_schoolarship,"
            + " departure_student_convention, departure_erasmus_language_test, departure_doc_aggreement, "
            + "depart_doc_sent_highschool, software_proeco, software_mobility_tools, software_mobi, return_residence_cert, "
            + "return_transcript, return_internship_cert, return_final_report, return_erasmus_language_test, "
            + "return_doc_sent_highschool, cancelation_reason, academic_year, ver_nr "
            + "FROM bmobile.mobilities WHERE status != 'PENDING' OR canceled = 'false'";
    return fillArrayDto(query);
  }

  private ArrayList<MobilityDto> fillArrayDto(String query) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = dalBackendServices.prepare(query);
      ArrayList<MobilityDto> mobilities = new ArrayList<MobilityDto>();
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          MobilityDto mobilitydto = factory.getMobilityDto();
          mobilitydto.setId(resultSet.getInt(1));
          mobilitydto.setIdStudent(resultSet.getInt(2));
          mobilitydto.setIdProgram(resultSet.getInt(3));
          mobilitydto.setIdPartner(resultSet.getInt(4));
          mobilitydto.setType(resultSet.getString(5));
          mobilitydto.setPreferenceOrder(resultSet.getInt(6));
          mobilitydto.setCountry(resultSet.getString(7));
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
          mobilities.add(mobilitydto);
        }
        return mobilities;
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
