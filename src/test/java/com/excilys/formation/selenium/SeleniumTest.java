package com.excilys.formation.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

	private WebDriver webDriver;
	private String baseUrl;
	
	@Before 
	  public void setUp() throws Exception { 
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chrome");
	    webDriver = new ChromeDriver();
	    baseUrl = "http://www.google.fr"; 
	    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	  } 
	
	@Test 
	  public void testSelenium() throws Exception { 
	    webDriver.get(baseUrl); 
	  } 
	
	
}
