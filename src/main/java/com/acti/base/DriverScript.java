package com.acti.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * Name:DriverScript
 * Description: This class file is responsible for loading the properties file 
 * and loading the application.
 * Developed by: Md Faruk
 * date: 11/24/22
 * Reviewed By: Shantosh
 */

public class DriverScript {

	public static WebDriver driver;
	static Properties prop;

	public DriverScript()

	// loading config file.
	{

		try
		{
			File file = new File("./at_config/config.properties");
			FileInputStream fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);

		}

		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to load the propertis file please check  " + e.getMessage());
		}

	}

	// Initiating the application.
	
	public void initApplication()
	{
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome"))

		{
			System.setProperty("webdriver.chrome.driver", "./at_browser/chromedriver.exe");
			driver = new ChromeDriver();
		}

		else {
			System.err.println("browser parameter is not supported, please the config file");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		getURL();
		
	}

	public static void getURL()

	{
		String url = prop.getProperty("url");
        driver.get(url);
	}

	public void quitDriver()
	{
		driver.close();
	}

}
