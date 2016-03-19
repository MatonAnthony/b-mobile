package ucc;

import dal.DalBackendServices;
import dal.DalServices;
import dal.DalServicesImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anthony on 19/03/16.
 */
public class UserUcControllerImplTest {

    private UserUcControllerImpl userUcc;

    @Before
    public void setUp() throws Exception {
        DalServices dal = new DalServicesImpl();

    }

    @Test
    public void testLogin() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {

    }
}
