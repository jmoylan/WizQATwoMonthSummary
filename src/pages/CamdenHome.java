package pages;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
;

public class CamdenHome extends BasePage{
	
	
	
	@FindBy(xpath = ".//div[@class = 'popup_container]")
	private WebElement popup; //can never find this popup
	
	@FindBy(how = How.CLASS_NAME, using =  "icon ion-close-round")
	private WebElement close;
	
	@FindBy(how = How.ID, using = "eighty_close_welcome_widget")
	private WebElement welcome;
	
	@FindBy(xpath = ".//a[contains(@href, *)]")
	private WebElement links;
	
	@FindBy(xpath = ".//button[@class= 'btn btn-top btn-success dropdown-toggle sb-languages-btn']")
	private WebElement language;
	
	@FindBy(xpath = ".//span[contains(text(),'Book a Room')]")
	private WebElement bookRoom;
	
	@FindBy(xpath = ".//input[@id = 'sb-be-form-arrival']")
	private WebElement inDate;
	
	@FindBy(xpath = ".//input[@id = 'sb-be-form-arrival']")
	private WebElement outDate;
	
	@FindBy(xpath = ".//a[@data-handler = 'next']")
	private WebElement next;
	
	@FindBy(xpath = ".//span[@class = \"ui-datepicker-month\"]//text()")
	private WebElement month;
	
	@FindBy(xpath = ".//input[@name = 'Book_Now']")
	private WebElement bookNow;
	
	private WebDriver driver = null;
	
	public CamdenHome(WebDriver driver) {
		
		super(driver); //access super class constructor of BasePage
		this.driver = driver;
		
	}
	
	public boolean verifyCamdenHome () {
		
		/*Thread.sleep(3000); none of this works
		WebElement iFrame = driver.findElement(By.xpath(".//div[@class = 'popup_container']"));
		driver.switchTo().frame(iFrame);*/
		
		return bookRoom.isDisplayed();
	}
	
	public String verifyLanguage() {
		
		return language.getText();
	}
	
	public void verifyLinks() {
		
		
		//source: https://www.browserstack.com/guide/how-to-find-broken-links-in-selenium
		//revise better design for this method
		
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		//iterator to move through all links
		Iterator<WebElement> it = links.iterator();

		while(it.hasNext()){

		url = it.next().getAttribute("href");

		System.out.println(url);

		if(url == null || url.isEmpty()){
		System.out.println("URL is either not configured for anchor tag or it is empty");
		continue;
		}
		//If the URL belongs to the main domain, continue. If it belongs to a third party domain, skip the steps after this.
		if(!url.startsWith("https://www.camdencourthotel.com/")){
		System.out.println("URL belongs to another domain, skipping it.");
		continue;
		}

		try {
		huc = (HttpURLConnection)(new URL(url).openConnection());
		//set Request type as “HEAD” instead of “GET”, only headers will be returned
		huc.setRequestMethod("HEAD");
		// connection to the URL is established and the HTTP request is sent
		huc.connect();
		//Use the getResponseCode() method to get the HTTP response code for the previously sent HTTP request
		respCode = huc.getResponseCode();
		//validate links
		if(respCode >= 400){
		System.out.println(url+" is a broken link");
		}
		else{
		System.out.println(url+" is a valid link");
		}

		} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		
		
	}
	
	public void datePicker() throws Exception {
		
		//desired checkin and checkout dates
		String dateA = "2021-03-29";
		String dateB = "2021-04-02";
		LocalDate depDate = LocalDate.parse(dateA, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate retDate= LocalDate.parse(dateB, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String depMonth = depDate.getMonth().name().toLowerCase();
		String depDay = String.valueOf(depDate.getDayOfMonth());
		
		String retMonth = retDate.getMonth().name().toLowerCase();
		String retDay = String.valueOf(retDate.getDayOfMonth());
		
		By depDayCell = By.xpath(".//a[contains(text()," + depDay + ")]");
		By retDayCell = By.xpath(".//a[contains(text()," + retDay + ")]");
		
		Thread.sleep(3000); //this seems to be the only way to consistently perform action
		
		//WebElement shadowRootElement = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot", welcome);
		//shadowRootElement.findElement(By.className("icon ion-close-round")).click();
		
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//this will fail because closed shadow DOM is null
		//wait.until(ExpectedConditions.visibilityOf(shadowRootElement)); 
		
		Actions click = new Actions(driver);
		Actions dismissPopup = click.moveToElement(welcome).click(); //actually this is flakey
		dismissPopup.perform();
		
		Thread.sleep(2000);
		/*bookRoom.click();
		Thread.sleep(2000);
		inDate.click();
		Thread.sleep(2000);*/
		
		/*while(!depMonth.equals(actualMonth)) {
			
			driver.findElement(next).click();
			actualMonth = month.getText().split(" ")[0].toLowerCase();
			Thread.sleep(1000);
		}
		
		driver.findElement(depDayCell).click();
		Thread.sleep(1000);
		
		while(!retMonth.equals(actualMonth)) {
			
			driver.findElement(next).click();
			actualMonth = month.getText().split(" ")[0].toLowerCase();
			Thread.sleep(1000);
		}
		
		driver.findElement(retDayCell).click();*/
		

	}
	

}
