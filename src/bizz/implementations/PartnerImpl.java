package bizz.implementations;

import bizz.interfaces.PartnerBizz;
import dto.CountryDto;
import dto.UserDto;

public class PartnerImpl implements PartnerBizz {

  private int id;
  private int idUser;
  private UserDto userDto;
  private String legalName;
  private String businessName;
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
  private String tel;
  private String country;
  private CountryDto countryDto;
  private String email;
  private String website;
  private boolean exists;
  private int verNr;


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

  public UserDto getUserDto() {
    return userDto;
  }

  public void setUserDto(UserDto userDto) {
    this.userDto = userDto;
  }

  public String getLegalName() {
    return legalName;
  }

  public void setLegalName(String legalName) {
    this.legalName = legalName;
  }

  public String getBusiness() {
    return businessName;
  }

  public void setBusiness(String business) {
    this.businessName = business;
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

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public CountryDto getCountryDto() {
    return countryDto;
  }

  public void setCountryDto(CountryDto countryDto) {
    this.countryDto = countryDto;
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

  public int getVerNr() {
    return verNr;
  }

  public void setVerNr(int verNr) {
    this.verNr = verNr;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    PartnerImpl partner = (PartnerImpl) o;

    if (id != partner.id)
      return false;
    if (idUser != partner.idUser)
      return false;
    if (nbEmployees != partner.nbEmployees)
      return false;
    if (exists != partner.exists)
      return false;
    if (verNr != partner.verNr)
      return false;
    if (userDto != null ? !userDto.equals(partner.userDto) : partner.userDto != null)
      return false;
    if (legalName != null ? !legalName.equals(partner.legalName) : partner.legalName != null)
      return false;
    if (businessName != null ?
        !businessName.equals(partner.businessName) :
        partner.businessName != null)
      return false;
    if (fullName != null ? !fullName.equals(partner.fullName) : partner.fullName != null)
      return false;
    if (department != null ? !department.equals(partner.department) : partner.department != null)
      return false;
    if (type != null ? !type.equals(partner.type) : partner.type != null)
      return false;
    if (street != null ? !street.equals(partner.street) : partner.street != null)
      return false;
    if (number != null ? !number.equals(partner.number) : partner.number != null)
      return false;
    if (mailbox != null ? !mailbox.equals(partner.mailbox) : partner.mailbox != null)
      return false;
    if (zip != null ? !zip.equals(partner.zip) : partner.zip != null)
      return false;
    if (city != null ? !city.equals(partner.city) : partner.city != null)
      return false;
    if (state != null ? !state.equals(partner.state) : partner.state != null)
      return false;
    if (tel != null ? !tel.equals(partner.tel) : partner.tel != null)
      return false;
    if (country != null ? !country.equals(partner.country) : partner.country != null)
      return false;
    if (countryDto != null ? !countryDto.equals(partner.countryDto) : partner.countryDto != null)
      return false;
    if (email != null ? !email.equals(partner.email) : partner.email != null)
      return false;
    return website != null ? website.equals(partner.website) : partner.website == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + idUser;
    result = 31 * result + (userDto != null ? userDto.hashCode() : 0);
    result = 31 * result + (legalName != null ? legalName.hashCode() : 0);
    result = 31 * result + (businessName != null ? businessName.hashCode() : 0);
    result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
    result = 31 * result + (department != null ? department.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + nbEmployees;
    result = 31 * result + (street != null ? street.hashCode() : 0);
    result = 31 * result + (number != null ? number.hashCode() : 0);
    result = 31 * result + (mailbox != null ? mailbox.hashCode() : 0);
    result = 31 * result + (zip != null ? zip.hashCode() : 0);
    result = 31 * result + (city != null ? city.hashCode() : 0);
    result = 31 * result + (state != null ? state.hashCode() : 0);
    result = 31 * result + (tel != null ? tel.hashCode() : 0);
    result = 31 * result + (country != null ? country.hashCode() : 0);
    result = 31 * result + (countryDto != null ? countryDto.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (website != null ? website.hashCode() : 0);
    result = 31 * result + (exists ? 1 : 0);
    result = 31 * result + verNr;
    return result;
  }
}
