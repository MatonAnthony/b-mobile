package dto;

public interface PartnerDto {

  public int getId();

  public void setId(int id);

  public int getIdUser();

  public void setIdUser(int idUser);

  public String getLegalName();

  public void setLegalName(String legalName);

  public String getBusiness();

  public void setBusiness(String business);

  public String getFullName();

  public void setFullName(String fullName);

  public String getDepartment();

  public void setDepartment(String department);

  public String getType();

  public void setType(String type);

  public int getNbEmployees();

  public void setNbEmployees(int nbEmployees);

  public String getStreet();

  public void setStreet(String street);

  public String getNumber();

  public void setNumber(String number);

  public String getMailbox();

  public void setMailbox(String mailbox);

  public String getZip();

  public void setZip(String zip);

  public String getCity();

  public void setCity(String city);

  public String getState();

  public void setState(String state);

  public CountryDto getCountryDto();

  public void setCountryDto(CountryDto countryDto);

  public String getCountry();

  public void setCountry(String country);

  public String getEmail();

  public void setEmail(String email);

  public String getWebsite();

  public void setWebsite(String website);

  public boolean isExists();

  public void setExists(boolean exists);

  public int getVerNr();

  public void setVerNr(int verNr);

}
