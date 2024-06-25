package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReportsClass;
import com.tutorialsninja.qa.utils.Utilities;

public class ProjectListeners implements ITestListener{
	//ITestListener is default interface in TestNg 
		//change TestNG dependency scope to compile from test in POM.xml file 
		
		//override methods of ITestListener in ProjectListener class by going to source in menu and select 
	//override/implement methods
	ExtentReports extentReport;
	ExtentTest extentTest;
	public WebDriver driver;

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Project started....");
		extentReport =new ExtentReports();
		try {
			extentReport = ExtentReportsClass.generateExtentReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testName=result.getName();
		extentTest=extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" Started executing testcasews....");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.INFO, testName+" Successfully executed....");
		}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName();
		
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		//to add screenshot in extentREprot
		String destinationScreenshotPath=Utilities.captureScreenshot(driver, testName);
		System.out.println( "-------------------" + destinationScreenshotPath);
		extentTest = extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+"got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped....");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
