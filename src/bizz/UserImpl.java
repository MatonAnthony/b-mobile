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
   * Renvoie le pseudo de l'utilisateur.
   * 
   * @return Le pseudo de l'utilisateur.
   */
  @Override
  public String getPseudo() {
    return pseudo;
  }

  /**
   * Modifie le pseudo de l'utilisateur.
   * 
   * @param pseudo Le nouveau pseudo de l'utilisateur.
   */
  @Override
  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  /**
   * Renvoie le mot de passe de l'utilisateur.
   * 
   * @return Le mot de passe de l'utilisateur.
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Modifie le mot de passe de l'utilisateur.
   * 
   * @param password Le nouveau mot de passe.
   */
  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Renvoie le name de l'utilisateur.
   * 
   * @return Le name de l'utilisateur.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Modifie le name de l'utilisateur.
   * 
   * @param name Le nouveau name de l'utilisateur.
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Renvoie le firstname de l'utilisateur.
   * 
   * @return Le firstname de l'utilisateur.
   */
  @Override
  public String getFirstname() {
    return firstname;
  }

  /**
   * Modifie le firstname de l'utilisateur.
   * 
   * @param firstname Le nouveau firstname de l'utilisateur.
   */
  @Override
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  /**
   * Renvoie l'email de l'utilisateur.
   * 
   * @return L'email de l'utilisateur.
   */
  @Override
  public String getEmail() {
    return email;
  }

  /**
   * Modifie l'email de l'utilisateur.
   * 
   * @param email Le nouvel email de l'utilisateur.
   */
  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Renvoie l'address de l'utilisateur.
   * 
   * @return L'address de l'utilisateur.
   */
  @Override
  public String getAddress() {
    return address;
  }

  /**
   * Modifie l'address de l'utilisateur.
   * 
   * @param address La nouvelle address de l'utilisateur.
   */
  @Override
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Renvoie le numero de telephone de l'utilisateur.
   * 
   * @return Le numero de telephone de l'utilisateur.
   */
  @Override
  public String getTel() {
    return tel;
  }

  /**
   * Modifie le numero de telephone de l'utilisateur.
   * 
   * @param tel Le nouveau numero de telephone de l'utilisateur.
   */
  @Override
  public void setTel(String tel) {
    this.tel = tel;
  }

  /**
   * Renvoie le gender de l'utilisateur.
   * 
   * @return Le gender de l'utilisateur.
   */
  @Override
  public String getGender() {
    return gender;
  }

  /**
   * Modifie le gender de l'utilisateur.
   * 
   * @param gender Le nouveau gender de l'utilisateur.
   */
  @Override
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * Renvoie les doits de l'utilisateur.
   * 
   * @return Les permissions de l'utilisateur.
   */
  @Override
  public String getPermissions() {
    return permissions;
  }

  /**
   * Modifie les permissions de l'utilisateur.
   * 
   * @param permissions Les nouveaux permissions de l'utilisateur.
   */
  @Override
  public void setPermissions(String permissions) {
    this.permissions = permissions;
  }

  /**
   * Renvoie le numero de banque iban de l'utilisateur.
   * 
   * @return Le numero de banque iban de l'utilisateur.
   */
  @Override
  public String getIban() {
    return iban;
  }

  /**
   * Modifie Modifie le numero de banque Iban de l'utilisateur.
   * 
   * @param iban Le nouveau numero de banque Iban de l'utilisateur.
   */
  @Override
  public void setIban(String iban) {
    this.iban = iban;
  }

  /**
   * Renvoie le numero de banque Bic de l'utilisateur.
   * 
   * @return Le numero de banque Bic de l'utilisateur.
   */
  @Override
  public String getBic() {
    return bic;
  }

  /**
   * Modifie le numero de banque Bic de l'utilisateur.
   * 
   * @param bic Le nouveau numero de banque Bic.
   */
  @Override
  public void setBic(String bic) {
    this.bic = bic;
  }

  /**
   * Renvoie le nom du titulaire du compte en banque de l'utilisateur.
   * 
   * @return Le nom du titulaire du compte en banque de l'utilisateur.
   */
  @Override
  public String getAccountHolder() {
    return accountHolder;
  }

  /**
   * Modifie le nom du titulaire du compte en banque.
   * 
   * @param accountHolder Le nouveau nom du titulaire.
   */
  @Override
  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }

  /**
   * Renvoie le nom de la banque de l'utilisateur.
   * 
   * @return Le nom de la banque de l'utilisateur.
   */
  @Override
  public String getBankName() {
    return bankName;
  }

  /**
   * Modifie le nom de la banque de l'utilisateur.
   * 
   * @param bankName Le nouveau nom de la banque.
   */
  @Override
  public void setBankName(String bankName) {
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
    this.successfullYearInCollege = successfullYearInCollege;
  }

  /**
   * Renvoie la date d'inscription de l'utilisateur.
   * 
   * @return La date d'inscription de l'utilisateur.
   */
  @Override
  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  /**
   * Modifie la date d'inscription.
   * 
   * @param registrationDate La nouvelle date d'inscription.
   */
  @Override
  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }

  /**
   * Renvoie la date de naissance de l'utilisateur.
   * 
   * @return La date de naissance de l'utilisateur.
   */
  @Override
  public LocalDate getBirthDate() {
    return birthDate;
  }

  /**
   * Modifie la date de naissance de l'utilisateur.
   * 
   * @param birthDate La nouvelle date de naissance.
   */
  @Override
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * Crypte l'attribut password de l'utilisateur qui doit être en clair.
   */
  @Override
  public void cryptPassword() {
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
    return BCrypt.checkpw(passwordToCheck, this.password);
  }
}
