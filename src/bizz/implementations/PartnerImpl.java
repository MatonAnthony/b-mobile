package bizz.implementations;

import bizz.interfaces.PartnerBizz;
import dto.CountryDto;

public class PartnerImpl implements PartnerBizz {

  private int id;
  private int idUser;
  // TODO (Martin) Ajouter un userDto si c'est nécessaire
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
    result = prime * result + ((type == null) ? 0 : type.hashCode());
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
    if (type == null) {
      if (other.type != null) {
        return false;
      }
    } else if (!type.equals(other.type)) {
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
