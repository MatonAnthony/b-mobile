
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

}
