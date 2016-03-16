package dto;

public interface MobilityDto {

  public int getId();

  public void setId(int id);

  public int getIdStudent();

  public void setIdStudent(int idStudent);

  public int getIdProgram();

  public void setIdProgram(int idProgram);

  public int getIdPartner();

  public void setIdPartner(int idPartner);

  public int getPreferenceOrder();

  public void setPreferenceOrder(int preferenceOrder);

  public int getIdDepartment();

  public void setIdDepartment(int idDepartment);

  public int getQuadrimester();

  public void setQuadrimester(int quadrimester);

  public int getVerNr();

  public void setVerNr(int verNr);

  public int getCancelationReason();

  public void setCancelationReason(int cancelationReason);

  public String getType();

  public void setType(String type);

  public String getCountry();

  public void setCountry(String country);

  public String getStatus();

  public void setStatus(String status);

  public String getAcademicYear();

  public void setAcademicYear(String academicYear);

  public boolean isCanceled();

  public void setCanceled(boolean canceled);

  public boolean isDepartureGrantContract();

  public void setDepartureGrantContract(boolean departureGrantContract);

  public boolean isDepartureConventionInternshipSchoolarship();

  public void setDepartureConventionInternshipSchoolarship(
      boolean departureConventionInternshipSchoolarship);

  public boolean isDepartureStudentConvention();

  public void setDepartureStudentConvention(boolean departureStudentConvention);

  public boolean isDepartureErasmusLanguageTest();

  public void setDepartureErasmusLanguageTest(boolean departureErasmusLanguageTest);

  public boolean isDepartureDocAggreement();

  public void setDepartureDocAggreement(boolean departureDocAggreement);

  public boolean isDepartDocSentHighschool();

  public void setDepartDocSentHighschool(boolean departDocSentHighschool);

  public boolean isSoftwareProeco();

  public void setSoftwareProeco(boolean softwareProeco);

  public boolean isSoftwareMobilityTools();

  public void setSoftwareMobilityTools(boolean softwareMobilityTools);

  public boolean isSoftwareMobi();

  public void setSoftwareMobi(boolean softwareMobi);

  public boolean isReturnResidenceCert();

  public void setReturnResidenceCert(boolean returnResidenceCert);

  public boolean isReturnTranscript();

  public void setReturnTranscript(boolean returnTranscript);

  public boolean isReturnInternshipCert();

  public void setReturnInternshipCert(boolean returnInternshipCert);

  public boolean isReturnFinalReport();

  public void setReturnFinalReport(boolean returnFinalReport);

  public boolean isReturnErasmusLanguageTest();

  public void setReturnErasmusLanguageTest(boolean returnErasmusLanguageTest);

  public boolean isReturnDocSentHighschool();

  public void setReturnDocSentHighschool(boolean returnDocSentHighschool);

}