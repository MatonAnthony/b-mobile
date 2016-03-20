package bizz;

import nl.garvelink.iban.IBAN;

import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;

class UserImpl implements UserBizz, Cloneable {

  private int id;
  private int idDepartment;
  private String pseudo;
  private String password;
  private String name;
  private String firstname;
  private String email;
  private String street;
  private String houseNumber;
  private String mailBox;
  private String zip;
  private String city;
  private String country;
  private String tel;
  private String gender;
  private String permissions;
  private IBAN iban;
  private String bic;
  private String accountHolder;
  private String bankName;
  private int successfullYearInCollege;
  private LocalDate registrationDate;
  private LocalDate birthDate;
  private int verNr;

  public int getIdDepartment() {
    return idDepartment;
  }

  public void setIdDepartment(int idDepartment) {
    this.idDepartment = idDepartment;
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
    if (pseudo == null) {
      throw new NullPointerException();
    }
    return pseudo;
  }

  /**
   * Edit user pseudo.
   * 
   * @param pseudo New pseudo for that user.
   */
  @Override
  public void setPseudo(String pseudo) {
    if (pseudo == null) {
      throw new IllegalArgumentException();
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
    if (password == null) {
      throw new NullPointerException();
    }
    return password;
  }

  /**
   * Change the user password.
   * 
   * @param password New password for that user.
   */
  @Override
  public void setPassword(String password) {
    if (password == null) {
      throw new IllegalArgumentException();
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
    if (name == null) {
      throw new NullPointerException();
    }
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
      throw new IllegalArgumentException();
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
    if (firstname == null) {
      throw new NullPointerException();
    }
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
      throw new IllegalArgumentException();
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
    if (email == null) {
      throw new NullPointerException();
    }
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
      throw new IllegalArgumentException();
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
    if (tel == null) {
      throw new NullPointerException();
    }
    return tel;
  }

  /**
   * Modifie le numero de telephone de l'utilisateur.
   * 
   * @param tel Le nouveau numero de telephone de l'utilisateur.
   */
  @Override
  public void setTel(String tel) {
    // TODO le tel peut etre null normalment
    /*
     * if (tel == null) { throw new IllegalArgumentException(); }
     */
    this.tel = tel;
  }

  /**
   * Renvoie le gender de l'utilisateur.
   * 
   * @return Le gender de l'utilisateur.
   */
  @Override
  public String getGender() {
    if (gender == null) {
      throw new NullPointerException();
    }
    return gender;
  }

  /**
   * Modifie le gender de l'utilisateur.
   * 
   * @param gender Le nouveau gender de l'utilisateur.
   */
  @Override
  public void setGender(String gender) {
    /*
     * if (gender == null) { throw new IllegalArgumentException(); }
     */
    this.gender = gender;
  }

  /**
   * Renvoie les doits de l'utilisateur.
   * 
   * @return Les permissions de l'utilisateur.
   */
  @Override
  public String getPermissions() {
    if (permissions == null) {
      throw new NullPointerException();
    }
    return permissions;
  }

  /**
   * Modifie les permissions de l'utilisateur.
   * 
   * @param permissions Les nouveaux permissions de l'utilisateur.
   */
  @Override
  public void setPermissions(String permissions) {
    if (permissions == null) {
      throw new IllegalArgumentException();
    }
    this.permissions = permissions;
  }

  /**
   * Renvoie le numero de banque iban de l'utilisateur.
   * 
   * @return Le numero de banque iban de l'utilisateur.
   */
  @Override
  public String getIban() {
    if (iban == null) {
      throw new NullPointerException();
    }
    return iban.toPlainString();
  }

  /**
   * Modifie Modifie le numero de banque Iban de l'utilisateur.
   * 
   * @param iban Le nouveau numero de banque Iban de l'utilisateur.
   */
  @Override
  public void setIban(String iban) {
    if (iban == null) {
      this.iban = null;
    } else {
      IBAN staging = IBAN.parse(iban);
      this.iban = staging;
    }
  }

  /**
   * Renvoie le numero de banque Bic de l'utilisateur.
   * 
   * @return Le numero de banque Bic de l'utilisateur.
   */
  @Override
  public String getBic() {
    /*
     * if (bic == null) { throw new NullPointerException(); }
     */
    return bic;
  }

  /**
   * Modifie le numero de banque Bic de l'utilisateur.
   * 
   * @param bic Le nouveau numero de banque Bic.
   */
  @Override
  public void setBic(String bic) {
    /*
     * if (bic == null) { throw new IllegalArgumentException(); }
     */
    this.bic = bic;
  }

  /**
   * Renvoie le nom du titulaire du compte en banque de l'utilisateur.
   * 
   * @return Le nom du titulaire du compte en banque de l'utilisateur.
   */
  @Override
  public String getAccountHolder() {
    /*
     * if (accountHolder == null) { throw new NullPointerException(); }
     */
    return accountHolder;
  }

  /**
   * Modifie le nom du titulaire du compte en banque.
   * 
   * @param accountHolder Le nouveau nom du titulaire.
   */
  @Override
  public void setAccountHolder(String accountHolder) {
    /*
     * if (accountHolder == null) { throw new IllegalArgumentException(); }
     */
    this.accountHolder = accountHolder;
  }

  /**
   * Renvoie le nom de la banque de l'utilisateur.
   * 
   * @return Le nom de la banque de l'utilisateur.
   */
  @Override
  public String getBankName() {
    /*
     * if (bankName == null) { throw new NullPointerException(); }
     */
    return bankName;
  }

  /**
   * Modifie le nom de la banque de l'utilisateur.
   * 
   * @param bankName Le nouveau nom de la banque.
   */
  @Override
  public void setBankName(String bankName) {
    /*
     * if (bankName == null) { throw new IllegalArgumentException(); }
     */
    this.bankName = bankName;
  }

  /**
   * Renvoie le nombre d'annee reussies par l'utilisateur.
   * 
   * @return Le nombre d'annee reussies par l'utilisateur.
   */
  @Override
  public int getSuccessfullYearInCollege() {
    return successfullYearInCollege;
  }

  /**
   * Modifie le nombre d'annees reussies.
   * 
   * @param successfullYearInCollege Le nouveau nombre d'annees reussies.
   */
  @Override
  public void setSuccessfullYearInCollege(int successfullYearInCollege) {
    if (successfullYearInCollege < 0) {
      throw new IllegalArgumentException();
    }
    this.successfullYearInCollege = successfullYearInCollege;
  }

  /**
   * Renvoie la date d'inscription de l'utilisateur.
   * 
   * @return La date d'inscription de l'utilisateur.
   */
  @Override
  public LocalDate getRegistrationDate() {
    /*
     * if (registrationDate == null) { throw new NullPointerException(); }
     */
    return registrationDate;
  }

  /**
   * Modifie la date d'inscription.
   * 
   * @param registrationDate La nouvelle date d'inscription.
   */
  @Override
  public void setRegistrationDate(LocalDate registrationDate) {
    /*
     * if (registrationDate == null || registrationDate.isAfter(LocalDate.now())) { throw new
     * IllegalArgumentException(); }
     */
    this.registrationDate = registrationDate;
  }

  /**
   * Renvoie la date de naissance de l'utilisateur.
   * 
   * @return La date de naissance de l'utilisateur.
   */
  @Override
  public LocalDate getBirthDate() {
    /*
     * if (birthDate == null) { throw new NullPointerException(); }
     */
    return birthDate;
  }

  /**
   * Modifie la date de naissance de l'utilisateur.
   * 
   * @param birthDate La nouvelle date de naissance.
   */
  @Override
  public void setBirthDate(LocalDate birthDate) {
    /*
     * if (birthDate == null || birthDate.isAfter(LocalDate.now())) { throw new
     * IllegalArgumentException(); }
     */
    this.birthDate = birthDate;
  }

  /**
   * Crypte l'attribut password de l'utilisateur qui doit être en clair.
   */
  @Override
  public void cryptPassword() {
    /*
     * if (this.password == null) { throw new NullPointerException(); }
     */
    this.password = BCrypt.hashpw(password, BCrypt.gensalt());
  }

  /**
   * Compare le mot de passe passé en paramètre avec le mot de passe crypté en attribut.
   * 
   * @param passwordToCheck Le mot de passe a comparer avec le mot de passe de l'utilisateur.
   * @return true si le mot de passe correspond, false sinon.
   */
  @Override
  public boolean checkPassword(String passwordToCheck) {
    /*
     * if (passwordToCheck == null) { throw new IllegalArgumentException(); } if (this.password ==
     * null) { throw new NullPointerException(); }
     */
    return BCrypt.checkpw(passwordToCheck, this.password);
  }



  @Override
  protected Object clone() throws CloneNotSupportedException {
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
