package bizz.implementations;

import bizz.interfaces.PartnerBizz;
import dto.CountryDto;
import dto.UserDto;

import java.util.regex.Pattern;

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
  private boolean deleted;
  private int verNr;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getIdUser() {
    return idUser;
  }

  @Override
  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  @Override
  public UserDto getUserDto() {
    return userDto;
  }

  @Override
  public void setUserDto(UserDto userDto) {
    this.userDto = userDto;
  }

  @Override
  public String getLegalName() {
    return legalName;
  }

  @Override
  public void setLegalName(String legalName) {
    if (legalName == null) {
      this.legalName = null;
    } else if (!Pattern.matches("[A-zÀ-ÿ-. ]*", legalName)) {
      throw new IllegalArgumentException("Le nom légal doit être cohérent");
    }
    this.legalName = legalName;
  }

  @Override
  public String getBusiness() {
    return businessName;
  }

  @Override
  public void setBusiness(String business) {
    if (business == null) {
      this.businessName = null;
    } else if (!Pattern.matches("[A-zÀ-ÿ-. ]*", business)) {
      throw new IllegalArgumentException("Le nom du business doit être cohérent");
    }
    this.businessName = business;
  }

  @Override
  public String getFullName() {
    return fullName;
  }

  @Override
  public void setFullName(String fullName) {
    if (fullName == null) {
      this.fullName = null;
    } else if (!Pattern.matches("[A-zÀ-ÿ-. ]*", fullName)) {
      throw new IllegalArgumentException("Le nom complet doit être cohérent");
    }
    this.fullName = fullName;
  }

  @Override
  public String getDepartment() {
    return department;
  }

  @Override
  public void setDepartment(String department) {
    this.department = department;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public void setType(String type) {
    // TODO Check validation with Martin
    this.type = type;
  }

  @Override
  public int getNbEmployees() {
    return nbEmployees;
  }

  @Override
  public void setNbEmployees(int nbEmployees) {
    /*if(nbEmployees <= 0){
      throw new IllegalArgumentException("Le nombre d'employés doit être supérieur à 0");
    }*/
    this.nbEmployees = nbEmployees;
  }

  @Override
  public String getStreet() {
    return street;
  }

  @Override
  public void setStreet(String street) {
    if (street == null) {
      this.street = null;
    } else if (!Pattern.matches("[A-zÀ-ÿ-. 0-9]*", street)) {
      throw new IllegalArgumentException("Le nom de la rue doit-être cohérent");
    }
    this.street = street;
  }

  @Override
  public String getNumber() {
    return number;
  }

  @Override
  public void setNumber(String number) {
    // TODO : Pourquoi est-ce un string ?
    this.number = number;
  }

  @Override
  public String getMailbox() {
    return mailbox;
  }

  @Override
  public void setMailbox(String mailbox) {
    this.mailbox = mailbox;
  }

  @Override
  public String getZip() {
    return zip;
  }

  @Override
  public void setZip(String zip) {
    this.zip = zip;
  }

  @Override
  public String getCity() {
    return city;
  }

  @Override
  public void setCity(String city) {
    if (city == null) {
      this.city = null;
    } else if (!Pattern.matches("[A-zÀ-ÿ-. ]*", city)) {
      throw new IllegalArgumentException(
          "Le nom de la ville ne peut contenir de chiffres ou de" + "caractères spéciaux.");
    }
    this.city = city;
  }

  @Override
  public String getState() {
    return state;
  }

  @Override
  public void setState(String state) {
    if (this.state == null) {
      this.state = null;
    } else if (!Pattern.matches("[A-zÀ-ÿ-. ]*", state)) {
      throw new IllegalArgumentException("La région ne peut contenir de caractères spéciaux");
    }
    this.state = state;
  }

  @Override
  public String getTel() {
    return tel;
  }

  @Override
  public void setTel(String tel) {
    this.tel = tel;
  }

  @Override
  public CountryDto getCountryDto() {
    return countryDto;
  }

  @Override
  public void setCountryDto(CountryDto countryDto) {
    this.countryDto = countryDto;
  }

  @Override
  public String getCountry() {
    return country;
  }

  @Override
  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    if (email == null || email.equals("")) {
      this.email = null;
    } else if (!Pattern.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)"
        + "*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email)) {
      throw new IllegalArgumentException("Email invalide");
    }
    this.email = email;
  }

  @Override
  public String getWebsite() {
    return website;
  }

  @Override
  public void setWebsite(String website) {
    // TODO : FIX THE ISSUE WITH PATTERN
    String pattern = "/^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$/";
    if (website == null) {
      this.website = website;
    } else if (!Pattern.matches(pattern, website)) {
      // throw new IllegalArgumentException("Format de l'adresse du site internet invalide");
      this.website = website;
    }
    this.website = website;
  }

  @Override
  public boolean isExists() {
    return exists;
  }

  @Override
  public void setExists(boolean exists) {
    this.exists = exists;
  }

  @Override
  public boolean isDeleted() {
    return deleted;
  }

  @Override
  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public int getVerNr() {
    return verNr;
  }

  @Override
  public void setVerNr(int verNr) {
    this.verNr = verNr;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((country == null) ? 0 : country.hashCode());
    result = prime * result + ((countryDto == null) ? 0 : countryDto.hashCode());
    result = prime * result + ((department == null) ? 0 : department.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + (exists ? 1231 : 1237);
    result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
    result = prime * result + id;
    result = prime * result + idUser;
    result = prime * result + ((legalName == null) ? 0 : legalName.hashCode());
    result = prime * result + ((mailbox == null) ? 0 : mailbox.hashCode());
    result = prime * result + nbEmployees;
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((street == null) ? 0 : street.hashCode());
    result = prime * result + ((tel == null) ? 0 : tel.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + ((userDto == null) ? 0 : userDto.hashCode());
    result = prime * result + verNr;
    result = prime * result + ((website == null) ? 0 : website.hashCode());
    result = prime * result + ((zip == null) ? 0 : zip.hashCode());
    return result;
  }

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
    PartnerImpl other = (PartnerImpl) obj;
    if (businessName == null) {
      if (other.businessName != null) {
        return false;
      }
    } else if (!businessName.equals(other.businessName)) {
      return false;
    }
    if (city == null) {
      if (other.city != null) {
        return false;
      }
    } else if (!city.equals(other.city)) {
      return false;
    }
    if (country == null) {
      if (other.country != null) {
        return false;
      }
    } else if (!country.equals(other.country)) {
      return false;
    }
    if (countryDto == null) {
      if (other.countryDto != null) {
        return false;
      }
    } else if (!countryDto.equals(other.countryDto)) {
      return false;
    }
    if (department == null) {
      if (other.department != null) {
        return false;
      }
    } else if (!department.equals(other.department)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (exists != other.exists) {
      return false;
    }
    if (fullName == null) {
      if (other.fullName != null) {
        return false;
      }
    } else if (!fullName.equals(other.fullName)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (idUser != other.idUser) {
      return false;
    }
    if (legalName == null) {
      if (other.legalName != null) {
        return false;
      }
    } else if (!legalName.equals(other.legalName)) {
      return false;
    }
    if (mailbox == null) {
      if (other.mailbox != null) {
        return false;
      }
    } else if (!mailbox.equals(other.mailbox)) {
      return false;
    }
    if (nbEmployees != other.nbEmployees) {
      return false;
    }
    if (number == null) {
      if (other.number != null) {
        return false;
      }
    } else if (!number.equals(other.number)) {
      return false;
    }
    if (state == null) {
      if (other.state != null) {
        return false;
      }
    } else if (!state.equals(other.state)) {
      return false;
    }
    if (street == null) {
      if (other.street != null) {
        return false;
      }
    } else if (!street.equals(other.street)) {
      return false;
    }
    if (tel == null) {
      if (other.tel != null) {
        return false;
      }
    } else if (!tel.equals(other.tel)) {
      return false;
    }
    if (type == null) {
      if (other.type != null) {
        return false;
      }
    } else if (!type.equals(other.type)) {
      return false;
    }
    if (userDto == null) {
      if (other.userDto != null) {
        return false;
      }
    } else if (!userDto.equals(other.userDto)) {
      return false;
    }
    if (verNr != other.verNr) {
      return false;
    }
    if (website == null) {
      if (other.website != null) {
        return false;
      }
    } else if (!website.equals(other.website)) {
      return false;
    }
    if (zip == null) {
      if (other.zip != null) {
        return false;
      }
    } else if (!zip.equals(other.zip)) {
      return false;
    }
    return true;
  }
}
