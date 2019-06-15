package listeners;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;




import base.Base;
import utilities.TestUtil;




public class Listeners extends Base implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		try{
			System.out.println("Reading property file");
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\project.properties");
			prop.load(fis);
		}catch (IOException e){
		}

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test PASSED :"+result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test FAILED :"+result.getName());
		System.out.println("Capturing Screenshot on failure");
		try {
			TestUtil.takeScreenShot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
