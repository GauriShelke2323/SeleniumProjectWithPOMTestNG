package com.tutorialsninja.qa.testcases;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;


public class Login extends BaseClass{
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUP() {
		try {
			loadPropertiesFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//browser from properties file
		driver=initializebrowserOpenAppURL(prop.getProperty("Browser"));
		HomePage homepageobj=new HomePage(driver);
		homepageobj.clickOnMyAccount();
		homepageobj.selectLoginOption();
	}
	
	@Test(priority=1,dataProvider="readTestDataFromExcelSheet")
	public void verifyWithValidCredentials(String email, String password) {
		
		LoginPage loginPageObj=new LoginPage(driver);
		loginPageObj.enterEmail(email);
		loginPageObj.enterPassword(password);
		loginPageObj.clickLogin();
		AccountPage accPageObj=new AccountPage(driver);
		Assert.assertTrue(accPageObj.getDisplayStatusOfEditInformation());
		//driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
		//driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(password);
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		//WebElement text=driver.findElement(By.linkText("Edit your account information"));
			
	}
	
	@DataProvider
	public Object[][] readTestDataFromExcelSheet(){
		Object[][] data = null;
		try {
			data = Utilities.getTestDataFromExcelSheet("Login");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		LoginPage loginPageObj=new LoginPage(driver);
		loginPageObj.enterEmail(Utilities.generateTimeStamp());
		loginPageObj.enterPassword(dataprop.getProperty("INVALIDPASSWORD"));
		loginPageObj.clickLogin();
		//driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateTimeStamp());
		//driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(dataprop.getProperty("INVALIDPASSWORD"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		String Actualerrormsg=loginPageObj.retrieveEmailPasswordNotmatchingText();
		String Expectederrormsg= "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(Actualerrormsg.contains(Expectederrormsg),"Expected msg is not displayed");
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		LoginPage loginPageObj=new LoginPage(driver);
		loginPageObj.enterEmail(Utilities.generateTimeStamp());
		loginPageObj.enterPassword(prop.getProperty("password"));
		loginPageObj.clickLogin();
		String Actualerrormsg=loginPageObj.retrieveEmailPasswordNotmatchingText();
		String Expectederrormsg=dataprop.getProperty("EMAIL_PASSWORD_NO_MATCH_ERROR_MSG");
		Assert.assertTrue(Actualerrormsg.contains(Expectederrormsg),"Expected msg is not displayed");
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		LoginPage loginPageObj=new LoginPage(driver);
		loginPageObj.enterEmail(prop.getProperty("validemail"));
		loginPageObj.enterPassword(dataprop.getProperty("INVALIDPASSWORD"));
		loginPageObj.clickLogin();
	   //driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("apekshatest123@gmail.com");
		//driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(dataprop.getProperty("INVALIDPASSWORD"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String Actualerrormsg=loginPageObj.retrieveEmailPasswordNotmatchingText();
		String Expectederrormsg=dataprop.getProperty("EMAIL_PASSWORD_NO_MATCH_ERROR_MSG");
		Assert.assertTrue(Actualerrormsg.contains(Expectederrormsg),"Expected msg is not displayed");
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		LoginPage loginPageObj=new LoginPage(driver);
		loginPageObj.clickLogin();
		String Actualerrormsg=loginPageObj.retrieveEmailPasswordNotmatchingText();
		String Expectederrormsg= dataprop.getProperty("EMAIL_PASSWORD_NO_MATCH_ERROR_MSG");
		Assert.assertTrue(Actualerrormsg.contains(Expectederrormsg),"Expected msg is not displayed");
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
}
