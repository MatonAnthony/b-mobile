package bizz.implementations;

import bizz.interfaces.PartnerBizz;

public class PartnerImpl implements PartnerBizz {

  private int id;
  private int idUser;
  private String legalName;
  private String business;
  private String fullName;
  private String department;
  private String type;
  private int nbEmployees;
  private String street;
  private String number;
  private String mailbox;
  private String zip;
  private String city;
  private String state;
  private String country;
  private String email;
  private String website;
  private boolean exists;
  private int ver_nr;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  public String getLegalName() {
    return legalName;
  }

  public void setLegalName(String legalName) {
    this.legalName = legalName;
  }

  public String getBusiness() {
    return business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getNbEmployees() {
    return nbEmployees;
  }

  public void setNbEmployees(int nbEmployees) {
    this.nbEmployees = nbEmployees;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getMailbox() {
    return mailbox;
  }

  public void setMailbox(String mailbox) {
    this.mailbox = mailbox;
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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public boolean isExists() {
    return exists;
  }

  public void setExists(boolean exists) {
    this.exists = exists;
  }

  public int getVer_nr() {
    return ver_nr;
  }

  public void setVer_nr(int ver_nr) {
    this.ver_nr = ver_nr;
  }



}
