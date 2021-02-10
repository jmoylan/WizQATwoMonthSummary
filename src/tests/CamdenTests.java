package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CamdenHome;

public class CamdenTests extends TestBase{
	
	CamdenHome home = null;
	
	@Test
	public void Verify_HomePage() throws Exception {
		
		home = new CamdenHome(getDriver());
		Assert.assertEquals(home.verifyCamdenHome(), true, "Unable to reach Camden Court Hotels");
		Assert.assertEquals(home.verifyLanguage(), "EN", "Default language incorrect");
		//home.verifyLinks();
		home.datePicker();
		
	}
	
	
	/*@Test
	public void Verify_Links() {
		
	}
	
	@Test
	public void bookRoom() throws Exception {
		
		
	}*/
	

	
	

}
