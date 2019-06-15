package logging;


import org.testng.Assert;
import base.Base;


/*This class provides soft assertions for Amazon Test*/
public class Verify extends Base {

    public static void verifyTrue(Boolean object, String message) {
        try {
            Assert.assertTrue(object);
            System.out.println("Verified TC as PASSED: " + message);

        } catch (AssertionError e) {
            System.out.println("Verification FAILED " + "Expected " + object + "As TRUE " + "But found " + object + " FALSE");
            System.out.println(e.getMessage());
        }
    }

    public static void verifyEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected);
            System.out.println("Verified TC: " + message);
        } catch (AssertionError e) {
            System.out.println("Verification failed : " + "Expected element is " + expected + " Actual Element displayed as " + actual);
            System.out.println(e.getMessage());
        }
    }

}
