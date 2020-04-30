package com.qa.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.keyword.base.Base;



public class KeywordEngine {
	
	public WebDriver driver;
	public Properties prop;
	public WebElement element;
	
	public static Workbook book;
	public static org.apache.poi.ss.usermodel.Sheet sheet;
	
	public Base base;
	
	public final String SCENARIO_SHEET_PATH = "C:\\Users\\52001551\\eclipse-workspace\\"
			+"KeyWordDrivenFrmework\\src\\main\\java\\com\\qa\\keyword\\scenarios\\KeyWordFWData.xlsx";
	
	
	
	
	public void startExecution(String sheetName) throws IOException, InvalidFormatException {
		
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);    // To get the workbook
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		sheet = book.getSheet(sheetName);     // To get the sheet
		
		
		//To read the excel sheet
		int k=0;
		for(int i=0; i<sheet.getLastRowNum(); i++) {
			
		try {
		String locatorName = sheet.getRow(i+1).getCell(k+1).toString().trim();     //id
		String locatorValue = sheet.getRow(i+1).getCell(k+2).toString().trim();    //username
		String action =	sheet.getRow(i+1).getCell(k+3).toString().trim();  //action
		String value = sheet.getRow(i+1).getCell(k+4).toString().trim();  //value
		
		switch(action) {
		case "Launch browser" : 
			base = new Base();     //Creating object of Base class to call the Base class method initialization
			prop = base.init_properties();    //calling method to initializer properties
			
			if(value.isEmpty() || value.equals("NA")) {      //if browser value is NA or blank in excel sheet then it is telling to take from config
				driver = base.initialization(prop.getProperty("Browser"));
			}
			else {
				driver = base.initialization(value);   //else part if the value is there then take from excel
			}
			
			break;
			
		case "enter url" :
			if(value.isEmpty() || value.equals("NA")) {
				driver.get(prop.getProperty("url"));
              driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			} 
			else {
				driver.get(value);
				   driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			}
			break;
			
		case "quit" :
			driver.quit();
			
		default:
			break;
		}
		
		switch(locatorName) {
		case "id":
		  element = driver.findElement(By.id(locatorValue));
		  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			    if(action.equalsIgnoreCase("sendKeys")) {                 // with id it can be either sendkeys or click so if condition
			    	element.sendKeys(value);
			    }
			    else if(action.equalsIgnoreCase("click")){
			    	element.click();
			    	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			    } else if(action.equalsIgnoreCase("isDisplayed")) {
			    	element.isDisplayed();
			    }
			    else if(action.equalsIgnoreCase("getText")) {
			    	element.getText();
			    }
			    locatorName = null;
		break;
		
		case "xpath" :
		
			  element = driver.findElement(By.xpath(locatorValue));
			  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				    if(action.equalsIgnoreCase("sendKeys")) {                 // with id it can be either sendkeys or click so if condition
				    	element.sendKeys(value);
				    }
				    else if(action.equalsIgnoreCase("click")){
				    	element.click();
				    } 
				    else if(action.equalsIgnoreCase("isDisplayed")) {
				    	element.isDisplayed();
				    }
				    else if(action.equalsIgnoreCase("getText")) {
				    String ElementText =	element.getText();
				    System.out.println("Header-->"+ElementText);
				    }
				    locatorName = null;
			break;	
		
		
		case "linkText" :
			 element = driver.findElement(By.linkText(locatorValue));
			   driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			 element.click();
			 locatorName = null;
				break;
		 }
		}
		
		 catch(Exception e){
			 e.printStackTrace();
		 }
		
	  } 
		 
	     }
	}

