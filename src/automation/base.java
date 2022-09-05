package automation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class base {

	public static AndroidDriver<AndroidElement> capabilitites() throws MalformedURLException {
		// TODO Auto-generated method stub
		

		File appDir = new File("src");
		
		File app = new File(appDir, "MnMapp.apk");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "RZ8T10YR1LN");
		// //fazil_emulator
		cap.setCapability(MobileCapabilityType.UDID, "RZ8T10YR1LN");
		//ffbbecfb //emulator-5554 // 
		//cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		// /
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability("appPackage", "com.mindandmom.appstg");
		cap.setCapability("appActivity", "com.mindandmom.app.views.activities.MainActivity");
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		return driver;
	
}
}