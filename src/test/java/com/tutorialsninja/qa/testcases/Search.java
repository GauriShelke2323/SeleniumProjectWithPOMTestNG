package com.tutorialsninja.qa.testcases;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchResultPage;

public class Search extends BaseClass {
	
	WebDriver driver;
	@BeforeMethod
	public void setup() throws IOException {
		/*loadPropertiesFile();
		driver=initializebrowserOpenAppURL(prop.getProperty("Browser"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();*/
		try {
			loadPropertiesFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver=initializebrowserOpenAppURL(prop.getProperty("Browser"));
		HomePage homepageobj=new HomePage(driver);
		homepageobj.clickOnMyAccount();
		homepageobj.selectLoginOption();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifySearchwithvalidProduct() {
		HomePage homepageobj=new HomePage(driver);
		homepageobj.enterSearchText(dataprop.getProperty("VALIDPRODUCT"));
		homepageobj.clickSearchBtn();
		
		//driver.findElement(By.xpath("//input[@name='search']")).sendKeys(dataprop.getProperty("VALIDPRODUCT"));
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		SearchResultPage searchResultObj= new SearchResultPage(driver);
		String searchheader=searchResultObj.searchResultHeaderTextValidation();
		Assert.assertEquals(searchheader,"Search - HP");
		
		Boolean searchresult=searchResultObj.searchResultValidation();
		Assert.assertTrue(searchresult);
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		HomePage homepageobj=new HomePage(driver);
		homepageobj.enterSearchText(dataprop.getProperty("INVALIDPRODUCT"));
		homepageobj.clickSearchBtn();
		
		//driver.findElement(By.xpath("//input[@name='search']")).sendKeys(dataprop.getProperty("INVALIDPRODUCT"));
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		SearchResultPage searchResultPageObj=new SearchResultPage(driver);
		String SearchMessage=searchResultPageObj.noProductMatchWarningMsgValidation();
		Assert.assertEquals(SearchMessage, dataprop.getProperty("NO_MATCH_PRODUCT_WARNING"));	
	}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct() {
		HomePage homepageobj=new HomePage(driver);
		homepageobj.clickSearchBtn();
		//driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		SearchResultPage searchResultPageObj=new SearchResultPage(driver);
		String SearchMessage=searchResultPageObj.noProductMatchWarningMsgValidation();
		Assert.assertEquals(SearchMessage, dataprop.getProperty("NO_MATCH_PRODUCT_WARNING"));	
	}
	

}
