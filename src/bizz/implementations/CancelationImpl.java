package bizz.implementations;

import bizz.interfaces.CancelationBizz;

import java.util.regex.Pattern;

public class CancelationImpl implements CancelationBizz, Cloneable {

  private int id;
  private String reason;
  private String responsible;
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
  public String getReason() {
    return reason;
  }

  @Override
  public void setReason(String reason) {
    if (this.reason == null) {
      this.reason = null;
    } else if (!Pattern.matches("[A-zÀ-ÿ-\\s0-9\\W]{10,}", reason)) {
      throw new IllegalArgumentException("La raison doit contenir au moins 10 caractères.");
    }
    this.reason = reason;
  }

  @Override
  public String getResponsible() {
    return responsible;
  }

  @Override
  public void setResponsible(String responsible) {
    if (responsible == null) {
      this.responsible = null;
    } else if (!responsible.equals("TEACHER") && !responsible.equals("STUDENT")) {
      throw new IllegalArgumentException("Responsable incorrect, arrêter de jouer avec l'ajax !");
    }
    this.responsible = responsible;
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
    result = prime * result + id;
    result = prime * result + ((reason == null) ? 0 : reason.hashCode());
    result = prime * result + ((responsible == null) ? 0 : responsible.hashCode());
    result = prime * result + verNr;
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
    CancelationImpl other = (CancelationImpl) obj;
    if (id != other.id) {
      return false;
    }
    if (reason == null) {
      if (other.reason != null) {
        return false;
      }
    } else if (!reason.equals(other.reason)) {
      return false;
    }
    if (responsible == null) {
      if (other.responsible != null) {
        return false;
      }
    } else if (!responsible.equals(other.responsible)) {
      return false;
    }
    if (verNr != other.verNr) {
      return false;
    }
    return true;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
