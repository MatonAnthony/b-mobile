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

}
