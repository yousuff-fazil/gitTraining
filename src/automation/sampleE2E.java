package automation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class sampleE2E extends base {
	
	public static void waitforElement(String s, AndroidDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(s)));
	}
	
	public static void scrollAndClick(String visibleText, AndroidDriver driver) {
	     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").click();
	        }
	    
	
	public static String capture(AndroidDriver driver) throws IOException {
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("C:\\Users\\smdyo\\eclipse-workspace\\mindAndMom\\report\\image" + System.currentTimeMillis()+".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
		
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = capabilitites();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		ExtentReports reports = new ExtentReports("C:\\Users\\smdyo\\eclipse-workspace\\mindAndMom\\report\\extentreport.html", true);
		ExtentTest test = reports.startTest("Mind&Mom_Android");
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + " Launched the Mind & Mom app");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='EXISTING USER']")));
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "Home screen for login appears sucsessfully");
		driver.findElementByXPath("//android.widget.Button[@text='EXISTING USER']").click();
		//com.mindandmom.app:id/etEmailPhone
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='Email']")));
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "successfully navigated to the login screen for existing user.");
		driver.findElementById("com.mindandmom.app:id/etEmailPhone").sendKeys("mdyousuf.fazil@gmail.com");
		//driver.findElementByXPath("//android.widget.EditText[@text='Email']").sendKeys("mdyousuf.fazil@gmail.com");
		//com.mindandmom.app:id/etPassword
		driver.findElementById("com.mindandmom.app:id/etPassword").sendKeys("Test@123");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		reports.endTest(test);
		
		test = reports.startTest("Mind&Mom_Contraction");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Contractions']")));
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "Logged In sucessfully foe the existing user");
		driver.findElementByXPath("//android.widget.TextView[@text='Contractions']").click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.mindandmom.app:id/ivInfoContraction")));
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + " clicked and opened the Contraction feature");
		driver.findElementById("com.mindandmom.app:id/ivInfoContraction").click();
		
		waitforElement("android:id/message", driver);
		String s1 = driver.findElementById("android:id/message").getText();
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "contracion message " + s1);
		System.out.println(s1+ " \n" + s1);
		
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageView[2]")));
		test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver)) + " closed the contraction info");
		AndroidElement contraction = driver.findElementByXPath("//android.widget.ImageView[1]");
		contraction.click();
		Thread.sleep(10000);
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "Logged the contraction for the 10 seconds");
		driver.findElementByXPath("//android.widget.Button[@text='DONE']").click();
		
		
		driver.findElementById("com.mindandmom.app:id/ivResetContraction").click();
		waitforElement("android:id/message", driver);
		String s2 = driver.findElementById("android:id/message").getText();
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + " reset contraction text" + s2);
		System.out.println(s2);
		
		
		driver.findElementByXPath("//android.widget.Button[@text='NO']").click();
		
		driver.findElementById("com.mindandmom.app:id/ivResetContraction").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='YES']")));
		driver.findElementByXPath("//android.widget.Button[@text='YES']").click();
		
		Thread.sleep(2000);
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "sucessfully reseted the contraction");
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		
		reports.endTest(test);
		
		test = reports.startTest("Mind&Mom_Kicks");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Kicks']")));
		
		driver.findElementByXPath("//android.widget.TextView[@text='Kicks']").click();
		
		waitforElement("com.mindandmom.app:id/tvKick", driver);
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + " Clicked and Opened the kicks feature");
		AndroidElement kicks = driver.findElementById("com.mindandmom.app:id/tvKick");
		kicks.click();
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + " clicked on the kick");
		
		int kick = 4;
		
//		for(int i =0 ;i<kick;i++) {
//			kicks.click();
//	}
		
		driver.findElementById("com.mindandmom.app:id/btnReset").click();
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "reseted the kick");
		kicks.click();
		driver.findElementById("com.mindandmom.app:id/btnSave").click();
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "saved the kick");
		
		
//		for(int i =0 ;i<kick;i++) {
//			kicks.click();
//	}
		
		driver.findElementById("com.mindandmom.app:id/ivMore").click();
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "deleted the saved kick");
		 
		Thread.sleep(3000);
		 
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		
		waitforElement("com.mindandmom.app:id/ivSettings",driver);
		driver.findElementById("com.mindandmom.app:id/ivSettings").click();
		scrollAndClick("Logout", driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='YES']")));
		driver.findElementByXPath("//android.widget.Button[@text='YES']").click();
		Thread.sleep(2000);
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "Logged out sucessfully for the current user");
		 
		 
		reports.endTest(test);
		reports.flush();
		reports.close();
		
		driver.closeApp();
	}

}
