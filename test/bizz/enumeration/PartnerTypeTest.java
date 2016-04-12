package bizz.enumeration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by anthony on 10/04/16.
 */
public class PartnerTypeTest {

  @Test
  public void testValueOf() {
    assertEquals(PartnerType.valueOf("TPE"), PartnerType.TPE);
    assertEquals(PartnerType.valueOf("PME"), PartnerType.PME);
    assertEquals(PartnerType.valueOf("ETI"), PartnerType.ETI);
    assertEquals(PartnerType.valueOf("TGE"), PartnerType.TGE);

  }
}
