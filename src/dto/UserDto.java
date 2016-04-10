package dto;

import java.time.LocalDate;

/**
 * The interface User dto.
 */
public interface UserDto {

  /**
   * Sets id.
   *
   * @param id the id
   */
  void setId(int id);

  /**
   * Gets id.
   *
   * @return the id
   */
  int getId();

  /**
   * Sets pseudo.
   *
   * @param pseudo the pseudo
   */
  void setPseudo(String pseudo);

  /**
   * Gets pseudo.
   *
   * @return the pseudo
   */
  String getPseudo();

  /**
   * Gets password.
   *
   * @return the password
   */
  String getPassword();

  /**
   * Sets password.
   *
   * @param password the password
   */
  void setPassword(String password);

  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();

  /**
   * Sets name.
   *
   * @param name the name
   */
  void setName(String name);

  /**
   * Gets firstname.
   *
   * @return the firstname
   */
  String getFirstname();

  /**
   * Sets firstname.
   *
   * @param firstname the firstname
   */
  void setFirstname(String firstname);

  /**
   * Gets email.
   *
   * @return the email
   */
  String getEmail();

  /**
   * Sets email.
   *
   * @param email the email
   */
  void setEmail(String email);

  /**
   * Gets tel.
   *
   * @return the tel
   */
  String getTel();

  /**
   * Sets tel.
   *
   * @param tel the tel
   */
  void setTel(String tel);

  /**
   * Gets gender.
   *
   * @return the gender
   */
  String getGender();

  /**
   * Sets gender.
   *
   * @param gender the gender
   */
  void setGender(String gender);

  /**
   * Gets permissions.
   *
   * @return the permissions
   */
  String getPermissions();

  /**
   * Sets permissions.
   *
   * @param permissions the permissions
   */
  void setPermissions(String permissions);

  /**
   * Gets iban.
   *
   * @return the iban
   */
  String getIban();

  /**
   * Sets iban.
   *
   * @param iban the iban
   */
  void setIban(String iban);

  /**
   * Gets bic.
   *
   * @return the bic
   */
  String getBic();

  /**
   * Sets bic.
   *
   * @param bic the bic
   */
  void setBic(String bic);

  /**
   * Gets account holder.
   *
   * @return the account holder
   */
  String getAccountHolder();

  /**
   * Sets account holder.
   *
   * @param accountHolder the account holder
   */
  void setAccountHolder(String accountHolder);

  /**
   * Sets bank name.
   *
   * @param bankName the bank name
   */
  void setBankName(String bankName);

  /**
   * Gets bank name.
   *
   * @return the bank name
   */
  String getBankName();

  /**
   * Gets successfull year in college.
   *
   * @return the successfull year in college
   */
  int getSuccessfullYearInCollege();

  /**
   * Sets successfull year in college.
   *
   * @param successfullYearInCollege the successfull year in college
   */
  void setSuccessfullYearInCollege(int successfullYearInCollege);

  /**
   * Gets registration date.
   *
   * @return the registration date
   */
  LocalDate getRegistrationDate();

  /**
   * Sets registration date.
   *
   * @param registrationDate the registration date
   */
  void setRegistrationDate(LocalDate registrationDate);

  /**
   * Gets birth date.
   *
   * @return the birth date
   */
  LocalDate getBirthDate();

  /**
   * Sets birth date.
   *
   * @param birthDate the birth date
   */
  void setBirthDate(LocalDate birthDate);

  /**
   * Gets id department.
   *
   * @return the id department
   */
  public int getIdDepartment();

  /**
   * Sets id department.
   *
   * @param idDepartment the id department
   */
  public void setIdDepartment(int idDepartment);

  /**
   * Gets department dto.
   *
   * @return the department dto
   */
  public DepartmentDto getDepartmentDto();

  /**
   * Sets department dto.
   *
   * @param departmentDto the department dto
   */
  public void setDepartmentDto(DepartmentDto departmentDto);

  /**
   * Gets street.
   *
   * @return the street
   */
  public String getStreet();

  /**
   * Sets street.
   *
   * @param street the street
   */
  public void setStreet(String street);

  /**
   * Gets house number.
   *
   * @return the house number
   */
  public String getHouseNumber();

  /**
   * Sets house number.
   *
   * @param houseNumber the house number
   */
  public void setHouseNumber(String houseNumber);

  /**
   * Gets mail box.
   *
   * @return the mail box
   */
  public String getMailBox();

  /**
   * Sets mail box.
   *
   * @param mailBox the mail box
   */
  public void setMailBox(String mailBox);

  /**
   * Gets zip.
   *
   * @return the zip
   */
  public String getZip();

  /**
   * Sets zip.
   *
   * @param zip the zip
   */
  public void setZip(String zip);

  /**
   * Gets city.
   *
   * @return the city
   */
  public String getCity();

  /**
   * Sets city.
   *
   * @param city the city
   */
  public void setCity(String city);

  /**
   * Gets country.
   *
   * @return the country
   */
  public String getCountry();

  /**
   * Sets country.
   *
   * @param country the country
   */
  public void setCountry(String country);

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

}
