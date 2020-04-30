package com.qa.keyword.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
		
		
		public Properties init_properties() throws IOException {
			
			prop = new Properties();
			
			FileInputStream ip = new FileInputStream("C:\\Users\\52001551\\eclipse-workspace\\KeyWordDrivenFrmework\\src\\main\\java\\com\\qa\\keyword\\config\\config.properties");
			prop.load(ip);
			return prop;
		}
		
		public WebDriver initialization(String BrowserName) {
			if(BrowserName.equals("Chrome")) {
				WebDriverManager.chromedriver().setup();
			if(prop.getProperty("headless").equals("yes")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			} 
			else {
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}
		}
		return driver;
			
		}
		
}

