package bizz;

import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;

class UserImpl implements UserBizz {

  private String pseudo;
  private String password;
  private String name;
  private String firstname;
  private String email;
  private String address;
  private String tel;
  private String gender;
  private String permissions;
  private String iban;
  private String bic;
  private String accountHolder;
  private String bankName;
  private int successfullYearInCollege;
  private LocalDate registrationDate;
  private LocalDate birthDate;

  /**
   * Return user pseudo
   * 
   * @return Pseudo of the user.
   */
  @Override
  public String getPseudo() {
    if (pseudo == null)
      throw new NullPointerException();
    return pseudo;
  }

  /**
   * Edit user pseudo.
   * 
   * @param pseudo New pseudo for that user.
   */
  @Override
  public void setPseudo(String pseudo) {
    if(pseudo == null) throw new IllegalArgumentException();
    this.pseudo = pseudo;
  }

  /**
   * Return password for that user.
   * 
   * @return Password of the user.
   */
  @Override
  public String getPassword() {
    if (password == null)
      throw new NullPointerException();
    return password;
  }

  /**
   * Change the user password.
   * 
   * @param password New password for that user.
   */
  @Override
  public void setPassword(String password) {
    if(password == null) throw new IllegalArgumentException();
    this.password = password;
  }

  /**
   * Returns name for that user.
   * 
   * @return Name of the user.
   */
  @Override
  public String getName() {
    if (name == null)
      throw new NullPointerException();
    return name;
  }

  /**
   * Edit user name.
   * 
   * @param name New user name.
   */
  @Override
  public void setName(String name) {
    if (name == null)
      throw new IllegalArgumentException();
    this.name = name;
  }

  /**
   * Return firstname of the user.
   * 
   * @return firstname of the user.
   */
  @Override
  public String getFirstname() {
    if (firstname == null)
      throw new NullPointerException();
    return firstname;
  }

  /**
   * Edit user firstname.
   * 
   * @param firstname New user firstname.
   */
  @Override
  public void setFirstname(String firstname) {
    if (firstname == null)
      throw new IllegalArgumentException();
    this.firstname = firstname;
  }

  /**
   * Return user email.
   * 
   * @return Email of our user.
   */
  @Override
  public String getEmail() {
    if (email == null)
      throw new NullPointerException();
    return email;
  }

  /**
   * Edit user email
   * 
   * @param email New user email.
   */
  @Override
  public void setEmail(String email) {
    if (email == null)
      throw new IllegalArgumentException();
    this.email = email;
  }

  /**
   * Return address of an user.
   * 
   * @return user address.
   */
  @Override
  public String getAddress() {
    if (address == null)
      throw new NullPointerException();
    return address;
  }

  /**
   * Edit user address.
   * 
   * @param address New user address.
   */
  @Override
  public void setAddress(String address) {
    if (address == null)
      throw new IllegalArgumentException();
    this.address = address;
  }

  /**
   * Return user phone number of an user.
   * 
   * @return user phone number.
   */
  @Override
  public String getTel() {
    if (tel == null)
      throw new NullPointerException();
    return tel;
  }

  /**
   * Modifie le numero de telephone de l'utilisateur.
   * 
   * @param tel Le nouveau numero de telephone de l'utilisateur.
   */
  @Override
  public void setTel(String tel) {
    if (tel == null)
      throw new IllegalArgumentException();
    this.tel = tel;
  }

  /**
   * Renvoie le gender de l'utilisateur.
   * 
   * @return Le gender de l'utilisateur.
   */
  @Override
  public String getGender() {
    if (gender == null)
      throw new NullPointerException();
    return gender;
  }

  /**
   * Modifie le gender de l'utilisateur.
   * 
   * @param gender Le nouveau gender de l'utilisateur.
   */
  @Override
  public void setGender(String gender) {
    if (gender == null)
      throw new IllegalArgumentException();
    this.gender = gender;
  }

  /**
   * Renvoie les doits de l'utilisateur.
   * 
   * @return Les permissions de l'utilisateur.
   */
  @Override
  public String getPermissions() {
    if (permissions == null)
      throw new NullPointerException();
    return permissions;
  }

