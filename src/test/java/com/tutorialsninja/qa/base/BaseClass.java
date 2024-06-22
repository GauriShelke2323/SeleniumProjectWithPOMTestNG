package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	public void loadPropertiesFile() throws IOException {
		
		//filepath config.properties
		prop =new Properties();
		File propFile = new File(System.getProperty("user.dir")+"/src/main/java/com/tutorialsninja/qa/config/config.properties");
		try {
			//input stream to load the file
			 FileInputStream fis = new FileInputStream(propFile);
				prop.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		//for testdata.properties file
		dataprop =new Properties();
		File dataPropFile= new File(System.getProperty("user.dir")+"/src/main/java/com/tutorialsninja/qa/testdata/testdata.properties");
		
		try {
			FileInputStream fis2 = new FileInputStream(dataPropFile);
			dataprop.load(fis2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
		//load file
		
	}
	
	public WebDriver initializebrowserOpenAppURL(String BrowserName) {
		
		WebDriverManager.chromedriver().setup();
		if(BrowserName.equalsIgnoreCase("Chrome")) {
			driver=new ChromeDriver();
		}else if(BrowserName.equalsIgnoreCase("Firefox")) {
		driver=new FirefoxDriver();
		}else if(BrowserName.equalsIgnoreCase("Edge")){
			driver=new EdgeDriver();
		}
		
		//WebDriverManager.chromedriver().setup();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		driver.get(prop.getProperty("URL"));
		return driver;
	}
}
