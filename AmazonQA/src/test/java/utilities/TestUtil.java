package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import base.Base;

/**This class provides test support methods**/
public class TestUtil extends Base {

    /*This method takes screen shots on test failure*/
    public static String screenShotName;
    public static String screenShotpath;
    public static void takeScreenShot(String methodName) throws IOException
    {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        screenShotName = d.toString().replace(":", "_").replace(" ", "_") + "_" + methodName + ".jpg";
        screenShotpath = System.getProperty("user.dir") + "\\src\\test\\resources\\logs\\ScreenShots\\" + screenShotName;
        FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\src\\test\\resources\\logs\\ScreenShots\\" + screenShotName));
    }
}



