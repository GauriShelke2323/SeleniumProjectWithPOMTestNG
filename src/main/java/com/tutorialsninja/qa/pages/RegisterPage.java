package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement firstnameField;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement lastnameField;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement phoneNumberField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement newPasswordField;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath="//input[@type='checkbox']")
	private WebElement privacyPolicyCheckbox;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsLetterradiobtn;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement submitBtn;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailAlreadyregisteredMsg;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarningMsg;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMsg;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMsg;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarningMsg;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement phoneNumberWarningMsg;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningMsg;

	public void enterFirstname(String firstname) {
		firstnameField.sendKeys(firstname);
	}
	
	public void enterLastname(String lastname) {
		lastnameField.sendKeys(lastname);
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void enterPhoneNumber(String phone) {
		phoneNumberField.sendKeys(phone);	
	}

	public void enterPassword(String password) {
		newPasswordField.sendKeys(password);	
	}
	
	public void enterConfirmPassword(String Confirmpassword) {
		confirmPasswordField.sendKeys(Confirmpassword);	
	}
	
	public void selectCheckbox() {
		privacyPolicyCheckbox.click();
	}
	
	public void selectRadioButton() {
		newsLetterradiobtn.click();
	}
	public void clickOnSubmit() {
		submitBtn.click();
	}
	
	public String existingEmailMsg() {
		String emailexistingMsg=emailAlreadyregisteredMsg.getText();
		return emailexistingMsg;
	}
	
	public String privacyPolicyWarningMsgValidation() {
		String privacyPolicyMsg=privacyPolicyWarningMsg.getText();
		return privacyPolicyMsg;
	}
	
	public String firstNameWarningMsgValidation() {
		String firstNameErrMsg=firstNameWarningMsg.getText();
		return firstNameErrMsg;
	}
	
	public String lastNameWarningMsgValidation() {
		String lastNameErrMsg=lastNameWarningMsg.getText();
		return lastNameErrMsg;
	}
	
	public String emailWarningMsgValidation() {
		String emailErrMsg=emailWarningMsg.getText();
		return emailErrMsg;
	}
	
	public String phoneNumberWarningMsgValidation() {
		String phoneNumberErrMsg=phoneNumberWarningMsg.getText();
		return phoneNumberErrMsg;
	}
	
	public String passwordWarningMsgValidation() {
		String passwordErrMsg=passwordWarningMsg.getText();
		return passwordErrMsg;
	}
	
	
}
