package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void testCheckObjects() {
        String string = "string";
        Utils.checkObjects(string, String.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckObjects1() {
        Integer number = new Integer(1);
        Utils.checkObjects(number, String.class);
    }
}
