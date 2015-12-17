package testng_package;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	WebDriver driver=null;
	String filePath = "./screenshots/";
    public void onTestFailure(ITestResult result) {
    	System.out.println("***** FailedTest: "+result.getName()+" test has failed *****");
    	 long time = result.getEndMillis() - result.getStartMillis();
	    String time_string = Long.toString(time);
	    
    	String methodName=result.getName().toString().trim()+" "+ time_string;
    	takeScreenShot(methodName);
    }
    
    public void takeScreenShot(String methodName) {
    	driver=Base.getDriver();
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
				FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
				System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	public void onFinish(ITestContext context) {}
  
    public void onTestStart(ITestResult result) {   }
  
    public void onTestSuccess(ITestResult result) {   }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }
}  