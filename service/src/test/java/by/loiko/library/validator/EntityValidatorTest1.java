package by.loiko.library.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

/***
 Author: Aliaksei Loika
 Date: 13.02.2018
 ***/
public class EntityValidatorTest1 {
    private EntityValidator entityValidator;

    @BeforeMethod
    public void setUp() throws Exception {
        entityValidator = new EntityValidator();

    }

    @DataProvider
    public Object[][] dataGoodIds() {
        return new Object[][]{
                {"1"}, {"101"}, {"3"}, {"768"}, {"21"}
        };
    }

    @DataProvider
    public Object[][] dataBadIds() {
        return new Object[][]{
                {"1m"}, {"0"}, {null}, {"ppp"}, {"1s1"}
        };
    }

    @DataProvider
    public Object[][] arrayWithTwoBad() {
        return new Object[][]{
                {new String[]{"-1", "101", "m", "4", "7", "11"}},
                {new String[]{"32", "1", "2", "0", "87", "2m"}}
        };
    }

    @DataProvider
    public Object[][] dataGoodDate() {
        return new Object[][]{
                {"1998-01-01"},
                {"2015-07-08"},
                {"2018-02-13"},
        };
    }

    @DataProvider
    public Object[][] dataBadDate() {
        return new Object[][]{
                {"31 January 2018"},
                {"01.01.2018"},
                {"31 JUN 2017"},
        };
    }

    @DataProvider
    public Object[][] dataParamsGood() {
        return new Object[][]{

        };
    }

    @Test(dataProvider = "dataGoodIds")
    public void testValidateIdSuccess(String data) throws Exception {
        boolean result = entityValidator.validateId(data);
        Assert.assertTrue(result);
    }

    @Test(dataProvider = "dataBadIds")
    public void testValidateIdFailure(String data) throws Exception {
        boolean result = entityValidator.validateId(data);
        Assert.assertFalse(result);
    }


    @Test(dataProvider = "dataGoodDate")
    public void testValidateDateSuccess(String data) throws Exception {
        boolean result = entityValidator.validateDate(data);
        Assert.assertTrue(result);
    }

    @Test(dataProvider = "dataBadDate")
    public void testValidateDateFailure(String data) throws Exception {
        boolean result = entityValidator.validateDate(data);
        Assert.assertFalse(result);
    }

    @Test
    public void testValidateBook() throws Exception {
    }

    @Test(dataProvider = "arrayWithTwoBad")
    public void testValidateArrayOfId(String[] data) throws Exception {
        ArrayList<Long> arrayIds = entityValidator.validateArrayOfId(data);
        int actual = arrayIds.size();
        int expected = 4;
        Assert.assertEquals(actual, expected);
    }

}