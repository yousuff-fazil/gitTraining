package automation;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import mindnMomAPIs.basics;
import pages.spotLight;

public class SpotlightAutomtion extends base {
	
	static ExtentTest test;
	
	public static void waitforElement(String s, AndroidDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(s)));
		System.out.println("Testing");
		System.out.println("Testing2");
		System.out.println("Testing3");
	}
	
	public static void scrollAndClick(String visibleText, AndroidDriver driver) {
	     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").click();
	        }
	
	public static void scrollUpto(String visibleText, AndroidDriver driver) {
	     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))");
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
		test = reports.startTest("Mind&Mom_Android");
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + " Launched the Mind & Mom app");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@text='EXISTING USER']")));
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "Home screen for login appears sucsessfully");
		driver.findElementByXPath("//android.widget.Button[@text='EXISTING USER']").click();
		//com.mindandmom.app:id/etEmailPhone
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='Email']")));
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "successfully navigated to the login screen for existing user.");
		Thread.sleep(1000);
		driver.findElementById("com.mindandmom.appstg:id/etEmailPhone").sendKeys("mdyousuf.fazil@gmail.com");
		//driver.findElementByXPath("//android.widget.EditText[@text='Email']").sendKeys("mdyousuf.fazil@gmail.com");
		//com.mindandmom.app:id/etPassword
		driver.findElementById("com.mindandmom.appstg:id/etPassword").sendKeys("Test@123");
		driver.findElementByXPath("//android.widget.Button[@text='LOGIN']").click();
		reports.endTest(test);
		
		test = reports.startTest("Mind&Mom_SpotlightValidation");
		Thread.sleep(5000);
		driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button").click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Contractions']")));
		test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + "Logged In sucessfully for the existing user");
		
		int spotlightCount= basics.noOfSpotlight();
		System.out.println("no of spot " + spotlightCount);
		assertTrue(driver, spotlightCount>0 , "The User not has a valid spotlight to be displayed");
		
		if(spotlightCount>0) {
			String SpotLightLabel = driver.findElementById("com.mindandmom.appstg:id/tvSpotlightLabel").getText();
			assertEquals(driver, "Spotlight", SpotLightLabel,"The SpotLightLabel is not displayed in homescreen as expected");
			System.out.println("spot label " + SpotLightLabel);
			AndroidElement spotlightimg= driver.findElementById("com.mindandmom.appstg:id/rvList3");
			System.out.println("image done");
			assertTrue(driver, spotlightimg.isDisplayed(),"Spotlight Image is not displayed");
			System.out.println("befoe done");
			spotlightimg.click();
			System.out.println("after done");
			Thread.sleep(5000);
			
			assertTrue(driver, spotLight.backSpotLight(driver), "The back button is the spotlight is not working as expected");
			spotlightimg.click();
			Thread.sleep(5000);
			
			assertEquals(driver, spotLight.productName(driver), basics.spotLightName(), "The Product name in the spotlight is not displayed as expected");
			assertEquals(driver, spotLight.pageTitle(driver), basics.spotlightTitle(), "The page title in the spotlight is not displayed as expected");
			List pointHead=basics.pointHead();
			List pointContent= basics.pointContent();
			System.out.println("  button name" + basics.buttonName);
			scrollAndClick(basics.buttonName, driver);
			Thread.sleep(3000);
			
			assertEquals(driver, spotLight.blogTitle(driver),"Safe pregnancy blogs", "the blog page is not displayed successfully");
			
			spotLight.backToSpotLight(driver);
			
			Thread.sleep(3000);
			scrollUpto(basics.spotlightTitle(), driver);
			assertEquals(driver, spotLight.pageTitle(driver), basics.spotlightTitle(), "spotlight is navigated is not successfully from blog page");
			Thread.sleep(3000);
			
			assertEquals(driver, spotLight.pointerTitle(driver), basics.pointTitle, "Pointer Title is not loaded as per the backend ");
			
			List<AndroidElement> pointerTitle = driver.findElementsById("com.mindandmom.appstg:id/tvCoverageTitle");
			
			System.out.println(" length of list " + pointHead.size());
			
			System.out.println(" value of list " + pointHead.get(0));
			
			if(pointHead.size()== pointerTitle.size()) {
				assertTrue(driver,true,"the Length of the pointer head Loaded not matched the backend");
			}
			
			for(int i=0;i<pointerTitle.size();i++) {
				
				assertEquals(driver, pointerTitle.get(i).getText(), (String) pointHead.get(i), " The pointer heading is not loaded sucessfully");
				System.out.println(" values " + pointerTitle.get(i).getText());
			}
			//
			
			List<AndroidElement> pointerContent = driver.findElementsById("com.mindandmom.appstg:id/tvCoverageContent");
			
			for(int i=0;i<pointerContent.size();i++) {
				
				assertEquals(driver, pointerContent.get(i).getText(), (String) pointContent.get(i), " The pointer content is not loaded sucessfully");
				System.out.println(" contents " + pointerContent.get(i).getText());
			}
			
			
			List faqList= basics.faqList();
			scrollUpto(basics.faqList().get(faqList.size()-1), driver);
			List<AndroidElement> faqs = driver.findElementsById("com.mindandmom.appstg:id/tvFaqQuestion");
			
			assertEquals(driver, String.valueOf(faqList.size()), String.valueOf(faqs.size()), " the No of FAQ loaded is not matched the backend");
			
		
			for(int i=0;i<faqs.size();i++) {
				
				assertEquals(driver, faqs.get(i).getText(), (String) faqList.get(i), " The FAQ Title name is not loaded sucessfully as in Backend");
				System.out.println(" faqs values " + faqs.get(i).getText());
			}
			
		}
		 
		 
		reports.endTest(test);
		reports.flush();
		reports.close();
		
		driver.closeApp();
	}
	
	
	public static void assertEquals(AndroidDriver driver,String s1, String s2, String s3) throws IOException {
		if(s1.equals(s2)) {
			if(s3.contains("not")) {
			s3 = s3.replaceAll("not ", "");
			
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver)) + s3);
			}
		}
		
		else {
			
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver)) + s3);
		}
	}
	
	public static void assertLog(String s3) throws IOException {
		test.log(LogStatus.INFO, s3);
	}
	
	public static void assertTrue(AndroidDriver driver, boolean bool, String s3) throws IOException {
		
		if(bool) {
			if(s3.contains("not")) {
				s3 = s3.replaceAll("not ", "");
				
		test.log(LogStatus.PASS, s3);}}
		
		else {
		test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver)) + s3);}
	}

}
