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
    cancelation.setResponsible("responsible");
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
    cancelation.setReason("no reason");
    assertEquals(cancelation.getReason(), "no reason");
  }

  @Test
  public void testGetResponsible() throws Exception {
    assertEquals(cancelation.getResponsible(), "responsible");
  }

  @Test
  public void testSetResponsible() throws Exception {
    cancelation.setResponsible("no one");
    assertEquals(cancelation.getResponsible(), "no one");
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
