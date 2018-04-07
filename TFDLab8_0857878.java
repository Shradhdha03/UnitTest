import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
Program Name: TFDLab8_0857878.java
      Author: Shradhdha Parsana
        Date: Mar 14, 2018 8:34:08 AM
 Description: Test Web Driver
*/
public class TFDLab8_0857878
{
	static WebDriver driver;
	static boolean isRegister = false;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		// Load the Chrome webdriver using Java properties
		System.setProperty("webdriver.chrome.driver", "C:/Users/shraadhdha/Desktop/lab6/chromedriver.exe");
		driver = new ChromeDriver();
		String baseUrl = "http://newtours.demoaut.com";
		driver.get(baseUrl);
		isRegister=RunRegistration();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		try
		{
			Thread.sleep(10000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// close Chrome
		driver.close();

		// exit the program explicitly
		System.exit(0);
	}

	@Before
	public void setUp() throws Exception
	{

	}

	@After
	public void tearDown() throws Exception
	{
	}

	// Testing Deposit
	@Test
	public void TestRegistration()
	{

//		System.out.println(isRegister);
		assertEquals(true, isRegister);
	}

	@Test
	public void TestConfirmation()
	{
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isRegister)
		{
			// Find Flights
			driver.findElement(By.linkText("Flights")).click();
			selectValueFromRadioButton(driver, "tripType", "roundtrip", true);
			selectValueFromSelectBox(driver, "passCount", "2", false);
			selectValueFromSelectBox(driver, "fromPort", "London", false);
			selectValueFromSelectBox(driver, "fromMonth", "June", false);
			selectValueFromSelectBox(driver, "fromDay", "11", false);
			selectValueFromSelectBox(driver, "toPort", "Paris", false);
			selectValueFromSelectBox(driver, "toMonth", "August", false);
			selectValueFromSelectBox(driver, "toDay", "1", false);
			selectValueFromRadioButton(driver, "servClass", "First", true);
			selectValueFromSelectBox(driver, "airline", "Unified", true);
			driver.findElement(By.name("findFlights")).click();

			// Reserve Flights
			selectValueFromRadioButton(driver, "outFlight", "Blue Skies Airlines$361", true);
			selectValueFromRadioButton(driver, "inFlight", "Unified Airlines$633", true);
			driver.findElement(By.name("reserveFlights")).click();

			// Buy Flights
			WebElement textbox = driver.findElement(By.name("passFirst0"));
			textbox.sendKeys("BooBoo");
			textbox = driver.findElement(By.name("passLast0"));
			textbox.sendKeys("FooFoo");
			textbox = driver.findElement(By.name("passFirst1"));
			textbox.sendKeys("BooBoo1");
			textbox = driver.findElement(By.name("passLast1"));
			textbox.sendKeys("FooFoo1");
			textbox = driver.findElement(By.name("creditnumber"));
			textbox.sendKeys("1111222233334444");

			driver.findElement(By.name("buyFlights")).click();

			// grap confirmation number
			List<WebElement> labels = driver.findElements(By.tagName("font"));
			for (WebElement option : labels)
			{
				if (option.getText().contains("Confirmation"))
				{
					System.out.println(option.getText().trim());
					break;
				}
			}

			// Grab title to check test pass or fail
			String actualTitle = driver.getTitle();
			String expectedTitle = "Flight Confirmation: Mercury Tours";
//			System.out.println(actualTitle.trim().equalsIgnoreCase(expectedTitle));
			if(actualTitle.trim().equalsIgnoreCase(expectedTitle)) {
				assertTrue(true);
			}else {
				assertTrue(false);
			}
		} else
		{
			assertTrue(false);
		}
		assertTrue(true);
	}

	public static boolean RunRegistration()
	{

		driver.findElement(By.linkText("REGISTER")).click();
		WebElement textbox = driver.findElement(By.name("firstName"));
		textbox.sendKeys("Shradhdha");
		textbox = driver.findElement(By.name("lastName"));
		textbox.sendKeys("Parsana");
		textbox = driver.findElement(By.name("phone"));
		textbox.sendKeys("1-222-333-4444");
		textbox = driver.findElement(By.name("userName"));
		textbox.sendKeys("s_parsana@myself.com");
		textbox = driver.findElement(By.name("address1"));
		textbox.sendKeys("Silicon Valley");
		textbox = driver.findElement(By.name("city"));
		textbox.sendKeys("San Francisco Bay");
		textbox = driver.findElement(By.name("state"));
		textbox.sendKeys("California");
		
		driver.findElement(By.name("register")).click();
		// Grab thank you
		boolean isRegister = false;
		List<WebElement> labels = driver.findElements(By.tagName("font"));
		for (WebElement option : labels)
		{
			if (option.getText().trim().contains("Thank you"))
			{
//				System.out.println(option.getText().trim());
				isRegister = true;
				break;
			}
		}

		return isRegister;
	}

	public static void selectValueFromRadioButton(WebDriver driver, String name, String value, boolean isPartial)
	{
		List<WebElement> select = driver.findElements(By.name(name));
		for (WebElement option : select)
		{
			if (isPartial)
			{
				if (option.getAttribute("value").trim().contains(value))
				{
					option.click();
					break;
				}
			} else
			{
				if (option.getAttribute("value").trim().equalsIgnoreCase(value))
				{
					option.click();
					break;
				}
			}
		}
	}

	public static void selectValueFromSelectBox(WebDriver driver, String name, String value, boolean isPartial)
	{

		WebElement select = driver.findElement(By.name(name));
		List<WebElement> allOptions = select.findElements(By.tagName("option"));
		for (WebElement option : allOptions)
		{
			if (isPartial)
			{
				if (option.getText().trim().contains(value))
				{
					option.click();
					break;
				}
			} else
			{
				if (option.getText().trim().equalsIgnoreCase(value))
				{
					option.click();
					break;
				}
			}
		}
	}
}