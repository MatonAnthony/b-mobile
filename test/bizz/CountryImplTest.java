
package bizz;

import org.junit.Before;
import org.junit.Test;

import bizz.implementations.CountryImpl;

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
   * Test method for {@link bizz.implementations.CountryImpl#getIso()}.
   */
  @Test
  public void testGetIso() {
    // TODO (Martin) remplir les tests
  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#setIso(java.lang.String)}.
   */
  @Test
  public void testSetIso() {

  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#getNameEn()}.
   */
  @Test
  public void testGetNameEn() {

  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#setNameEn(java.lang.String)}.
   */
  @Test
  public void testSetNameEn() {

  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#getNameFr()}.
   */
  @Test
  public void testGetNameFr() {

  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#setNameFr(java.lang.String)}.
   */
  @Test
  public void testSetNameFr() {

  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#getIdProgram()}.
   */
  @Test
  public void testGetIdProgram() {

  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#setIdProgram(int)}.
   */
  @Test
  public void testSetIdProgram() {

  }

}
