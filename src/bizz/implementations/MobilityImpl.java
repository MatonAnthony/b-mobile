package bizz.implementations;

import bizz.interfaces.MobilityBizz;
import dto.CancelationDto;
import dto.CountryDto;
import dto.DepartmentDto;
import dto.PartnerDto;
import dto.ProgramDto;
import dto.UserDto;

public class MobilityImpl implements MobilityBizz, Cloneable {
  private int id;
  private int idStudent;
  private UserDto studentDto;
  private int idProgram;
  private ProgramDto programDto;
  private int idPartner;
  private PartnerDto partnerDto;
  private String type;
  private int preferenceOrder;
  private String isoCountry;
  private CountryDto countryDto;
  private String idDepartment;
  private DepartmentDto departmentDto;
  private int quadrimester;
  private String status;
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
  private int cancelationReason;
  private CancelationDto cancelationDto;
  private String academicYear;
  private int verNr;
  private double amount;
  private boolean paymentDate1;
  private boolean paymentDate2;

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

  public UserDto getStudentDto() {
    return studentDto;
  }

  public void setStudentDto(UserDto studentDto) {
    this.studentDto = studentDto;
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

  public CountryDto getCountryDto() {
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
  public String getIsoCountry() {
    return isoCountry;
  }

  /**
   * Sets the country.
   * 
   * @param isoCountry the country to set.
   */
  @Override
  public void setIsoCountry(String isoCountry) {
    this.isoCountry = isoCountry;
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
    return super.clone();
  }

  public DepartmentDto getDepartmentDto() {
    return departmentDto;
  }

  public void setDepartmentDto(DepartmentDto departmentDto) {
    this.departmentDto = departmentDto;
  }

  public CancelationDto getCancelationDto() {
    return cancelationDto;
  }

  public void setCancelationDto(CancelationDto cancelationDto) {
    this.cancelationDto = cancelationDto;
  }

  public boolean getPaymentDate1() {
    return paymentDate1;
  }

  public void setPaymentDate1(boolean paymentDate1) {
    this.paymentDate1 = paymentDate1;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public boolean getPaymentDate2() {
    return paymentDate2;
  }

  public void setPaymentDate2(boolean paymentDate2) {
    this.paymentDate2 = paymentDate2;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((academicYear == null) ? 0 : academicYear.hashCode());
    long temp;
    temp = Double.doubleToLongBits(amount);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((cancelationDto == null) ? 0 : cancelationDto.hashCode());
    result = prime * result + cancelationReason;
    result = prime * result + (canceled ? 1231 : 1237);
    result = prime * result + ((countryDto == null) ? 0 : countryDto.hashCode());
    result = prime * result + (departDocSentHighschool ? 1231 : 1237);
    result = prime * result + ((departmentDto == null) ? 0 : departmentDto.hashCode());
    result = prime * result + (departureConventionInternshipSchoolarship ? 1231 : 1237);
    result = prime * result + (departureDocAggreement ? 1231 : 1237);
    result = prime * result + (departureErasmusLanguageTest ? 1231 : 1237);
    result = prime * result + (departureGrantContract ? 1231 : 1237);
    result = prime * result + (departureStudentConvention ? 1231 : 1237);
    result = prime * result + id;
    result = prime * result + ((idDepartment == null) ? 0 : idDepartment.hashCode());
    result = prime * result + idPartner;
    result = prime * result + idProgram;
    result = prime * result + idStudent;
    result = prime * result + ((isoCountry == null) ? 0 : isoCountry.hashCode());
    result = prime * result + ((partnerDto == null) ? 0 : partnerDto.hashCode());
    result = prime * result + (paymentDate1 ? 1231 : 1237);
    result = prime * result + (paymentDate2 ? 1231 : 1237);
    result = prime * result + preferenceOrder;
    result = prime * result + ((programDto == null) ? 0 : programDto.hashCode());
    result = prime * result + quadrimester;
    result = prime * result + (returnDocSentHighschool ? 1231 : 1237);
    result = prime * result + (returnErasmusLanguageTest ? 1231 : 1237);
    result = prime * result + (returnFinalReport ? 1231 : 1237);
    result = prime * result + (returnInternshipCert ? 1231 : 1237);
    result = prime * result + (returnResidenceCert ? 1231 : 1237);
    result = prime * result + (returnTranscript ? 1231 : 1237);
    result = prime * result + (softwareMobi ? 1231 : 1237);
    result = prime * result + (softwareMobilityTools ? 1231 : 1237);
    result = prime * result + (softwareProeco ? 1231 : 1237);
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    result = prime * result + ((studentDto == null) ? 0 : studentDto.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + verNr;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    MobilityImpl other = (MobilityImpl) obj;
    if (academicYear == null) {
      if (other.academicYear != null) {
        return false;
      }
    } else if (!academicYear.equals(other.academicYear)) {
      return false;
    }
    if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount)) {
      return false;
    }
    if (cancelationDto == null) {
      if (other.cancelationDto != null) {
        return false;
      }
    } else if (!cancelationDto.equals(other.cancelationDto)) {
      return false;
    }
    if (cancelationReason != other.cancelationReason) {
      return false;
    }
    if (canceled != other.canceled) {
      return false;
    }
    if (countryDto == null) {
      if (other.countryDto != null) {
        return false;
      }
    } else if (!countryDto.equals(other.countryDto)) {
      return false;
    }
    if (departDocSentHighschool != other.departDocSentHighschool) {
      return false;
    }
    if (departmentDto == null) {
      if (other.departmentDto != null) {
        return false;
      }
    } else if (!departmentDto.equals(other.departmentDto)) {
      return false;
    }
    if (departureConventionInternshipSchoolarship != other.departureConventionInternshipSchoolarship) {
      return false;
    }
    if (departureDocAggreement != other.departureDocAggreement) {
      return false;
    }
    if (departureErasmusLanguageTest != other.departureErasmusLanguageTest) {
      return false;
    }
    if (departureGrantContract != other.departureGrantContract) {
      return false;
    }
    if (departureStudentConvention != other.departureStudentConvention) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (idDepartment == null) {
      if (other.idDepartment != null) {
        return false;
      }
    } else if (!idDepartment.equals(other.idDepartment)) {
      return false;
    }
    if (idPartner != other.idPartner) {
      return false;
    }
    if (idProgram != other.idProgram) {
      return false;
    }
    if (idStudent != other.idStudent) {
      return false;
    }
    if (isoCountry == null) {
      if (other.isoCountry != null) {
        return false;
      }
    } else if (!isoCountry.equals(other.isoCountry)) {
      return false;
    }
    if (partnerDto == null) {
      if (other.partnerDto != null) {
        return false;
      }
    } else if (!partnerDto.equals(other.partnerDto)) {
      return false;
    }
    if (paymentDate1 != other.paymentDate1) {
      return false;
    }
    if (paymentDate2 != other.paymentDate2) {
      return false;
    }
    if (preferenceOrder != other.preferenceOrder) {
      return false;
    }
    if (programDto == null) {
      if (other.programDto != null) {
        return false;
      }
    } else if (!programDto.equals(other.programDto)) {
      return false;
    }
    if (quadrimester != other.quadrimester) {
      return false;
    }
    if (returnDocSentHighschool != other.returnDocSentHighschool) {
      return false;
    }
    if (returnErasmusLanguageTest != other.returnErasmusLanguageTest) {
      return false;
    }
    if (returnFinalReport != other.returnFinalReport) {
      return false;
    }
    if (returnInternshipCert != other.returnInternshipCert) {
      return false;
    }
    if (returnResidenceCert != other.returnResidenceCert) {
      return false;
    }
    if (returnTranscript != other.returnTranscript) {
      return false;
    }
    if (softwareMobi != other.softwareMobi) {
      return false;
    }
    if (softwareMobilityTools != other.softwareMobilityTools) {
      return false;
    }
    if (softwareProeco != other.softwareProeco) {
      return false;
    }
    if (status == null) {
      if (other.status != null) {
        return false;
      }
    } else if (!status.equals(other.status)) {
      return false;
    }
    if (studentDto == null) {
      if (other.studentDto != null) {
        return false;
      }
    } else if (!studentDto.equals(other.studentDto)) {
      return false;
    }
    if (type == null) {
      if (other.type != null) {
        return false;
      }
    } else if (!type.equals(other.type)) {
      return false;
    }
    if (verNr != other.verNr) {
      return false;
    }
    return true;
  }


}
