package bizz;

import java.time.LocalDate;

class UserImpl implements UserBizz {

  private String pseudo;
  private String mdp;
  private String nom;
  private String prenom;
  private String email;
  private String adresse;
  private String tel;
  private String sexe;
  private String droits;
  private String banqueIban;
  private String banqueBic;
  private String banqueTitulaireNom;
  private String banqueNom;
  private int nbAnneeReussiesEnsSup;
  private LocalDate dateInscription;
  private LocalDate dateNaissance;

  /**
   * @return the pseudo.
   */
  @Override
  public String getPseudo() {
    return pseudo;
  }

  /**
   * @param pseudo the pseudo to set.
   */
  @Override
  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  /**
   * @return the mdp.
   */
  @Override
  public String getMdp() {
    return mdp;
  }

  /**
   * @param mdp the mdp to set.
   */
  @Override
  public void setMdp(String mdp) {
    this.mdp = mdp;
  }

  /**
   * @return the nom.
   */
  @Override
  public String getNom() {
    return nom;
  }

  /**
   * @param nom the nom to set.
   */
  @Override
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * @return the prenom.
   */
  @Override
  public String getPrenom() {
    return prenom;
  }

  /**
   * @param prenom the prenom to set.
   */
  @Override
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  /**
   * @return the email.
   */
  @Override
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set.
   */
  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the adresse.
   */
  @Override
  public String getAdresse() {
    return adresse;
  }

  /**
   * @param adresse the adresse to set.
   */
  @Override
  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  /**
   * @return the tel.
   */
  @Override
  public String getTel() {
    return tel;
  }

  /**
   * @param tel the tel to set.
   */
  @Override
  public void setTel(String tel) {
    this.tel = tel;
  }

  /**
   * @return the sexe.
   */
  @Override
  public String getSexe() {
    return sexe;
  }

  /**
   * @param sexe the sexe to set.
   */
  @Override
  public void setSexe(String sexe) {
    this.sexe = sexe;
  }

  /**
   * @return the droits.
   */
  @Override
  public String getDroits() {
    return droits;
  }

  /**
   * @param droits the droits to set.
   */
  @Override
  public void setDroits(String droits) {
    this.droits = droits;
  }

  /**
   * @return the banqueIban.
   */
  @Override
  public String getBanqueIban() {
    return banqueIban;
  }

  /**
   * @param banqueIban the banqueIban to set.
   */
  @Override
  public void setBanqueIban(String banqueIban) {
    this.banqueIban = banqueIban;
  }

  /**
   * @return the banqueBic.
   */
  @Override
  public String getBanqueBic() {
    return banqueBic;
  }

  /**
   * @param banqueBic the banqueBic to set.
   */
  @Override
  public void setBanqueBic(String banqueBic) {
    this.banqueBic = banqueBic;
  }

  /**
   * @return the banqueTitulaireNom.
   */
  @Override
  public String getBanqueTitulaireNom() {
    return banqueTitulaireNom;
  }

  /**
   * @param banqueTitulaireNom the banqueTitulaireNom to set.
   */
  @Override
  public void setBanqueTitulaireNom(String banqueTitulaireNom) {
    this.banqueTitulaireNom = banqueTitulaireNom;
  }

  /**
   * @return the banqueNom.
   */
  @Override
  public String getBanqueNom() {
    return banqueNom;
  }

  /**
   * @param banqueNom the banqueNom to set.
   */
  @Override
  public void setBanqueNom(String banqueNom) {
    this.banqueNom = banqueNom;
  }

  /**
   * @return the nbAnneeReussiesEnsSup.
   */
  @Override
  public int getNbAnneeReussiesEnsSup() {
    return nbAnneeReussiesEnsSup;
  }

  /**
   * @param nbAnneeReussiesEnsSup the nbAnneeReussiesEnsSup to set.
   */
  @Override
  public void setNbAnneeReussiesEnsSup(int nbAnneeReussiesEnsSup) {
    this.nbAnneeReussiesEnsSup = nbAnneeReussiesEnsSup;
  }

  /**
   * @return the dateInscription.
   */
  @Override
  public LocalDate getDateInscription() {
    return dateInscription;
  }

  /**
   * @param dateInscription the dateInscription to set.
   */
  @Override
  public void setDateInscription(LocalDate dateInscription) {
    this.dateInscription = dateInscription;
  }

  /**
   * @return the dateNaissance.
   */
  @Override
  public LocalDate getDateNaissance() {
    return dateNaissance;
  }

  /**
   * @param dateNaissance the dateNaissance to set.
   */
  @Override
  public void setDateNaissance(LocalDate dateNaissance) {
    this.dateNaissance = dateNaissance;
  }



}
