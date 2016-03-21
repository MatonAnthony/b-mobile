
package bizz.implementations;

import bizz.interfaces.DepartmentBizz;

public class DepartmentImpl implements DepartmentBizz {

  private String id;
  private String label;
  private int ver_nr;

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

  public int getVer_nr() {
    return ver_nr;
  }

  public void setVer_nr(int ver_nr) {
    this.ver_nr = ver_nr;
  }

}
