package bizz.implementations;

import bizz.interfaces.MobilityBizz;
import dto.CountryDto;
import dto.DepartmentDto;
import dto.PartnerDto;
import dto.ProgramDto;
import dto.UserDto;

public class MobilityImpl implements MobilityBizz, Cloneable {
  // TODO(fany) ajouter dto et leur get et set
  private int id;
  private int idStudent;
  private UserDto userDto;
  private int idProgram;
  private ProgramDto programDto;
  private int idPartner;
  private PartnerDto partnerDto;
  private int preferenceOrder;
  private String idDepartment;
  private DepartmentDto departmentDto;
  private int quadrimester;
  private int verNr;
  private int cancelationReason;
  // private CancelationDto cancelationDto;
  private String type;
  private String country;// iso
  private CountryDto countryDto;
  private String status;
  private String academicYear;
  private boolean canceled;
  private boolean departureGrantContract;
  private boolean departureConventionInternshipSchoolarship;
  private boolean departureStudentConvention;
  private boolean departureErasmusLanguageTest;
  private boolean departureDocAggreement;
  private boolean departDocSentHighschool;
  private boolean softwareProeco;
  private boolean softwareMobilityTools;
  private boolean softwareMobi;
  private boolean returnResidenceCert;
  private boolean returnTranscript;
  private boolean returnInternshipCert;
  private boolean returnFinalReport;
  private boolean returnErasmusLanguageTest;
  private boolean returnDocSentHighschool;

  /**
   * Gets the id .
   * 
   * @return the idMobility.
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * Sets the id of this mobility.
   * 
   * @param id the id of the mobility to set.
   */
  @Override
  public void setId(int id) {
    this.id = id;
  }

  public UserDto getUserDto() {
    return userDto;
  }

  public void setUserDto(UserDto userDto) {
    this.userDto = userDto;
  }

  public ProgramDto getProgramDto() {
    return programDto;
  }

  public void setProgramDto(ProgramDto programDto) {
    this.programDto = programDto;
  }

  public PartnerDto getPartnerDto() {
    return partnerDto;
  }

  public void setPartnerDto(PartnerDto partnerDto) {
    this.partnerDto = partnerDto;
  }

  public DepartmentDto getDepartementDto() {
    if (departmentDto == null) {
      // departmentDto = ;
    }
    return departmentDto;
  }

  public void setDepartementDto(DepartmentDto departmentDto) {
    this.departmentDto = departmentDto;
  }

  public CountryDto getCountryDto() {
    if (countryDto == null) {

    }
    return countryDto;
  }

  public void setCountryDto(CountryDto countryDto) {
    this.countryDto = countryDto;
  }

  /**
   * Gets the id of the student .
   * 
   * @return the idStudent.
   */
  @Override
  public int getIdStudent() {
    return idStudent;
  }

  /**
   * Sets the id of the student .
   * 
   * @param idStudent the idStudent to set.
   */
  @Override
  public void setIdStudent(int idStudent) {
    this.idStudent = idStudent;
  }

  /**
   * Gets the id of the program .
   * 
   * @return the idProgram.
   */
  @Override
  public int getIdProgram() {
    return idProgram;
  }

  /**
   * Sets the id of the program
   * 
   * @param idProgram the idProgram to set.
   */
  @Override
  public void setIdProgram(int idProgram) {
    this.idProgram = idProgram;
  }

  /**
   * Gets the id of the partner .
   * 
   * @return the idPartner.
   */
  @Override
  public int getIdPartner() {
    return idPartner;
  }

  /**
   * Sets the id of the partner .
   * 
   * @param idPartner the idPartner to set.
   */
  @Override
  public void setIdPartner(int idPartner) {
    this.idPartner = idPartner;
  }

  /**
   * Gets the preference order .
   * 
   * @return the preferenceOrder.
   */
  @Override
  public int getPreferenceOrder() {
    return preferenceOrder;
  }

  /**
   * Sets the preference order.
   * 
   * @param preferenceOrder the preferenceOrder to set.
   */
  @Override
  public void setPreferenceOrder(int preferenceOrder) {
    this.preferenceOrder = preferenceOrder;
  }

  /**
   * Gets the id of the department.
   * 
   * @return the idDepartment.
   */
  @Override
  public String getIdDepartment() {
    return idDepartment;
  }

  /**
   * Sets the id of the department.
   * 
   * @param idDepartment the idDepartment to set.
   */
  @Override
  public void setIdDepartment(String idDepartment) {
    this.idDepartment = idDepartment;
  }

  /**
   * Gets the quadrimester (first or second).
   * 
   * @return the quadrimester.
   */
  @Override
  public int getQuadrimester() {
    return quadrimester;
  }

  /**
   * Sets the quadrimester (first or second).
   * 
   * @param quadrimester the quadrimester to set.
   */
  @Override
  public void setQuadrimester(int quadrimester) {
    this.quadrimester = quadrimester;
  }

