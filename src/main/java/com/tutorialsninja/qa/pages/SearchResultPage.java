package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
 WebDriver driver;
	
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement searchResultHeaderText;
	
	@FindBy(linkText="HP LP3065")
	private WebElement searchResult;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMatchWarning;
	
	public String searchResultHeaderTextValidation() {
		String checkSearchResultHeader=searchResultHeaderText.getText();
		return checkSearchResultHeader;
	}
	
	public boolean searchResultValidation() {
		boolean searchResultStatus=searchResult.isDisplayed();
		return searchResultStatus;
	}
	
	public String noProductMatchWarningMsgValidation() {
		String noProductMatch=noProductMatchWarning.getText();
		return noProductMatch;
	}
	
}
