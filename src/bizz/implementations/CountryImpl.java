package bizz.implementations;

import bizz.interfaces.CountryBizz;

public class CountryImpl implements CountryBizz {

  private String iso;
  private String nameEn;
  private String nameFr;
  private int idProgram;


  public String getIso() {
    return iso;
  }

  public void setIso(String iso) {
    this.iso = iso;
  }

  public String getNameEn() {
    return nameEn;
  }

  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }

  public String getNameFr() {
    return nameFr;
  }

  public void setNameFr(String nameFr) {
    this.nameFr = nameFr;
  }

  public int getIdProgram() {
    return idProgram;
  }

  public void setIdProgram(int idProgram) {
    this.idProgram = idProgram;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idProgram;
    result = prime * result + ((iso == null) ? 0 : iso.hashCode());
    result = prime * result + ((nameEn == null) ? 0 : nameEn.hashCode());
    result = prime * result + ((nameFr == null) ? 0 : nameFr.hashCode());
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
    CountryImpl other = (CountryImpl) obj;
    if (idProgram != other.idProgram) {
      return false;
    }
    if (iso == null) {
      if (other.iso != null) {
        return false;
      }
    } else if (!iso.equals(other.iso)) {
      return false;
    }
    if (nameEn == null) {
      if (other.nameEn != null) {
        return false;
      }
    } else if (!nameEn.equals(other.nameEn)) {
      return false;
    }
    if (nameFr == null) {
      if (other.nameFr != null) {
        return false;
      }
    } else if (!nameFr.equals(other.nameFr)) {
      return false;
    }
    return true;
  }



}
