package bizz.implementations;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DepartmentImplTest {

  private DepartmentImpl puppet;

  @Before
  public void setUp() throws Exception {
    puppet = new DepartmentImpl();
    puppet.setId("BIN");
    puppet.setVerNr(0);
    puppet.setLabel("label");
  }

  @Test
  public void testGetId() {
    assertEquals(puppet.getId(), "BIN");
  }

  @Test
  public void testSetId() {
    puppet.setId("BIM");
    assertEquals(puppet.getId(), "BIM");
  }

  @Test
  public void testGetLabel() {
    assertEquals(puppet.getLabel(), "label");
  }

  @Test
  public void testSetLabel() {
    puppet.setLabel("et la bête");
    assertEquals(puppet.getLabel(), "et la bête");
  }

  @Test
  public void testGetVerNr() {
    assertEquals(0, puppet.getVerNr());
  }

}
