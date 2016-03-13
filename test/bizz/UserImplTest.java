package bizz;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserImplTest {

    private UserImpl puppet;
    private UserImpl empty;

    @Before
    public void setUp() throws Exception {
        puppet = new UserImpl();
        puppet.setPseudo("puppet");
        puppet.setPassword("master");
        puppet.setName("name");
        puppet.setFirstname("firstname");
        puppet.setEmail("xxx@yyy.zzz");
        puppet.setAddress("5th Street");
        puppet.setTel("32478803948");
        puppet.setGender("M");
        puppet.setPermissions("STUDENT");
        puppet.setSuccessfullYearInCollege(2);

        puppet.setAccountHolder("Maton Anthony");
        puppet.setBankName("Goldman Sachs");
        puppet.setRegistrationDate(LocalDate.MAX);
        puppet.setBirthDate(LocalDate.MIN);
        empty = new UserImpl();

    }

    /*
     * Test return pseudo when there is no pseudo defined.
     */
    @Test(expected = NullPointerException.class)
    public void testGetPseudo() {
        empty.getPseudo();
    }

    /*
     * Test return pseudo
     */
    @Test
    public void testGetPseudo1() {
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
     * Test getPassword() when there is no password defined yet
     */
    @Test(expected = NullPointerException.class)
    public void testGetPassword1() {
        empty.getPassword();
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

    /*
     * Test getName() when the User doesn't have a name
     */
    @Test(expected = NullPointerException.class)
    public void testGetName() {
        empty.getName();
    }

    /*
     * Test getName() when the User is having a name
     */
    @Test
    public void testGetName1() {
        assertEquals(puppet.getName(), "name");
    }

    /*
     * Try to set the name at null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetName() {
        puppet.setName(null);
    }

    /*
     * Test setName() with a correct name
     */
    @Test
    public void testSetName1() {
        puppet.setName("joe");
        assertEquals(puppet.getName(), "joe");
    }


    /*
     * Test getFirstname() when firstname is not yet defined
     */
    @Test(expected = NullPointerException.class)
    public void testGetFirstname() {
        empty.getFirstname();
    }

    /*
     * Test getFirstname() when firstname is defined
     */
    @Test
    public void testGetFirstname1() {
        assertEquals(puppet.getFirstname(), "firstname");
    }

    /*
     * Test setFirstname with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetFirstname() {
        puppet.setFirstname(null);
    }

    /*
     * Test setFirstname under normal conditions
     */
    @Test
    public void testSetFirstname1() {
        puppet.setFirstname("rico");
        assertEquals(puppet.getFirstname(), "rico");
    }

    /*
     * Test getEmail when email is not yet defined
     */
    @Test(expected = NullPointerException.class)
    public void testGetEmail() {
        empty.getEmail();
    }

    /*
     * Test getEmail when email is defined
     */
    @Test
    public void testGetEmail1() {
        assertEquals(puppet.getEmail(), "xxx@yyy.zzz");
    }


    /*
     * Test setEmail with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetEmail() {
        puppet.setEmail(null);
    }

    /*
     * Test setEmail standard cases
     */
    @Test
    public void testSetEmail1() {
        puppet.setEmail("anthony@anthony.be");
        assertEquals(puppet.getEmail(), "anthony@anthony.be");
    }

    /*
     * Test getAddress when the address is null
     */
    @Test(expected = NullPointerException.class)
    public void testGetAddress() {
        empty.getAddress();
    }

    /*
     * Test getAddress when there is an address set
     */
    @Test
    public void testGetAddress1() {
        assertEquals(puppet.getAddress(), "5th Street");
    }

    /*
     * Test setAddress with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetAddress() {
        puppet.setAddress(null);
    }

    /*
     * Test setAddress with a valid address
     */
    @Test
    public void testSetAddress1() {
        puppet.setAddress("Test Street");
        assertEquals(puppet.getAddress(), "Test Street");
    }

    /*
     * Test getTel when tel is not defined yet
     */
    @Test(expected = NullPointerException.class)
    public void testGetTel() {
        empty.getTel();
    }

    /*
     * Test getTel when tel is defined
     */
    @Test
    public void testGetTel1() {
        assertEquals(puppet.getTel(), "32478803948");
    }

    /*
     * Test setTel with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetTel() {
        puppet.setTel(null);
    }

    /*
     * Test when gender is not defined yet
     */
    @Test(expected = NullPointerException.class)
    public void testGetGender() {
        empty.getGender();
    }

    /*
     * Test when gender is defined.
     */
    @Test
    public void testGetGender1() {
        assertEquals(puppet.getGender(), "M");
    }

    /*
     * Test setGender with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetGender() {
        puppet.setGender(null);
    }

    /*
     * Test setGender with a valid argument
     */
    @Test
    public void testSetGender1() {
        puppet.setGender("F");
        assertEquals(puppet.getGender(), "F");
    }

    /*
     * Test when permissions is undefined
     */
    @Test(expected = NullPointerException.class)
    public void testGetPermissions() {
        empty.getPermissions();
    }

    /*
     * Test when permissions is defined
     */
    @Test
    public void testGetPermissions1() {
        assertEquals(puppet.getPermissions(), "STUDENT");
    }

    /*
     * Test with null as argument
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPermissions() {
        puppet.setPermissions(null);
    }

    /*
     * Test with a correct argument
     */
    @Test
    public void testSetPermissions1() {
        puppet.setPermissions("TEACHER");
        assertEquals(puppet.getPermissions(), "TEACHER");
    }

    // Todo Ajouter tout les tests relatif à l'IBAN et au BIC
    /*
     * Test when iban is not defined yet
     */
    @Test(expected = NullPointerException.class)
    public void testGetIban() {
        empty.getIban();
    }

    /*
     * Test setIban with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetIban() {
        puppet.setIban(null);
    }

    /*
     * Test when bic is not defined yet
     */
    @Test(expected = NullPointerException.class)
    public void testGetBic() {
        empty.getBic();
    }

    /*
     * Test with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBic() {
        puppet.setBic(null);
    }

    /*
     * Test getAccountHolder() when holder is not defined yet
     */
    @Test(expected = NullPointerException.class)
    public void testGetAccountHolder() {
        empty.getAccountHolder();
    }

    /*
     * Test getAccountHolder() when holder is defined
     */
    @Test
    public void testGetAccountHolder1() {
        assertEquals(puppet.getAccountHolder(), "Maton Anthony");
    }

    /*
     * Test setAccountHolder with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetAccountHolder() {
        puppet.setAccountHolder(null);
    }

    /*
     * Test setAccountHolder with a valid string
     */
    @Test
    public void testSetAccountHolder1() {
        puppet.setAccountHolder("Tyrion Lannister");
        assertEquals(puppet.getAccountHolder(), "Tyrion Lannister");
    }

    /*
     * Test getBankName when bankName is undefined yet
     */
    @Test(expected = NullPointerException.class)
    public void testGetBankName() {
        empty.getBankName();
    }

    /*
     * Test getBankName when bankName is defined
     */
    @Test
    public void testGetBankName1() {
        assertEquals(puppet.getBankName(), "Goldman Sachs");
    }

    /*
     * Test setBankName with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBankName() {
        puppet.setBankName(null);
    }

    /*
     * Test with a valid bankname
     */
    @Test
    public void testSetBankName1() {
        puppet.setBankName("Ethias");
        assertEquals(puppet.getBankName(), "Ethias");
    }

    @Test
    public void testGetSuccessfullYearInCollege() {
        assertEquals(puppet.getSuccessfullYearInCollege(), 2, 0);
    }

    /*
     * Test while trying to set a negative number of successfull year
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetSuccessfullYearInCollege() {
        puppet.setSuccessfullYearInCollege(-1);
    }

    /*
     * Test while trying to set a negative number of successfull year
     */
    @Test
    public void testSetSuccessfullYearInCollege1() {
        puppet.setSuccessfullYearInCollege(1);
        assertEquals(puppet.getSuccessfullYearInCollege(), 1, 0);
    }

    /*
     * Test getRegistrationDate when registrationDate is undefined
     */
    @Test(expected = NullPointerException.class)
    public void testGetRegistrationDate() {
        empty.getRegistrationDate();
    }

    /*
     * Test getRegistrationDate when registrationDate is defined
     */
    @Test
    public void testGetRegistrationDate1() {
        assertEquals(puppet.getRegistrationDate(), LocalDate.MAX);
    }

    /*
     * Test setRegistrationDate with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetRegistrationDate() {
        puppet.setRegistrationDate(null);
    }

    /*
     * Test setRegistrationDate with a correct value
     */
    @Test
    public void testSetRegistrationDate1() {
        puppet.setRegistrationDate(LocalDate.MIN);
        assertEquals(puppet.getRegistrationDate(), LocalDate.MIN);
    }

    // Todo Tester avec une date dans le future

    /*
     * Test getBirthDate when it's not defined
     */
    @Test(expected = NullPointerException.class)
    public void testGetBirthDate() {
        empty.getBirthDate();
    }

    /*
     * Test getBirthDate when it's defined
     */
    @Test
    public void testGetBirthDate1() {
        assertEquals(puppet.getBirthDate(), LocalDate.MIN);
    }

    /*
     * Test setBirthDate with null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBirthDate() {
        puppet.setBirthDate(null);
    }

    /*
     * Test with a valid date
     */
    @Test
    public void testSetBirthDate1() {
        puppet.setBirthDate(LocalDate.MAX);
    }

    // TODO Tester avec une date dans le futur

    /*
     * Test cryptPassword when password is null
     */
    @Test(expected = NullPointerException.class)
    public void testCryptPassword() {
        empty.cryptPassword();
    }

    /*
     * Test cryptPassword when password is correct
     */
    @Test
    public void testCryptPassword1() {
        puppet.cryptPassword();
        assertTrue(puppet.checkPassword("master"));
    }

    /*
     * Test checkPassword when password is null
     */
    @Test(expected = NullPointerException.class)
    public void testCheckPassword() {
        empty.checkPassword("master");
    }

    /*
     * Test checkPassword when parameter is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckPassword2() {
        puppet.checkPassword(null);
    }

    /*
     * Test checkPassword when parameter is incorrect
     */
    @Test
    public void testCheckPassword3() {
        puppet.cryptPassword();
        assertFalse(puppet.checkPassword("bad password"));
    }

    /*
     * Test checkPassword when parameter is correct
     */
    @Test
    public void testCheckPassword4() {
        puppet.cryptPassword();
        assertTrue(puppet.checkPassword("master"));
    }
}
