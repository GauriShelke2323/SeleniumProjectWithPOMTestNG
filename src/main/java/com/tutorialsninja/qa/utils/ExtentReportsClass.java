package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsClass {

	public static ExtentReports generateExtentReport() throws IOException {
	ExtentReports extentReport=new ExtentReports();
	File extentReportFile=new File(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReports" + File.separator + "extentReport.html");
	
	ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("TutorialsNinja Test Automation Results");
	sparkReporter.config().setDocumentTitle("Automation Report");
	sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties configProp= new Properties();
	File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
	FileInputStream fis = null;
	try {
		fis = new FileInputStream(configPropFile);
		configProp.load(fis);
	} catch (Throwable e) {
		e.printStackTrace();
	} 
	
	//Details to print on Reports
	extentReport.setSystemInfo("Application URL", configProp.getProperty("URL"));
	extentReport.setSystemInfo("Browser Name", configProp.getProperty("Browser"));
	extentReport.setSystemInfo("Email", configProp.getProperty("validemail"));
	extentReport.setSystemInfo("Password", configProp.getProperty("password"));
	extentReport.setSystemInfo("OS Name", System.getProperty("os.name"));
	return extentReport;
	}
}
