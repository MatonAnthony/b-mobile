package dto;

/**
 * The interface Cancelation dto.
 */
public interface CancelationDto {

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId();

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(int id);

  /**
   * Gets reason.
   *
   * @return the reason
   */
  public String getReason();

  /**
   * Sets reason.
   *
   * @param reason the reason
   * @throws IllegalArgumentException the reason doesn't match [A-zÀ-ÿ-\s0-9\W]{10,}
   */
  public void setReason(String reason);

  /**
   * Gets responsible.
   *
   * @return the responsible
   */
  public String getResponsible();

  /**
   * Sets responsible.
   *
   * @param responsible the responsible
   */
  public void setResponsible(String responsible);

  /**
   * Gets ver nr.
   *
   * @return the ver nr
   */
  public int getVerNr();

  /**
   * Sets ver nr.
   *
   * @param verNr the ver nr
   */
  public void setVerNr(int verNr);

}
