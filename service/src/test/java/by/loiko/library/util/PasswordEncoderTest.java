package by.loiko.library.util;


import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordEncoderTest {
    private static final String TEST_PASSWORD = "admin";
    private static final String EXPECTED_HASHED_PASSWORD = "be68f5702b9b666a69697e78f6dbef23";

    @Test
    public void encodePasswordTest() throws Exception {
        String hashedPassword = PasswordEncoder.encodePassword(TEST_PASSWORD);
        Assert.assertEquals(EXPECTED_HASHED_PASSWORD, hashedPassword);
    }
}