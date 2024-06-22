package com.tutorialsninja.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	//objects location
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement MyAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement Login;
	
	@FindBy(linkText="Register")
	private WebElement Registerbtn;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchTextBox;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchBtn;
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);//objects initialization
	}
	
	//Actions
	public void clickOnMyAccount() {
		MyAccountDropMenu.click();
	}
	
	public void selectLoginOption() {
		Login.click();
	}
	
	public void selectRegisterOption() {
		Registerbtn.click();
	}

	public void enterSearchText(String searchitem) {
		searchTextBox.sendKeys(searchitem);
	}
	
	public void clickSearchBtn() {
		searchBtn.click();
	}
	
	
}
