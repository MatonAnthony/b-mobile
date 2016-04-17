package bizz.implementations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anthony on 17/04/16.
 */
public class ProgramImplTest {

  private ProgramImpl puppet;

  @Before
  public void setUp() {
    puppet = new ProgramImpl();
    puppet.setVerNr(0);
    puppet.setDescription("description");
    puppet.setId(0);
    puppet.setName("name");
  }

  @Test
  public void testGetId() throws Exception {
    assertEquals(puppet.getId(), 0);
  }

  @Test
  public void testSetId() throws Exception {
    puppet.setId(1);
    assertEquals(puppet.getId(), 1);
  }

  @Test
  public void testGetName() throws Exception {
    assertEquals(puppet.getName(), "name");
  }

  @Test
  public void testSetName() throws Exception {
    puppet.setName("eman");
    assertEquals(puppet.getName(), "eman");
  }

  @Test
  public void testGetDescription() throws Exception {
    assertEquals(puppet.getDescription(), "description");
  }

  @Test
  public void testSetDescription() throws Exception {
    puppet.setDescription("--");
    assertEquals(puppet.getDescription(), "--");
  }

  @Test
  public void testGetVerNr() throws Exception {
    assertEquals(puppet.getVerNr(), 0);
  }

  @Test
  public void testSetVerNr() throws Exception {
    puppet.setVerNr(1);
    assertEquals(puppet.getVerNr(), 1);
  }

  @Test
  public void testHashCode() throws Exception {
    // TODO With junit-addons (si autorisé)
  }

  @Test
  public void testEquals() throws Exception {
    // TODO With junit-addons (si autorisé)
  }
}
