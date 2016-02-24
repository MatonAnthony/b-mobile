package bizz;

import java.time.LocalDate;

public interface UserDto {

  void setPseudo(String pseudo);

  String getAdresse();

  String getPseudo();

  String getMdp();

  void setMdp(String mdp);

  String getNom();

  void setNom(String nom);

  String getPrenom();

  void setPrenom(String prenom);

  String getEmail();

  void setEmail(String email);

  void setAdresse(String adresse);

  String getTel();

  void setTel(String tel);

  String getSexe();

  void setSexe(String sexe);

  String getDroits();

  void setDroits(String droits);

  String getBanqueIban();

  void setBanqueIban(String banqueIban);

  String getBanqueBic();

  void setBanqueBic(String banqueBic);

  String getBanqueTitulaireNom();

  void setBanqueTitulaireNom(String banqueTitulaireNom);

  void setBanqueNom(String banqueNom);

  String getBanqueNom();

  int getNbAnneeReussiesEnsSup();

  void setNbAnneeReussiesEnsSup(int nbAnneeReussiesEnsSup);

  LocalDate getDateInscription();

  void setDateInscription(LocalDate dateInscription);

  LocalDate getDateNaissance();

  void setDateNaissance(LocalDate dateNaissance);

}
