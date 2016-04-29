package bizz.implementations;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by anthony on 10/04/16.
 */
public class CancelationImplTest {

  private CancelationImpl cancelation;

  @Before
  public void setUp() throws Exception {
    cancelation = (CancelationImpl) (new BizzFactoryImpl()).getCancelationDto();
    cancelation.setId(1);
    cancelation.setReason("reason");
    cancelation.setResponsible("STUDENT");
    cancelation.setVerNr(1);
  }

  @Test
  public void testGetId() throws Exception {
    assertEquals(cancelation.getId(), 1);
  }

  @Test
  public void testSetId() throws Exception {
    cancelation.setId(2);
    assertEquals(cancelation.getId(), 2);
  }

  @Test
  public void testGetReason() throws Exception {
    assertEquals(cancelation.getReason(), "reason");
  }

  @Test
  public void testSetReason() throws Exception {
    cancelation.setReason("no reason given");
    assertEquals(cancelation.getReason(), "no reason given");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetReason2() throws Exception {
    cancelation.setReason("nope");
    cancelation.getReason();
  }

  @Test
  public void testGetResponsible() throws Exception {
    assertEquals(cancelation.getResponsible(), "STUDENT");
  }

  @Test
  public void testGetResponsible2() throws Exception {
    cancelation.setResponsible(null);
    assertEquals(cancelation.getResponsible(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetResponsible3() throws Exception {
    cancelation.setResponsible("POULE");
    cancelation.getResponsible();
  }

  @Test
  public void testSetResponsible() throws Exception {
    cancelation.setResponsible("TEACHER");
    assertEquals(cancelation.getResponsible(), "TEACHER");
  }

  @Test
  public void testGetVerNr() throws Exception {
    assertEquals(cancelation.getVerNr(), 1);
  }

  @Test
  public void testSetVerNr() throws Exception {
    cancelation.setVerNr(2);
    assertEquals(cancelation.getVerNr(), 2);
  }

  @Test
  public void testHashCode() throws Exception {
    CancelationImpl clone = (CancelationImpl) cancelation.clone();
    assertEquals(clone.hashCode(), cancelation.hashCode());
  }

  @Test
  public void testEquals() throws Exception {
    assertEquals(cancelation, cancelation.clone());
  }
}
