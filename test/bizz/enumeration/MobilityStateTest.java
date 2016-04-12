package bizz.enumeration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by anthony on 10/04/16.
 */
public class MobilityStateTest {

  @Test
  public void testValueOf() {
    assertEquals(MobilityState.valueOf("PENDING"), MobilityState.PENDING);
    assertEquals(MobilityState.valueOf("CREATED"), MobilityState.CREATED);
    assertEquals(MobilityState.valueOf("IN_PREPARATION"), MobilityState.IN_PREPARATION);
    assertEquals(MobilityState.valueOf("PAYMENT_CLEARANCE"), MobilityState.PAYMENT_CLEARANCE);
    assertEquals(MobilityState.valueOf("PAYMENT_REQUESTED"), MobilityState.PAYMENT_REQUESTED);
    assertEquals(MobilityState.valueOf("SECOND_PAYMENT_CLEARANCE"),
        MobilityState.SECOND_PAYMENT_CLEARANCE);
    assertEquals(MobilityState.valueOf("SECOND_PAYMENT_REQUESTED"),
        MobilityState.SECOND_PAYMENT_REQUESTED);
  }
}