  /**
   * Modifie les permissions de l'utilisateur.
   * 
   * @param permissions Les nouveaux permissions de l'utilisateur.
   */
  @Override
  public void setPermissions(String permissions) {
    if (permissions == null)
      throw new IllegalArgumentException();
    this.permissions = permissions;
  }

  /**
   * Renvoie le numero de banque iban de l'utilisateur.
   * 
   * @return Le numero de banque iban de l'utilisateur.
   */
  @Override
  public String getIban() {
    if (iban == null)
      throw new NullPointerException();
    return iban;
  }

  /**
   * Modifie Modifie le numero de banque Iban de l'utilisateur.
   * 
   * @param iban Le nouveau numero de banque Iban de l'utilisateur.
   */
  @Override
  public void setIban(String iban) {
    if (iban == null)
      throw new IllegalArgumentException();
    this.iban = iban;
  }

  /**
   * Renvoie le numero de banque Bic de l'utilisateur.
   * 
   * @return Le numero de banque Bic de l'utilisateur.
   */
  @Override
  public String getBic() {
    if (bic == null)
      throw new NullPointerException();
    return bic;
  }

  /**
   * Modifie le numero de banque Bic de l'utilisateur.
   * 
   * @param bic Le nouveau numero de banque Bic.
   */
  @Override
  public void setBic(String bic) {
    if (bic == null)
      throw new IllegalArgumentException();
    this.bic = bic;
  }

  /**
   * Renvoie le nom du titulaire du compte en banque de l'utilisateur.
   * 
   * @return Le nom du titulaire du compte en banque de l'utilisateur.
   */
  @Override
  public String getAccountHolder() {
    if (accountHolder == null)
      throw new NullPointerException();
    return accountHolder;
  }

  /**
   * Modifie le nom du titulaire du compte en banque.
   * 
   * @param accountHolder Le nouveau nom du titulaire.
   */
  @Override
  public void setAccountHolder(String accountHolder) {
    if (accountHolder == null)
      throw new IllegalArgumentException();
    this.accountHolder = accountHolder;
  }

  /**
   * Renvoie le nom de la banque de l'utilisateur.
   * 
   * @return Le nom de la banque de l'utilisateur.
   */
  @Override
  public String getBankName() {
    if (bankName == null)
      throw new NullPointerException();
    return bankName;
  }

  /**
   * Modifie le nom de la banque de l'utilisateur.
   * 
   * @param bankName Le nouveau nom de la banque.
   */
  @Override
  public void setBankName(String bankName) {
    if (bankName == null)
      throw new IllegalArgumentException();
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
    if (successfullYearInCollege < 0)
      throw new IllegalArgumentException();
    this.successfullYearInCollege = successfullYearInCollege;
  }

  /**
   * Renvoie la date d'inscription de l'utilisateur.
   * 
   * @return La date d'inscription de l'utilisateur.
   */
  @Override
  public LocalDate getRegistrationDate() {
    if (registrationDate == null)
      throw new NullPointerException();
    return registrationDate;
  }

  /**
   * Modifie la date d'inscription.
   * 
   * @param registrationDate La nouvelle date d'inscription.
   */
  @Override
  public void setRegistrationDate(LocalDate registrationDate) {
    if (registrationDate == null)
      throw new IllegalArgumentException();
    this.registrationDate = registrationDate;
  }

  /**
   * Renvoie la date de naissance de l'utilisateur.
   * 
   * @return La date de naissance de l'utilisateur.
   */
  @Override
  public LocalDate getBirthDate() {
    if (birthDate == null)
      throw new NullPointerException();
    return birthDate;
  }

  /**
   * Modifie la date de naissance de l'utilisateur.
   * 
   * @param birthDate La nouvelle date de naissance.
   */
  @Override
  public void setBirthDate(LocalDate birthDate) {
    if (birthDate == null)
      throw new IllegalArgumentException();
    this.birthDate = birthDate;
  }

  /**
   * Crypte l'attribut password de l'utilisateur qui doit être en clair.
   */
  @Override
  public void cryptPassword() {
    if (this.password == null)
      throw new NullPointerException();
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
    if (passwordToCheck == null)
      throw new IllegalArgumentException();
    if (this.password == null)
      throw new NullPointerException();
    return BCrypt.checkpw(passwordToCheck, this.password);
  }
}
