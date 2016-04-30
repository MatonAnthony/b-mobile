package bizz.implementations;

import bizz.interfaces.UserBizz;
import dto.CountryDto;
import dto.DepartmentDto;
import nl.garvelink.iban.IBAN;

import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class UserImpl implements UserBizz, Cloneable {

  private int id;
  private String idDepartment;
  private DepartmentDto departmentDto;
  private String pseudo;
  private String password;
  private String name;
  private String firstname;
  private String email;
  private LocalDate registrationDate;
  private String permissions;
  private LocalDate birthDate;
  private String citizenship;
  private String street;
  private String houseNumber;
  private String mailBox;
  private String zip;
  private String city;
  private String country;
  private CountryDto countryDto;
  private String tel;
  private String gender;
  private int successfullYearInCollege;
  private IBAN iban;
  private String bic;
  private String accountHolder;
  private String bankName;
  private int verNr;

  public String getIdDepartment() {
    return idDepartment;
  }

  public void setIdDepartment(String idDepartment) {
    this.idDepartment = idDepartment;
  }

  public DepartmentDto getDepartmentDto() {
    return departmentDto;
  }

  public void setDepartmentDto(DepartmentDto departmentDto) {
    this.departmentDto = departmentDto;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getMailBox() {
    return mailBox;
  }

  public void setMailBox(String mailBox) {
    this.mailBox = mailBox;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public CountryDto getCountryDto() {
    return countryDto;
  }

  public void setCountryDto(CountryDto countryDto) {
    this.countryDto = countryDto;
  }

  public int getVerNr() {
    return verNr;
  }

  public void setVerNr(int verNr) {
    this.verNr = verNr;
  }

  /**
   * Return user pseudo
   * 
   * @return Pseudo of the user.
   */
  @Override
  public String getPseudo() {
    return pseudo;
  }

  /**
   * Edit user pseudo.
   * 
   * @param pseudo New pseudo for that user.
   */
  @Override
  public void setPseudo(String pseudo) {
    // TODO Remettre le Pattern à 6
    if (pseudo == null || !Pattern.matches("[A-z0-9]*{5,}", pseudo)) {
      throw new IllegalArgumentException("Le pseudo doit faire au moins 6 caractères et ne peut"
        + "contenir de caractères spéciaux !");
    }
    this.pseudo = pseudo;
  }

  /**
   * Return password for that user.
   * 
   * @return Password of the user.
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Change the user password.
   * 
   * @param password New password for that user.
   */
  @Override
  public void setPassword(String password) {
    if (password == null || !Pattern.matches(".{6,}", password)) {
      throw new IllegalArgumentException("Le mot de passe doit contenir au moins 6 caractères");
    }
    this.password = password;
  }

  /**
   * Returns name for that user.
   * 
   * @return Name of the user.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Edit user name.
   * 
   * @param name New user name.
   */
  @Override
  public void setName(String name) {
    if (name == null) {
      this.name = name;
    } else if (!Pattern.matches("[A-zÀ-ÿ- ]*", name)) {
      throw new IllegalArgumentException("Nom invalide");
    }
    this.name = name;
  }

  /**
   * Return firstname of the user.
   * 
   * @return firstname of the user.
   */
  @Override
  public String getFirstname() {
    return firstname;
  }

  /**
   * Edit user firstname.
   * 
   * @param firstname New user firstname.
   */
  @Override
  public void setFirstname(String firstname) {
    if (firstname == null) {
      this.firstname = firstname;
    } else if (!Pattern.matches("[A-zÀ-ÿ- ]*", firstname)) {
      throw new IllegalArgumentException("Prénom invalide");
    }
    this.firstname = firstname;
  }

  /**
   * Return user email.
   * 
   * @return Email of our user.
   */
  @Override
  public String getEmail() {
    return email;
  }

  /**
   * Edit user email
   * 
   * @param email New user email.
   */
  @Override
  public void setEmail(String email) {
    if (email == null) {
      this.email = email;
    } else if (!Pattern.matches("^[\\w!#$%&’*.+/=?`{|}~^-]+(?:\\.[\\w!#$%&.’*+/=?`{|}~^-]+)"
        + "*@(?:[a-zA-Z0-9-.]+\\.)+[a-zA-Z]{2,6}$", email)) {
      throw new IllegalArgumentException("Email invalide");
    }
    this.email = email;
  }

  /**
   * Return user phone number of an user.
   * 
   * @return user phone number.
   */
  @Override
  public String getTel() {
    return tel;
  }

  /**
   * Set the phone number for our user
   *
   * @param tel New phone number.
   */
  @Override
  public void setTel(String tel) {
    // TODO le tel peut etre null normalment
    if (tel == null) {
      this.tel = tel;
    } else if (!Pattern.matches("[0-9]*", tel)) {
      throw new IllegalArgumentException("Numéro de téléphone invalide");
    }
    this.tel = tel;
  }

  /**
   * Return the gender of User.
   *
   * @return gender of a user.
   */
  @Override
  public String getGender() {
    return gender;
  }

  /**
   * Set the user gender
   *
   * @param gender user's gender.
   */
  @Override
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * Return user permissions
   *
   * @return permissions for a user.
   */
  @Override
  public String getPermissions() {
    return permissions;
  }

  /**
   * Set user permissions.
   *
   * @param permissions Set of permissions to apply.
   */
  @Override
  public void setPermissions(String permissions) {
    if (permissions == null) {
      throw new IllegalArgumentException();
    }
    this.permissions = permissions;
  }

  /**
   * Return the IBAN of a user.
   *
   * @return IBAN number for a user.
   */
  @Override
  public String getIban() {

    if (iban == null) {
      return null;
    }
    return iban.toPlainString();
  }

  /**
   * Set the IBAN
   *
   * @param iban IBAN to set.
   */
  @Override
  public void setIban(String iban) {
    if (iban == null || iban.isEmpty()) {
      this.iban = null;
    } else {
      try {
        this.iban = IBAN.parse(iban);
      } catch(Exception exc) {
        throw new IllegalArgumentException("IBAN Invalide");
      }
    }
  }

  /**
   * Return the BIC.
   *
   * @return BIC number for this user.
   */
  @Override
  public String getBic() {
    return bic;
  }

  /**
   * Set the BIC.
   *
   * @param bic BIC to set.
   */
  @Override
  public void setBic(String bic) {
    this.bic = bic;
  }

  /**
   * Return the account holder.
   *
   * @return Name of the account holder
   */
  @Override
  public String getAccountHolder() {
    return accountHolder;
  }

  /**
   * Set the account holder name.
   *
   * @param accountHolder New account holder name.
   */
  @Override
  public void setAccountHolder(String accountHolder) {
    if (accountHolder == null) {
      this.accountHolder = accountHolder;
    } else if (!Pattern.matches("[A-zÀ-ÿ- ]*", accountHolder)) {
      throw new IllegalArgumentException("Nom du titulaire invalide");
    }
    this.accountHolder = accountHolder;
  }

  /**
   * Return the bank name.
   *
   * @return Bank name.
   */
  @Override
  public String getBankName() {
    return bankName;
  }

  /**
   * Set the bank name.
   *
   * @param bankName New bank name.
   */
  @Override
  public void setBankName(String bankName) {
    if (bankName == null) {
      this.bankName = bankName;
    } else if (!Pattern.matches("[A-zÀ-ÿ- ]*", bankName)) {
      throw new IllegalArgumentException("Nom de la banque invalide");
    }
    this.bankName = bankName;
  }

  /**
   * Return the number of successfull year in college.
   *
   * @return number of successfull college year.
   */
  @Override
  public int getSuccessfullYearInCollege() {
    return successfullYearInCollege;
  }

  /**
   * Set the number of successfull college year
   *
   * @param successfullYearInCollege New number of successfull college year.
   */
  @Override
  public void setSuccessfullYearInCollege(int successfullYearInCollege) {
    if (successfullYearInCollege < 0) {
      throw new IllegalArgumentException();
    }
    this.successfullYearInCollege = successfullYearInCollege;
  }

  /**
   * Return registration date.
   *
   * @return Registration date.
   */
  @Override
  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  /**
   * Set the registration date.
   *
   * @param registrationDate New registration date.
   */
  @Override
  public void setRegistrationDate(LocalDate registrationDate) {

    if (registrationDate.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException();
    }

    this.registrationDate = registrationDate;
  }

  /**
   * Return the birthdate.
   *
   * @return The birthdate.
   */
  @Override
  public LocalDate getBirthDate() {
    return birthDate;
  }

  /**
   * Set the birthdate.
   *
   * @param birthDate New birthdate.
   */
  @Override
  public void setBirthDate(LocalDate birthDate) {

    if (birthDate.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("La date de naissance ne peut-être dans le futur.");
    }

    this.birthDate = birthDate;
  }

  /**
   * Return the citizenship.
   *
   * @return The citizenship.
   */
  public String getCitizenship() {
    return citizenship;
  }

  /**
   * Set the citizenship.
   *
   * @param citizenship new citizenship.
   */
  public void setCitizenship(String citizenship) {
    if (citizenship == null) {
      this.citizenship = citizenship;
    } else if (!Pattern.matches("[A-zÀ-ÿ- ]*", citizenship)) {
      throw new IllegalArgumentException("Nationalité invalide");
    }
    this.citizenship = citizenship;
  }

  /**
   * Encrypt the password attribute (Assume the password set is in clear).
   */
  @Override
  public void cryptPassword() {

    if (this.password == null) {
      throw new NullPointerException();
    }
    this.password = BCrypt.hashpw(password, BCrypt.gensalt());
  }

  /**
   * Compare the parameter with the stored password.
   * 
   * @param passwordToCheck Password to check (in clear).
   * @return true if password match.
   */
  @Override
  public boolean checkPassword(String passwordToCheck) {
    return BCrypt.checkpw(passwordToCheck, this.password);
  }



  @Override
  public Object clone() throws CloneNotSupportedException {
    UserImpl clone = (UserImpl) super.clone();
    clone.setRegistrationDate(LocalDate.from(this.registrationDate));
    clone.setBirthDate(LocalDate.from(this.birthDate));
    return clone;
  }

  /**
   * Set the id for the curent user.
   *
   * @param id the id_user to set
   */
  @Override
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Get the id of the current user.
   * 
   * @return the id of the user.
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * Generate the hashcode for the current user.
   * 
   * @return the hashcode of the user.
   */
  // TODO (Jonathan) Corriger hashCode avec les nouveaux champs + test
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountHolder == null) ? 0 : accountHolder.hashCode());
    result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
    result = prime * result + ((bic == null) ? 0 : bic.hashCode());
    result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((iban == null) ? 0 : iban.hashCode());
    result = prime * result + id;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((permissions == null) ? 0 : permissions.hashCode());
    result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
    result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
    result = prime * result + successfullYearInCollege;
    result = prime * result + ((tel == null) ? 0 : tel.hashCode());
    return result;
  }

  /**
   * Check of the user is equal to the object in parameter.
   * 
   * @param obj the object to check
   * @return true if the object is equal to the current user. False if it's not equal.
   */
  // TODO (Jonathan) Corriger le equals avec les nouveaux champs + test
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
    UserImpl other = (UserImpl) obj;
    if (accountHolder == null) {
      if (other.accountHolder != null) {
        return false;
      }
    } else if (!accountHolder.equals(other.accountHolder)) {
      return false;
    }
    if (bankName == null) {
      if (other.bankName != null) {
        return false;
      }
    } else if (!bankName.equals(other.bankName)) {
      return false;
    }
    if (bic == null) {
      if (other.bic != null) {
        return false;
      }
    } else if (!bic.equals(other.bic)) {
      return false;
    }
    if (birthDate == null) {
      if (other.birthDate != null) {
        return false;
      }
    } else if (!birthDate.equals(other.birthDate)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (firstname == null) {
      if (other.firstname != null) {
        return false;
      }
    } else if (!firstname.equals(other.firstname)) {
      return false;
    }
    if (gender == null) {
      if (other.gender != null) {
        return false;
      }
    } else if (!gender.equals(other.gender)) {
      return false;
    }
    if (iban == null) {
      if (other.iban != null) {
        return false;
      }
    } else if (!iban.equals(other.iban)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (password == null) {
      if (other.password != null) {
        return false;
      }
    } else if (!password.equals(other.password)) {
      return false;
    }
    if (permissions == null) {
      if (other.permissions != null) {
        return false;
      }
    } else if (!permissions.equals(other.permissions)) {
      return false;
    }
    if (pseudo == null) {
      if (other.pseudo != null) {
        return false;
      }
    } else if (!pseudo.equals(other.pseudo)) {
      return false;
    }
    if (registrationDate == null) {
      if (other.registrationDate != null) {
        return false;
      }
    } else if (!registrationDate.equals(other.registrationDate)) {
      return false;
    }
    if (successfullYearInCollege != other.successfullYearInCollege) {
      return false;
    }
    if (tel == null) {
      if (other.tel != null) {
        return false;
      }
    } else if (!tel.equals(other.tel)) {
      return false;
    }
    return true;
  }
}
