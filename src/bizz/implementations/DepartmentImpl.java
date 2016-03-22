
package bizz.implementations;

import bizz.interfaces.DepartmentBizz;

public class DepartmentImpl implements DepartmentBizz {

  private String id;
  private String label;
  private int verNr;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
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
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((label == null) ? 0 : label.hashCode());
    result = prime * result + verNr;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DepartmentImpl other = (DepartmentImpl) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (label == null) {
      if (other.label != null)
        return false;
    } else if (!label.equals(other.label))
      return false;
    if (verNr != other.verNr)
      return false;
    return true;
  }



}
