package com.sophos.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sophos.src.JavaMysql;

public class TestMysql {
	public static WebDriver driver;
	public JavaMysql javaMysql;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\SELENIUM\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		javaMysql = new JavaMysql(driver);
		Boolean isConnected = javaMysql.setConnection();
		assertTrue(isConnected);
		
		Boolean results = javaMysql.search();
		assertTrue(results);
	}

}
