package bizz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anthony on 10/03/16.
 */
public class UserImplTest {

    private UserImpl puppet;

    @Before
    public void setUp() throws Exception {
        puppet = new UserImpl();
        puppet.setPseudo("puppet");
        puppet.setPassword("master");
    }

    /*
     * Test return pseudo
     */
    @Test
    public void testGetPseudo() {
        assertEquals(puppet.getPseudo(), "puppet");
    }

    /*
     * Test setPseudo() with null as a pseudo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPseudo() {
        puppet.setPseudo(null);
    }

    /*
     * Test setPseudo() with a normal string as a pseudo.
     */
    @Test
    public void testSetPseudo1() {
        puppet.setPseudo("puppeteer");
        assertEquals(puppet.getPseudo(), "puppeteer");
    }

    /*
     * Test getPassword()
     */
    @Test
    public void testGetPassword() {
        assertEquals(puppet.getPassword(), "master");
    }

    /*
     * Test setPassword() with null as a parameter
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPassword() {
        puppet.setPassword(null);
    }

    /*
     * Test setPassword() with a correct parameter
     */
    @Test
    public void testSetPassword1() {
        puppet.setPassword("password");
        assertEquals(puppet.getPassword(), "password");
    }

    @Test
    public void testGetName() {

    }

    @Test
    public void testSetName() {

    }

    @Test
    public void testGetFirstname() {

    }

    @Test
    public void testSetFirstname() {

    }

    @Test
    public void testGetEmail() {

    }

    @Test
    public void testSetEmail() {

    }

    @Test
    public void testGetAddress() {

    }

    @Test
    public void testSetAddress() {

    }

    @Test
    public void testGetTel() {

    }

    @Test
    public void testSetTel() {

    }

    @Test
    public void testGetGender() {

    }

    @Test
    public void testSetGender() {

    }

    @Test
    public void testGetPermissions() {

    }

    @Test
    public void testSetPermissions() {

    }

    @Test
    public void testGetIban() {

    }

    @Test
    public void testSetIban() {

    }

    @Test
    public void testGetBic() {

    }

    @Test
    public void testSetBic() {

    }

    @Test
    public void testGetAccountHolder() {

    }

    @Test
    public void testSetAccountHolder() {

    }

    @Test
    public void testGetBankName() {

    }

    @Test
    public void testSetBankName() {

    }

    @Test
    public void testGetSuccessfullYearInCollege() {

    }

    @Test
    public void testSetSuccessfullYearInCollege() {

    }

    @Test
    public void testGetRegistrationDate() {

    }

    @Test
    public void testSetRegistrationDate() {

    }

    @Test
    public void testGetBirthDate() {

    }

    @Test
    public void testSetBirthDate() {

    }

    @Test
    public void testCryptPassword() {

    }

    @Test
    public void testCheckPassword() {

    }
}
