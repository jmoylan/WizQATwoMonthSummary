package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import core.DriverFactory;


public class TestBase {
	
private WebDriver driver;
	
	@Parameters("browser")
	@BeforeClass
	public void initDriver(String browser) {
		driver =  new DriverFactory().getDriver(browser);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeClass
	public void launchApp() {
		driver.get("https://www.camdencourthotel.com/");
	}
	
	@AfterClass
	public void quitBrowser() {
		
		driver.quit();
		
	}

}
