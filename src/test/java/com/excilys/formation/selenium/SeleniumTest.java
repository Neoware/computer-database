package com.excilys.formation.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

// purpose
public class SeleniumTest {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		// File noScriptFile = new File("src/test/resources/noscript.xpi");
		// firefoxProfile.addExtension(noScriptFile);
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

}
