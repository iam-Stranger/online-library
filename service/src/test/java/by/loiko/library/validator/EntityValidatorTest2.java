package by.loiko.library.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/***
 Author: Aliaksei Loika
 Date: 13.02.2018
 ***/
public class EntityValidatorTest2 {
    private EntityValidator entityValidator;

    private static final Map<String, String> goodParameters;
    static
    {
        goodParameters = new HashMap<String, String>();
        goodParameters.put("id", "1");
        goodParameters.put("Publish_year", "1982");
        goodParameters.put("date_from", "2018-01-20");
        goodParameters.put("firstname", "Aleksei");
        goodParameters.put("email", "neverminsk@gmail.com");
        goodParameters.put("password", "Hello_baby");

    }

    private static final Map<String, String> badParameters;
    static
    {
        badParameters = new HashMap<String, String>();
        badParameters.put("id", "0");
        badParameters.put("Publish_year", "1980-ые");
        badParameters.put("date_from", "15 января");
        badParameters.put("firstname", "Рухамба маба роу");
        badParameters.put("email", "neverminsk@gmail.com");
        badParameters.put("password", "Пароль");

    }

    @BeforeMethod
    public void setUp() throws Exception {
        entityValidator = new EntityValidator();

    }


    @Test
    public void testValidateParamsSuccess() throws Exception {
        Map<String, String> errors = entityValidator.validateParams(goodParameters);
        boolean result = false;
        if (errors.isEmpty()) {
            result = true;
        }
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateParamsFailure() throws Exception {
        Map<String, String> errors = entityValidator.validateParams(badParameters);
        int expected = errors.size();
        int actual = 5;
        Assert.assertEquals(actual, expected);
    }
}