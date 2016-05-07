package dto;

import exceptions.MalformedIbanException;

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
   * @throws IllegalArgumentException The pseudo cannot be null.
   */
  String getPassword();

  /**
   * Sets password.
   *
   * @param password the password
   * @throws IllegalArgumentException The password cannot be null.
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
   * @param name the name.
   * @throws IllegalArgumentException Name must match `[A-zÀ-ÿ- ]*`.
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
   * @param firstname the firstname.
   * @throws IllegalArgumentException Firstname must match `[A-zÀ-ÿ- ]*`.
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
   * @param email the email.
   * @throws IllegalArgumentException Email must match
   *         `^[\w!#$%&’*+/=?`{|}~^-]+(?:\.[\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,
   *         6}$`.
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
   * @throws IllegalArgumentException Tel must match `[0-9]*`.
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
   * @throws IllegalArgumentException Permissions cannot be null.
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
   * @throws MalformedIbanException If the Iban is malformed.
   */
  void setIban(String iban) throws MalformedIbanException;

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
   * @throws IllegalArgumentException accountHolder must match `[A-zÀ-ÿ- ]*`.
   */
  void setAccountHolder(String accountHolder);

  /**
   * Sets bank name.
   *
   * @param bankName the bank name
   * @throws IllegalArgumentException bankName must match `[A-zÀ-ÿ- ]*`.
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
   * @throws IllegalArgumentException successfullYearInCollege must be greater than 0.
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
   * @throws IllegalArgumentException registrationDate must not be in the future.
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
   * @throws IllegalArgumentException birthDate must be in the past.
   */
  void setBirthDate(LocalDate birthDate);

  /**
   * Gets id department.
   *
   * @return the id department
   */
  String getIdDepartment();

  /**
   * Sets id department.
   *
   * @param idDepartment the id department
   */
  void setIdDepartment(String idDepartment);

  /**
   * Gets department dto.
   *
   * @return the department dto
   */
  DepartmentDto getDepartmentDto();

  /**
   * Sets department dto.
   *
   * @param departmentDto the department dto
   */
  void setDepartmentDto(DepartmentDto departmentDto);

  /**
   * Gets street.
   *
   * @return the street
   */
  String getStreet();

  /**
   * Sets street.
   *
   * @param street the street
   */
  void setStreet(String street);

  /**
   * Gets house number.
   *
   * @return the house number
   */
  String getHouseNumber();

  /**
   * Sets house number.
   *
   * @param houseNumber the house number
   */
  void setHouseNumber(String houseNumber);

  /**
   * Gets mail box.
   *
   * @return the mail box
   */
  String getMailBox();

  /**
   * Sets mail box.
   *
   * @param mailBox the mail box
   */
  void setMailBox(String mailBox);

  /**
   * Gets zip.
   *
   * @return the zip
   */
  String getZip();

  /**
   * Sets zip.
   *
   * @param zip the zip
   */
  void setZip(String zip);

  /**
   * Gets city.
   *
   * @return the city
   */
  String getCity();

  /**
   * Sets city.
   *
   * @param city the city
   */
  void setCity(String city);

  /**
   * Gets country.
   *
   * @return the country
   */
  String getCountry();

  /**
   * Sets country.
   *
   * @param country the country
   */
  void setCountry(String country);

  /**
   * Gets country dto.
   *
   * @return the country dto
   */
  CountryDto getCountryDto();

  /**
   * Sets country dto.
   *
   * @param countryDto the country dto
   */
  void setCountryDto(CountryDto countryDto);

  /**
   * Gets ver nr.
   *
   * @return the ver nr
   */
  int getVerNr();

  /**
   * Sets ver nr.
   *
   * @param verNr the ver nr
   */
  void setVerNr(int verNr);

  /**
   * Gets vcitizenship.
   *
   * @return the citizenship.
   */
  String getCitizenship();

  /**
   * Sets citizenship.
   *
   * @param citizenship the citizenship.
   * @throws IllegalArgumentException citizenship must match `[A-zÀ-ÿ- ]*`.
   */
  void setCitizenship(String citizenship);

}
