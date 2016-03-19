
package bizz;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class CountryImplTest {

  private CountryImpl puppet;
  private CountryImpl empty;


  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    empty = new CountryImpl();
    puppet = new CountryImpl();
    puppet.setIdProgram(3);
    puppet.setIso("be");
    puppet.setNameEn("Belgium");
    puppet.setNameFr("Belgique");
  }

  /**
   * Test method for {@link bizz.CountryImpl#getIso()}.
   */
  @Test
  public void testGetIso() {
    // TODO (Martin) remplir les tests
  }

  /**
   * Test method for {@link bizz.CountryImpl#setIso(java.lang.String)}.
   */
  @Test
  public void testSetIso() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link bizz.CountryImpl#getNameEn()}.
   */
  @Test
  public void testGetNameEn() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link bizz.CountryImpl#setNameEn(java.lang.String)}.
   */
  @Test
  public void testSetNameEn() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link bizz.CountryImpl#getNameFr()}.
   */
  @Test
  public void testGetNameFr() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link bizz.CountryImpl#setNameFr(java.lang.String)}.
   */
  @Test
  public void testSetNameFr() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link bizz.CountryImpl#getIdProgram()}.
   */
  @Test
  public void testGetIdProgram() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link bizz.CountryImpl#setIdProgram(int)}.
   */
  @Test
  public void testSetIdProgram() {
    fail("Not yet implemented");
  }

}