  /**
   * Gets the version number.
   * 
   * @return the verNr.
   */
  @Override
  public int getVerNr() {
    return verNr;
  }

  /**
   * Sets the version number.
   * 
   * @param verNr the verNr to set.
   */
  @Override
  public void setVerNr(int verNr) {
    this.verNr = verNr;
  }

  /**
   * Gets the cancelation reason.
   * 
   * @return the cancelationReason.
   */
  @Override
  public int getCancelationReason() {
    return cancelationReason;
  }

  /**
   * Sets the cancelation reason.
   * 
   * @param cancelationReason the cancelationReason to set.
   */
  @Override
  public void setCancelationReason(int cancelationReason) {
    this.cancelationReason = cancelationReason;
  }

  /**
   * Gets the type.
   * 
   * @return the type.
   */
  @Override
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   * 
   * @param type the type to set.
   */
  @Override
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the country.
   * 
   * @return the country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country.
   * 
   * @param country the country to set.
   */
  @Override
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Gets the status.
   * 
   * @return the status.
   */
  @Override
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status.
   * 
   * @param status the status to set.
   */
  @Override
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the academic year.
   * 
   * @return the academicYear.
   */
  @Override
  public String getAcademicYear() {
    return academicYear;
  }

  /**
   * Sets the academic year.
   * 
   * @param academicYear the academicYear to set.
   */
  @Override
  public void setAcademicYear(String academicYear) {
    this.academicYear = academicYear;
  }

  /**
   * Specifies the validity of the moblity.
   * 
   * @return true if the mobility is canceled, else return false.
   */
  @Override
  public boolean isCanceled() {
    return canceled;
  }

  /**
   * Sets the validity.
   * 
   * @param canceled the canceled to set.
   */
  @Override
  public void setCanceled(boolean canceled) {
    this.canceled = canceled;
  }

  /**
   * Specifies if the departureGrantContract is signed.
   * 
   * @return the departureGrantContract.
   */
  @Override
  public boolean isDepartureGrantContract() {
    return departureGrantContract;
  }

  /**
   * Sets the DepartureGrantContract to specify if it's signed.
   * 
   * @param departureGrantContract the departureGrantContract to set.
   */
  @Override
  public void setDepartureGrantContract(boolean departureGrantContract) {
    this.departureGrantContract = departureGrantContract;
  }

  /**
   * Specifies if the departureConventionInternshipSchoolarship is signed.
   * 
   * @return the departureConventionInternshipSchoolarship.
   */
  @Override
  public boolean isDepartureConventionInternshipSchoolarship() {
    return departureConventionInternshipSchoolarship;
  }

  /**
   * Sets the departureConventionInternshipSchoolarship to specify if it's signed.
   * 
   * @param departureConventionInternshipSchoolarship the departureConventionInternshipSchoolarship
   *        to set.
   */
  @Override
  public void setDepartureConventionInternshipSchoolarship(
      boolean departureConventionInternshipSchoolarship) {
    this.departureConventionInternshipSchoolarship = departureConventionInternshipSchoolarship;
  }

  /**
   * Specifies if the departureStudentConvention is signed.
   * 
   * @return the departureStudentConvention.
   */
  @Override
  public boolean isDepartureStudentConvention() {
    return departureStudentConvention;
  }

  /**
   * Sets the departureStudentConvention to specify if it's signed.
   * 
   * @param departureStudentConvention the departureStudentConvention to set.
   */
  @Override
  public void setDepartureStudentConvention(boolean departureStudentConvention) {
    this.departureStudentConvention = departureStudentConvention;
  }

  /**
   * Specifies if the departureErasmusLanguageTest is done.
   * 
   * @return the departureErasmusLanguageTest.
   */
  @Override
  public boolean isDepartureErasmusLanguageTest() {
    return departureErasmusLanguageTest;
  }

  /**
   * Sets the departureErasmusLanguageTest to specify if it's done.
   * 
   * @param departureErasmusLanguageTest the departureErasmusLanguageTest to set.
   */
  @Override
  public void setDepartureErasmusLanguageTest(boolean departureErasmusLanguageTest) {
    this.departureErasmusLanguageTest = departureErasmusLanguageTest;
  }

  /**
   * Specifies if the departureDocAggreement is signed.
   * 
   * @return the departureDocAggreement.
   */
  @Override
  public boolean isDepartureDocAggreement() {
    return departureDocAggreement;
  }

  /**
   * Sets the departureDocAggreement to specify if it's signed.
   * 
   * @param departureDocAggreement the departureDocAggreement to set.
   */
  @Override
  public void setDepartureDocAggreement(boolean departureDocAggreement) {
    this.departureDocAggreement = departureDocAggreement;
  }

  /**
   * Specifies if the departDocSentHighschool is signed.
   * 
   * @return the departDocSentHighschool.
   */
  @Override
  public boolean isDepartDocSentHighschool() {
    return departDocSentHighschool;
  }

