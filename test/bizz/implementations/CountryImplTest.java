
package bizz.implementations;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CountryImplTest {

  private CountryImpl puppet;
  private CountryImpl empty;


  /**
   * Set up the tests
   * 
   * @throws java.lang.Exception If there is an error.
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
    assertEquals(puppet.getIso(), "be");
  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#setIso(java.lang.String)}.
   */
  @Test
  public void testSetIso() {
    puppet.setIso("fr");
    assertEquals(puppet.getIso(), "fr");
  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#getNameEn()}.
   */
  @Test
  public void testGetNameEn() {
    assertEquals(puppet.getNameEn(), "Belgium");
  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#setNameEn(java.lang.String)}.
   */
  @Test
  public void testSetNameEn() {
    puppet.setNameEn("China");
    assertEquals(puppet.getNameEn(), "China");
  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#getNameFr()}.
   */
  @Test
  public void testGetNameFr() {
    assertEquals(puppet.getNameFr(), "Belgique");
  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#setNameFr(java.lang.String)}.
   */
  @Test
  public void testSetNameFr() {
    puppet.setNameFr("Chine");
    assertEquals(puppet.getNameFr(), "Chine");
  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#getIdProgram()}.
   */
  @Test
  public void testGetIdProgram() {
    assertEquals(puppet.getIdProgram(), 3);
  }

  /**
   * Test method for {@link bizz.implementations.CountryImpl#setIdProgram(int)}.
   */
  @Test
  public void testSetIdProgram() {
    puppet.setIdProgram(0);
    assertEquals(puppet.getIdProgram(), 0);
  }

}
