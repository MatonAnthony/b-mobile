package dto;

import java.time.LocalDate;

public interface UserDto {

  void setId(int id);

  int getId();

  void setPseudo(String pseudo);

  String getPseudo();

  String getPassword();

  void setPassword(String password);

  String getName();

  void setName(String name);

  String getFirstname();

  void setFirstname(String firstname);

  String getEmail();

  void setEmail(String email);

  String getTel();

  void setTel(String tel);

  String getGender();

  void setGender(String gender);

  String getPermissions();

  void setPermissions(String permissions);

  String getIban();

  void setIban(String iban);

  String getBic();

  void setBic(String bic);

  String getAccountHolder();

  void setAccountHolder(String accountHolder);

  void setBankName(String bankName);

  String getBankName();

  int getSuccessfullYearInCollege();

  void setSuccessfullYearInCollege(int successfullYearInCollege);

  LocalDate getRegistrationDate();

  void setRegistrationDate(LocalDate registrationDate);

  LocalDate getBirthDate();

  void setBirthDate(LocalDate birthDate);

  public int getIdDepartment();

  public void setIdDepartment(int idDepartment);

  public String getStreet();

  public void setStreet(String street);

  public String getHouseNumber();

  public void setHouseNumber(String houseNumber);

  public String getMailBox();

  public void setMailBox(String mailBox);

  public String getZip();

  public void setZip(String zip);

  public String getCity();

  public void setCity(String city);

  public String getCountry();

  public void setCountry(String country);

  public int getVerNr();

  public void setVerNr(int verNr);

}