  /**
   * Sets the departDocSentHighschool to specify if it's signed.
   * 
   * @param departDocSentHighschool the departDocSentHighschool to set.
   */
  @Override
  public void setDepartDocSentHighschool(boolean departDocSentHighschool) {
    this.departDocSentHighschool = departDocSentHighschool;
  }

  /**
   * Specifies if the departDocSentHighschool is checked.
   * 
   * @return the softwareProeco.
   */
  @Override
  public boolean isSoftwareProeco() {
    return softwareProeco;
  }

  /**
   * Sets the softwareProeco to specify if it's checked.
   * 
   * @param softwareProeco the softwareProeco to set.
   */
  @Override
  public void setSoftwareProeco(boolean softwareProeco) {
    this.softwareProeco = softwareProeco;
  }

  /**
   * Specifies if the softwareMobilityTools is checked.
   * 
   * @return the softwareMobilityTools.
   */
  @Override
  public boolean isSoftwareMobilityTools() {
    return softwareMobilityTools;
  }

  /**
   * Sets the softwareMobilityTools to specify if it's checked.
   * 
   * @param softwareMobilityTools the softwareMobilityTools to set.
   */
  @Override
  public void setSoftwareMobilityTools(boolean softwareMobilityTools) {
    this.softwareMobilityTools = softwareMobilityTools;
  }

  /**
   * Specifies if the softwareMobi is checked.
   * 
   * @return the softwareMobi.
   */
  @Override
  public boolean isSoftwareMobi() {
    return softwareMobi;
  }

  /**
   * Sets the softwareMobi to specify if it's checked.
   * 
   * @param softwareMobi the softwareMobi to set.
   */
  @Override
  public void setSoftwareMobi(boolean softwareMobi) {
    this.softwareMobi = softwareMobi;
  }

  /**
   * Specifies if the returnResidenceCert is signed.
   * 
   * @return the returnResidenceCert.
   */
  @Override
  public boolean isReturnResidenceCert() {
    return returnResidenceCert;
  }

  /**
   * Sets the returnResidenceCert to specify if it's signed.
   * 
   * @param returnResidenceCert the returnResidenceCert to set.
   */
  @Override
  public void setReturnResidenceCert(boolean returnResidenceCert) {
    this.returnResidenceCert = returnResidenceCert;
  }

  /**
   * Specifies if the returnTranscript is given.
   * 
   * @return the returnTranscript.
   */
  @Override
  public boolean isReturnTranscript() {
    return returnTranscript;
  }

  /**
   * Sets the returnTranscript to specify if it's given.
   * 
   * @param returnTranscript the returnTranscript to set.
   */
  @Override
  public void setReturnTranscript(boolean returnTranscript) {
    this.returnTranscript = returnTranscript;
  }

  /**
   * Specifies if the returnInternshipCert is given.
   * 
   * @return the returnInternshipCert.
   */
  @Override
  public boolean isReturnInternshipCert() {
    return returnInternshipCert;
  }

  /**
   * Sets the returnInternshipCert to specify if it's given.
   * 
   * @param returnInternshipCert the returnInternshipCert to set.
   */
  @Override
  public void setReturnInternshipCert(boolean returnInternshipCert) {
    this.returnInternshipCert = returnInternshipCert;
  }

  /**
   * Specifies if the returnFinalReport is given.
   * 
   * @return the returnFinalReport.
   */
  @Override
  public boolean isReturnFinalReport() {
    return returnFinalReport;
  }

  /**
   * Sets the returnFinalReport to specify if it's given.
   * 
   * @param returnFinalReport the returnFinalReport to set.
   */
  @Override
  public void setReturnFinalReport(boolean returnFinalReport) {
    this.returnFinalReport = returnFinalReport;
  }

  /**
   * Specifies if the returnErasmusLanguageTest is done.
   * 
   * @return the returnErasmusLanguageTest.
   */
  @Override
  public boolean isReturnErasmusLanguageTest() {
    return returnErasmusLanguageTest;
  }

  /**
   * Sets the returnErasmusLanguageTest to specify if it's done.
   * 
   * @param returnErasmusLanguageTest the returnErasmusLanguageTest to set.
   */
  @Override
  public void setReturnErasmusLanguageTest(boolean returnErasmusLanguageTest) {
    this.returnErasmusLanguageTest = returnErasmusLanguageTest;
  }

  /**
   * Specifies if the returnDocSentHighschool is given.
   * 
   * @return the returnDocSentHighschool.
   */
  @Override
  public boolean isReturnDocSentHighschool() {
    return returnDocSentHighschool;
  }

  /**
   * Sets the returnDocSentHighschool to specify if it's done..
   * 
   * @param returnDocSentHighschool the returnDocSentHighschool to set.
   */
  @Override
  public void setReturnDocSentHighschool(boolean returnDocSentHighschool) {
    this.returnDocSentHighschool = returnDocSentHighschool;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    // TODO (Jonathan) Auto-generated method stub
    return super.clone();
  }

}
