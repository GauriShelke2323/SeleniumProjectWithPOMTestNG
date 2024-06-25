package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	 WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Objects
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement Login;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement warningErrorEmailPasswordNotMatching;
	
	//Actions /Methods
	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void clickLogin() {
		Login.click();
	}
	
	public String retrieveEmailPasswordNotmatchingText() {
		String warningText = warningErrorEmailPasswordNotMatching.getText();
		return warningText;
	}
}
