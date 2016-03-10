package dto;

import java.time.LocalDate;

public interface UserDto {

  void setPseudo(String pseudo);

  String getAddress();

  String getPseudo();

  String getPassword();

  void setPassword(String password);

  String getName();

  void setName(String name);

  String getFirstname();

  void setFirstname(String firstname);

  String getEmail();

  void setEmail(String email);

  void setAddress(String address);

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

}
