package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
private WebDriver driver = null;
	
	public WebDriver getDriver(String browser) {	
		switch(browser.toLowerCase()) {
			case "chrome"  : 
				driver = new ChromeDriver();
				break;
			case "firefox" :
				driver = new FirefoxDriver();
			default : 
				System.out.println("Incorrect browser name provided "+ browser);
				System.out.println("Hence running in Chrome ");
				driver = new ChromeDriver();
				break;
		}
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);  // One time config done for the whole project
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // One time config
		return driver;	
	}
	
	public void quitDriver() {
		if(driver!=null) {
			driver.quit();
		}
	}
}