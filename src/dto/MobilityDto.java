package dto;

/**
 * The interface Mobility dto.
 */
public interface MobilityDto {

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId();

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(int id);

  /**
   * Gets student dto.
   *
   * @return the student dto
   */
  public UserDto getStudentDto();

  /**
   * Sets student dto.
   *
   * @param studentDto the student dto
   */
  public void setStudentDto(UserDto studentDto);

  /**
   * Gets program dto.
   *
   * @return the program dto
   */
  public ProgramDto getProgramDto();

  /**
   * Sets program dto.
   *
   * @param programDto the program dto
   */
  public void setProgramDto(ProgramDto programDto);

  /**
   * Gets partner dto.
   *
   * @return the partner dto
   */
  public PartnerDto getPartnerDto();

  /**
   * Sets partner dto.
   *
   * @param partnerDto the partner dto
   */
  public void setPartnerDto(PartnerDto partnerDto);

  /**
   * Gets departement dto.
   *
   * @return the departement dto
   */
  public DepartmentDto getDepartmentDto();

  /**
   * Sets departement dto.
   *
   * @param departmentDto the departement dto.
   */
  public void setDepartmentDto(DepartmentDto departmentDto);

  /**
   * Gets country dto.
   *
   * @return the country dto
   */
  public CountryDto getCountryDto();

  /**
   * Sets country dto.
   *
   * @param countryDto the country dto
   */
  public void setCountryDto(CountryDto countryDto);

  /**
   * Gets id student.
   *
   * @return the id student
   */
  public int getIdStudent();

  /**
   * Sets id student.
   *
   * @param idStudent the id student
   */
  public void setIdStudent(int idStudent);

  /**
   * Gets id program.
   *
   * @return the id program
   */
  public int getIdProgram();

  /**
   * Sets id program.
   *
   * @param idProgram the id program
   */
  public void setIdProgram(int idProgram);

  /**
   * Gets id partner.
   *
   * @return the id partner
   */
  public int getIdPartner();

  /**
   * Sets id partner.
   *
   * @param idPartner the id partner
   */
  public void setIdPartner(int idPartner);

  /**
   * Gets id department.
   *
   * @return the id department
   */
  public String getIdDepartment();

  /**
   * Sets id department.
   *
   * @param idDepartment the id department
   */
  public void setIdDepartment(String idDepartment);

  /**
   * Gets iso country.
   *
   * @return the iso country
   */
  public String getIsoCountry();

  /**
   * Sets iso country.
   *
   * @param isoCountry the iso country
   */
  public void setIsoCountry(String isoCountry);

  /**
   * Gets cancelation reason.
   *
   * @return the cancelation reason
   */
  public int getCancelationReason();

  /**
   * Sets cancelation reason.
   *
   * @param cancelationReason the cancelation reason
   */
  public void setCancelationReason(int cancelationReason);

  /**
   * Gets preference order.
   *
   * @return the preference order
   */
  public int getPreferenceOrder();

  /**
   * Sets preference order.
   *
   * @param preferenceOrder the preference order
   */
  public void setPreferenceOrder(int preferenceOrder);

  /**
   * Gets quadrimester.
   *
   * @return the quadrimester
   */
  public int getQuadrimester();

  /**
   * Sets quadrimester.
   *
   * @param quadrimester the quadrimester
   */
  public void setQuadrimester(int quadrimester);

  /**
   * Gets ver nr.
   *
   * @return the ver nr
   */
  public int getVerNr();

  /**
   * Sets ver nr.
   *
   * @param verNr the ver nr
   */
  public void setVerNr(int verNr);

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType();

  /**
   * Sets type.
   *
   * @param type the type
   */
  public void setType(String type);

  /**
   * Gets status.
   *
   * @return the status
   */
  public String getStatus();

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(String status);

  /**
   * Gets academic year.
   *
   * @return the academic year
   */
  public String getAcademicYear();

  /**
   * Sets academic year.
   *
   * @param academicYear the academic year
   */
  public void setAcademicYear(String academicYear);

  /**
   * Is canceled boolean.
   *
   * @return the boolean
   */
  public boolean isCanceled();

  /**
   * Sets canceled.
   *
   * @param canceled the canceled
   */
  public void setCanceled(boolean canceled);

  /**
   * Is departure grant contract boolean.
   *
   * @return the boolean
   */
  public boolean isDepartureGrantContract();

  /**
   * Sets departure grant contract.
   *
   * @param departureGrantContract the departure grant contract
   */
  public void setDepartureGrantContract(boolean departureGrantContract);

  /**
   * Is departure convention internship schoolarship boolean.
   *
   * @return the boolean
   */
  public boolean isDepartureConventionInternshipSchoolarship();

  /**
   * Sets departure convention internship schoolarship.
   *
   * @param departureConventionInternshipSchoolarship departure convention internship schoolarship.
   */
  public void setDepartureConventionInternshipSchoolarship(
      boolean departureConventionInternshipSchoolarship);

  /**
   * Is departure student convention boolean.
   *
   * @return the boolean
   */
  public boolean isDepartureStudentConvention();

