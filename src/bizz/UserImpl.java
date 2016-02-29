package bizz;

import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

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
  public String getMdp() {
    return mdp;
  }

  /**
   * Modifie le mot de passe de l'utilisateur.
   * 
   * @param mdp Le nouveau mot de passe.
   */
  @Override
  public void setMdp(String mdp) {
    this.mdp = mdp;
  }

  /**
   * Renvoie le nom de l'utilisateur.
   * 
   * @return Le nom de l'utilisateur.
   */
  @Override
  public String getNom() {
    return nom;
  }

  /**
   * Modifie le nom de l'utilisateur.
   * 
   * @param nom Le nouveau nom de l'utilisateur.
   */
  @Override
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Renvoie le prenom de l'utilisateur.
   * 
   * @return Le prenom de l'utilisateur.
   */
  @Override
  public String getPrenom() {
    return prenom;
  }

  /**
   * Modifie le prenom de l'utilisateur.
   * 
   * @param prenom Le nouveau prenom de l'utilisateur.
   */
  @Override
  public void setPrenom(String prenom) {
    this.prenom = prenom;
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
   * Renvoie l'adresse de l'utilisateur.
   * 
   * @return L'adresse de l'utilisateur.
   */
  @Override
  public String getAdresse() {
    return adresse;
  }

  /**
   * Modifie l'adresse de l'utilisateur.
   * 
   * @param adresse La nouvelle adresse de l'utilisateur.
   */
  @Override
  public void setAdresse(String adresse) {
    this.adresse = adresse;
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
   * Renvoie le sexe de l'utilisateur.
   * 
   * @return Le sexe de l'utilisateur.
   */
  @Override
  public String getSexe() {
    return sexe;
  }

  /**
   * Modifie le sexe de l'utilisateur.
   * 
   * @param sexe Le nouveau sexe de l'utilisateur.
   */
  @Override
  public void setSexe(String sexe) {
    this.sexe = sexe;
  }

  /**
   * Renvoie les doits de l'utilisateur.
   * 
   * @return Les droits de l'utilisateur.
   */
  @Override
  public String getDroits() {
    return droits;
  }

  /**
   * Modifie les droits de l'utilisateur.
   * 
   * @param droits Les nouveaux droits de l'utilisateur.
   */
  @Override
  public void setDroits(String droits) {
    this.droits = droits;
  }

  /**
   * Renvoie le numero de banque iban de l'utilisateur.
   * 
   * @return Le numero de banque iban de l'utilisateur.
   */
  @Override
  public String getBanqueIban() {
    return banqueIban;
  }

  /**
   * Modifie Modifie le numero de banque Iban de l'utilisateur.
   * 
   * @param banqueIban Le nouveau numero de banque Iban de l'utilisateur.
   */
  @Override
  public void setBanqueIban(String banqueIban) {
    this.banqueIban = banqueIban;
  }

  /**
   * Renvoie le numero de banque Bic de l'utilisateur.
   * 
   * @return Le numero de banque Bic de l'utilisateur.
   */
  @Override
  public String getBanqueBic() {
    return banqueBic;
  }

  /**
   * Modifie le numero de banque Bic de l'utilisateur.
   * 
   * @param banqueBic Le nouveau numero de banque Bic.
   */
  @Override
  public void setBanqueBic(String banqueBic) {
    this.banqueBic = banqueBic;
  }

  /**
   * Renvoie le nom du titulaire du compte en banque de l'utilisateur.
   * 
   * @return Le nom du titulaire du compte en banque de l'utilisateur.
   */
  @Override
  public String getBanqueTitulaireNom() {
    return banqueTitulaireNom;
  }

  /**
   * Modifie le nom du titulaire du compte en banque.
   * 
   * @param banqueTitulaireNom Le nouveau nom du titulaire.
   */
  @Override
  public void setBanqueTitulaireNom(String banqueTitulaireNom) {
    this.banqueTitulaireNom = banqueTitulaireNom;
  }

  /**
   * Renvoie le nom de la banque de l'utilisateur.
   * 
   * @return Le nom de la banque de l'utilisateur.
   */
  @Override
  public String getBanqueNom() {
    return banqueNom;
  }

  /**
   * Modifie le nom de la banque de l'utilisateur.
   * 
   * @param banqueNom Le nouveau nom de la banque.
   */
  @Override
  public void setBanqueNom(String banqueNom) {
    this.banqueNom = banqueNom;
  }

  /**
   * Renvoie le nombre d'annee reussies par l'utilisateur.
   * 
   * @return Le nombre d'annee reussies par l'utilisateur.
   */
  @Override
  public int getNbAnneeReussiesEnsSup() {
    return nbAnneeReussiesEnsSup;
  }

  /**
   * Modifie le nombre d'annees reussies.
   * 
   * @param nbAnneeReussiesEnsSup Le nouveau nombre d'annees reussies.
   */
  @Override
  public void setNbAnneeReussiesEnsSup(int nbAnneeReussiesEnsSup) {
    this.nbAnneeReussiesEnsSup = nbAnneeReussiesEnsSup;
  }

  /**
   * Renvoie la date d'inscription de l'utilisateur.
   * 
   * @return La date d'inscription de l'utilisateur.
   */
  @Override
  public LocalDate getDateInscription() {
    return dateInscription;
  }

  /**
   * Modifie la date d'inscription.
   * 
   * @param dateInscription La nouvelle date d'inscription.
   */
  @Override
  public void setDateInscription(LocalDate dateInscription) {
    this.dateInscription = dateInscription;
  }

  /**
   * Renvoie la date de naissance de l'utilisateur.
   * 
   * @return La date de naissance de l'utilisateur.
   */
  @Override
  public LocalDate getDateNaissance() {
    return dateNaissance;
  }

  /**
   * Modifie la date de naissance de l'utilisateur.
   * 
   * @param dateNaissance La nouvelle date de naissance.
   */
  @Override
  public void setDateNaissance(LocalDate dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  /**
   * Crypte l'attribut mdp de l'utilisateur qui doit être en clair.
   */
  @Override
  public void cryptPassword() {
    this.mdp = BCrypt.hashpw(mdp, BCrypt.gensalt());
  }

  /**
   * Compare le mot de passe passé en paramètre avec le mot de passe crypté en attribut.
   * 
   * @param passwordToCheck Mot de passe à vérifier.
   * @return true si le mot de passe correspond, false sinon.
   */
  @Override
  public boolean checkPassword(String passwordToCheck) {
    return BCrypt.checkpw(passwordToCheck, this.mdp);
  }
}
