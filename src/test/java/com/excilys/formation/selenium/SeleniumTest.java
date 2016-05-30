package com.excilys.formation.selenium;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SeleniumTest {
	private WebDriver driverNoJs;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		File noScriptFile = new File("src/test/resources/noscript.xpi");
		firefoxProfile.addExtension(noScriptFile);
		driverNoJs = new FirefoxDriver(firefoxProfile);
		baseUrl = "http://localhost:8080";
		driverNoJs.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testFirst() throws Exception {
		driverNoJs.get(baseUrl + "/computer-database/dashboard");
		driverNoJs.findElement(By.linkText("2")).click();
		driverNoJs.findElement(By.linkText("3")).click();
		driverNoJs.findElement(By.linkText("«")).click();
		driverNoJs.findElement(By.id("addComputer")).click();
		driverNoJs.findElement(By.id("computerName")).clear();
		driverNoJs.findElement(By.id("computerName")).sendKeys("dqzzqdqz");
		driverNoJs.findElement(By.id("introduced")).clear();
		driverNoJs.findElement(By.id("introduced")).sendKeys("qzddqz");
		driverNoJs.findElement(By.id("discontinued")).clear();
		driverNoJs.findElement(By.id("discontinued")).sendKeys("dqzdq");
		driverNoJs.findElement(By.id("submitButton")).click();
		driverNoJs.findElement(By.id("introduced")).clear();
		driverNoJs.findElement(By.id("introduced")).sendKeys("24-08-1992");
		driverNoJs.findElement(By.id("discontinued")).clear();
		driverNoJs.findElement(By.id("discontinued")).sendKeys("26-05-2016");
		driverNoJs.findElement(By.id("submitButton")).click();
	}

	@Ignore
	@After
	public void tearDown() throws Exception {
		driverNoJs.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driverNoJs.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverNoJs.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driverNoJs.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