  /**
   * Sets departure student convention.
   *
   * @param departureStudentConvention the departure student convention
   */
  public void setDepartureStudentConvention(boolean departureStudentConvention);

  /**
   * Is departure erasmus language test boolean.
   *
   * @return the boolean
   */
  public boolean isDepartureErasmusLanguageTest();

  /**
   * Sets departure erasmus language test.
   *
   * @param departureErasmusLanguageTest the departure erasmus language test
   */
  public void setDepartureErasmusLanguageTest(boolean departureErasmusLanguageTest);

  /**
   * Is departure doc aggreement boolean.
   *
   * @return the boolean
   */
  public boolean isDepartureDocAggreement();

  /**
   * Sets departure doc aggreement.
   *
   * @param departureDocAggreement the departure doc aggreement
   */
  public void setDepartureDocAggreement(boolean departureDocAggreement);

  /**
   * Is depart doc sent highschool boolean.
   *
   * @return the boolean
   */
  public boolean isDepartDocSentHighschool();

  /**
   * Sets depart doc sent highschool.
   *
   * @param departDocSentHighschool the depart doc sent highschool
   */
  public void setDepartDocSentHighschool(boolean departDocSentHighschool);

  /**
   * Is software proeco boolean.
   *
   * @return the boolean
   */
  public boolean isSoftwareProeco();

  /**
   * Sets software proeco.
   *
   * @param softwareProeco the software proeco
   */
  public void setSoftwareProeco(boolean softwareProeco);

  /**
   * Is software mobility tools boolean.
   *
   * @return the boolean
   */
  public boolean isSoftwareMobilityTools();

  /**
   * Sets software mobility tools.
   *
   * @param softwareMobilityTools the software mobility tools
   */
  public void setSoftwareMobilityTools(boolean softwareMobilityTools);

  /**
   * Is software mobi boolean.
   *
   * @return the boolean
   */
  public boolean isSoftwareMobi();

  /**
   * Sets software mobi.
   *
   * @param softwareMobi the software mobi
   */
  public void setSoftwareMobi(boolean softwareMobi);

  /**
   * Is return residence cert boolean.
   *
   * @return the boolean
   */
  public boolean isReturnResidenceCert();

  /**
   * Sets return residence cert.
   *
   * @param returnResidenceCert the return residence cert
   */
  public void setReturnResidenceCert(boolean returnResidenceCert);

  /**
   * Is return transcript boolean.
   *
   * @return the boolean
   */
  public boolean isReturnTranscript();

  /**
   * Sets return transcript.
   *
   * @param returnTranscript the return transcript
   */
  public void setReturnTranscript(boolean returnTranscript);

  /**
   * Is return internship cert boolean.
   *
   * @return the boolean
   */
  public boolean isReturnInternshipCert();

  /**
   * Sets return internship cert.
   *
   * @param returnInternshipCert the return internship cert
   */
  public void setReturnInternshipCert(boolean returnInternshipCert);

  /**
   * Is return final report boolean.
   *
   * @return the boolean
   */
  public boolean isReturnFinalReport();

  /**
   * Sets return final report.
   *
   * @param returnFinalReport the return final report
   */
  public void setReturnFinalReport(boolean returnFinalReport);

  /**
   * Is return erasmus language test boolean.
   *
   * @return the boolean
   */
  public boolean isReturnErasmusLanguageTest();

  /**
   * Sets return erasmus language test.
   *
   * @param returnErasmusLanguageTest the return erasmus language test
   */
  public void setReturnErasmusLanguageTest(boolean returnErasmusLanguageTest);

  /**
   * Is return doc sent highschool boolean.
   *
   * @return the boolean
   */
  public boolean isReturnDocSentHighschool();

  /**
   * Sets return doc sent highschool.
   *
   * @param returnDocSentHighschool the return doc sent highschool
   */
  public void setReturnDocSentHighschool(boolean returnDocSentHighschool);

  /**
   * Gets cancelation dto.
   *
   * @return the cancelation dto
   */
  public CancelationDto getCancelationDto();

  /**
   * Sets cancelation dto.
   *
   * @param cancelationDto the cancelation dto
   */
  public void setCancelationDto(CancelationDto cancelationDto);

  /**
   * Gets the first payment date.
   *
   * @return the first payment date.
   */
  public boolean getPaymentDate1();

  /**
   * Sets the first payment date.
   *
   * @param paymentDate1 the first payment date.
   */
  public void setPaymentDate1(boolean paymentDate1);

  /**
   * Gets the amount of the payment.
   *
   * @return the amount of the payment.
   */
  public double getAmount();

  /**
   * Sets the amount of the payment.
   *
   * @param amount the amount of the payment.
   */
  public void setAmount(double amount);

  /**
   * Gets the second payment date.
   *
   * @return the second payment date.
   */
  public boolean getPaymentDate2();

  /**
   * Sets the second payment date.
   *
   * @param paymentDate2 the second payment date.
   */
  public void setPaymentDate2(boolean paymentDate2);

}
