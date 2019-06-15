package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class Base {
    public static WebDriver driver;
    public Properties prop = new Properties();
    public FileInputStream fis;

    @BeforeMethod
    /*Initializing WebDriver by reading property file*/
    public WebDriver setUp() throws IOException {
        fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\project.properties");
        prop.load(fis);

        if (prop.getProperty("browser").contains("chrome")) {
            //Initializing WebDriver for Chrome Browser for Amazon Test
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").contains("FireFox")) {
            System.out.println("Setting FireFox driver");
        }
        //Adding implicit wait of 15 seconds
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Maximizing browser window
        driver.manage().window().maximize();
        //Getting Amazon web URL from property file
        driver.get(prop.getProperty("url"));
        return driver;
    }

    @AfterMethod
    /*To closing all the open windows after Test.*/
    public void tearDown() throws Exception {
        if (driver != null) {
            System.out.println("Closing all the opened windows");
            Thread.sleep(2000);
            driver.quit();
            driver = null;
        }
    }


}
