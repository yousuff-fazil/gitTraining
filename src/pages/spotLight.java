package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class spotLight {
	
public static boolean backSpotLight(AndroidDriver driver) {
	WebDriverWait wait = new WebDriverWait(driver,30);
	driver.findElementById("com.mindandmom.appstg:id/ivBack").click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Contractions']")));
	AndroidElement home = (AndroidElement) driver.findElementByXPath("//android.widget.TextView[@text='Contractions']");
	if(home.isDisplayed()) {
		return true;
	} else {
		return false;
	}
}

public static String pageTitle(AndroidDriver driver) {
	String title = driver.findElementById("com.mindandmom.appstg:id/tvRealPageTitle").getText();
	
	return title;
	
}

//
public static String pointerTitle(AndroidDriver driver) {
	String ptitle = driver.findElementById("com.mindandmom.appstg:id/tvWUG").getText();
	
	return ptitle;
	
}
public static String productName(AndroidDriver driver) {
	String name = driver.findElementById("com.mindandmom.appstg:id/tvRealSubTitle").getText();
	
	return name;
	
}


public static String blogTitle(AndroidDriver driver) {
	//
	String name = driver.findElementById("com.mindandmom.appstg:id/tvToolbarTitle").getText();
	
	return name;
}

public static void backToSpotLight( AndroidDriver driver) {
	driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
}




}
