package bizz.implementations;

import bizz.interfaces.CancelationBizz;

public class CancelationImpl implements CancelationBizz {

  private int id;
  private String reason;
  private String responsible;
  private int verNr;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getResponsible() {
    return responsible;
  }

  public void setResponsible(String responsible) {
    this.responsible = responsible;
  }

  public int getVerNr() {
    return verNr;
  }

  public void setVerNr(int verNr) {
    this.verNr = verNr;
  }

}
