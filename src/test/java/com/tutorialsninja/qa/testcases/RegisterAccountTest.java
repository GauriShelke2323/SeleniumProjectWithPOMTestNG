package com.tutorialsninja.qa.testcases;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;


public class RegisterAccountTest extends BaseClass{
	
	public WebDriver driver;
	RegisterPage registerPageObj;
	
	@BeforeMethod
	public void setup() {
		try {
			loadPropertiesFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver=initializebrowserOpenAppURL(prop.getProperty("Browser"));
		HomePage homepageobj=new HomePage(driver);
		homepageobj.clickOnMyAccount();
		registerPageObj=homepageobj.selectRegisterOption();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields(){
		
		registerPageObj.enterFirstname(dataprop.getProperty("FIRSTNAME"));
		registerPageObj.enterLastname(dataprop.getProperty("LASTNAME"));
		registerPageObj.enterEmail(Utilities.generateTimeStamp());
		registerPageObj.enterPhoneNumber(dataprop.getProperty("PHONENUMBER"));
		registerPageObj.enterPassword(prop.getProperty("password"));
		registerPageObj.enterConfirmPassword(prop.getProperty("password"));
		registerPageObj.selectCheckbox();
		registerPageObj.clickOnSubmit();
		/*driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(dataprop.getProperty("FIRSTNAME"));
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(dataprop.getProperty("LASTNAME"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateTimeStamp());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(dataprop.getProperty("PHONENUMBER"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();*/
		AccountSuccessPage accountPageObj= new AccountSuccessPage(driver);
		String actualsuccessmsg=accountPageObj.successMsgvalidation();
		Assert.assertEquals(actualsuccessmsg,dataprop.getProperty("ACC_SUCCESS_CREATED_MSG"));
		
	}

	@Test(priority=2)
	public void verifyRegisteringWithProvidingAllFields() {
	
		registerPageObj.enterFirstname(dataprop.getProperty("FIRSTNAME"));
		registerPageObj.enterLastname(dataprop.getProperty("LASTNAME"));
		registerPageObj.enterEmail(Utilities.generateTimeStamp());
		registerPageObj.enterPhoneNumber(dataprop.getProperty("PHONENUMBER"));
		registerPageObj.enterPassword(prop.getProperty("password"));
		registerPageObj.enterConfirmPassword(prop.getProperty("password"));
		registerPageObj.selectRadioButton();
		registerPageObj.selectCheckbox();
		registerPageObj.clickOnSubmit();
		/*driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(dataprop.getProperty("FIRSTNAME"));
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(dataprop.getProperty("LASTNAME"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateTimeStamp());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(dataprop.getProperty("PHONENUMBER"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();*/
		//String actualsuccessmsg=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		AccountSuccessPage accountPageObj= new AccountSuccessPage(driver);
		String actualsuccessmsg=accountPageObj.successMsgvalidation();
		Assert.assertEquals(actualsuccessmsg, dataprop.getProperty("ACC_SUCCESS_CREATED_MSG"));
	
	}
	
	@Test(priority=3)
	public void verifyRegisteringWithExistingEmailid() {
		
		registerPageObj.enterFirstname(dataprop.getProperty("FIRSTNAME"));
		registerPageObj.enterLastname(dataprop.getProperty("LASTNAME"));
		registerPageObj.enterEmail(prop.getProperty("validemail"));
		registerPageObj.enterPhoneNumber(dataprop.getProperty("PHONENUMBER"));
		registerPageObj.enterPassword(prop.getProperty("password"));
		registerPageObj.enterConfirmPassword(prop.getProperty("password"));
		registerPageObj.selectRadioButton();
		registerPageObj.selectCheckbox();
		registerPageObj.clickOnSubmit();
	/*	driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(dataprop.getProperty("FIRSTNAME"));
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(dataprop.getProperty("LASTNAME"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("apekshatest123@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(dataprop.getProperty("PHONENUMBER"));
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();*/
		String actualsuccessmsg=registerPageObj.existingEmailMsg();
		Assert.assertEquals(actualsuccessmsg, dataprop.getProperty("EMAIL_ALREADY_REGISTERED_MSG"));
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringWithoutFillingAnyDetails() {
		
		registerPageObj.clickOnSubmit();
		//driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String ActualWarningmsg=registerPageObj.privacyPolicyWarningMsgValidation();
		Assert.assertTrue(ActualWarningmsg.contains(dataprop.getProperty("PRIVACY_POLICY_MSG")),"Privacy policy warning message is not displayed");
		
		String FirstnameError=registerPageObj.firstNameWarningMsgValidation();
		Assert.assertTrue(FirstnameError.contains(dataprop.getProperty("FIRSTNAME_WARNING")), "Error message for firstname is not displayed");
		
		String LastnameError=registerPageObj.lastNameWarningMsgValidation();
		Assert.assertTrue(LastnameError.contains(dataprop.getProperty("LASTNAME_WARNING")), "Error message for lastname is not displayed");
		
		String EmailError=registerPageObj.emailWarningMsgValidation();
		Assert.assertTrue(EmailError.contains(dataprop.getProperty("EMAIL_WARNING")), "Error message for email is not displayed");
		
		String PhoneError=registerPageObj.phoneNumberWarningMsgValidation();
		Assert.assertTrue(PhoneError.contains(dataprop.getProperty("PHONENUMBER_WARNING")), "Error message for phone is not displayed");
		
		String PasswordError=registerPageObj.passwordWarningMsgValidation();
		Assert.assertTrue(PasswordError.contains(dataprop.getProperty("PASSWORD_WARNING")), "Error message for password is not displayed");
		
		//String ConfirmPassError=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		//Assert.assertTrue(EmailError.contains("E-Mail Address does not appear to be valid!"), "Error message for email is not displayed");
		
	}
}
