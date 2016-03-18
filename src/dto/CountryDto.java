package dto;

public interface CountryDto {

  /**
   * Set the iso of the country.
   * 
   * @param iso the iso to set
   */
  void setIso(String iso);

  /**
   * Get the iso of the country.
   * 
   * @return the iso
   */
  String getIso();

  /**
   * Set the nameEn of the country.
   * 
   * @param nameEn the nameEn to set
   */
  void setNameEn(String nameEn);

  /**
   * Get the english name of the country.
   * 
   * @return the nameEn
   */
  String getNameEn();

  /**
   * Set the nameFr of the country.
   * 
   * @param nameFr the nameFr to set
   */
  void setNameFr(String nameFr);

  /**
   * Get the french name of the country.
   * 
   * @return the nameFr
   */
  String getNameFr();

  /**
   * Set the idProgram of the country.
   * 
   * @param idProgram the idProgram to set
   */
  void setIdProgram(int idProgram);

  /**
   * Get the program id for this country.
   * 
   * @return the idProgram
   */
  int getIdProgram();

}
