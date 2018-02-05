package Test.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ChromeDriverTest {
	
	static ExtentReports report;
	ExtentTest test;
	static WebDriver driver;
	static WebDriver fdriver;
	
	@BeforeClass
	public static void beforeClassMethod()
	{
		// where to create the report file
		report = new ExtentReports("C:\\Users\\admin\\Desktop\\automationreport.html", true);
	}
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Downloads/chromedriver_win32/chromedriver.exe");

		driver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\Admin\\Downloads\\geckodriver-v0.19.1-win32\\geckodriver.exe");

		fdriver = new FirefoxDriver();
	}

	@Ignore
	@Test
	public void testGoogleSearch() throws InterruptedException {
		// Optional, if not specified, WebDriver will search your path for chromedriver
		driver.get("http://www.qa.com/");
		Thread.sleep(5000); // Let the user actually see something!
		// WebElement searchBox = driver.findElement(By.name("q"));
		// searchBox.sendKeys("ChromeDriver");
		// searchBox.submit();
		Thread.sleep(5000); // Let the user actually see something!
		driver.quit();
	}
	
	@Ignore
	@Test
	public void checkWeb() {
		driver.get("http://www.qa.com/");
		assertEquals("https://www.qa.com/", driver.getCurrentUrl());
		driver.quit();
	}

	// @Ignore
	// @Test
	// public void myTestMethod() {
	// System.out.println("Hello World");
	// }
	//
	// @BeforeClass
	// public static void method1() {
	// System.out.println("Before Class");
	// }
	//
	// @Before
	// public void method2()
	// {
	// System.out.println("Before Test");
	// }
	//
	// @Test
	// public void method3()
	// {
	// System.out.println("Test 1");
	// }
	//
	// @Test
	// public void method4()
	// {
	// System.out.println("Test 2");
	// }
	//
	// @After
	// public void method5()
	// {
	// System.out.println("After Test");
	// }
	//
	// @AfterClass
	// public static void method6()
	// {
	// System.out.println("After Class");
	// }
	
	@Ignore
	@Test
	public void testDemoSite() throws InterruptedException
	{
		driver.get("http://thedemosite.co.uk/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text() = '3. Add a User']")).click();
		Thread.sleep(2000);
		WebElement searchbox = driver.findElement(By.name("username"));
		searchbox.sendKeys("hello");
		driver.findElement(By.name("password")).sendKeys("hello");
		driver.findElement(By.name("FormsButton2")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//*[text() = '4. Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("username")).sendKeys("hello");
		driver.findElement(By.name("password")).sendKeys("hello");
		driver.findElement(By.name("FormsButton2")).click();
		Thread.sleep(5000);
		
		boolean text1 = driver.getPageSource().contains("Successful Login");
		if (text1)
		{
			System.out.println("It exists");
		}
		
		assertTrue(text1);
		
		driver.quit();
	}
	
	// test report for login demosite

	@Ignore
	@Test //(priority = 1, enabled = true)
	public void verifyHomePageTitle() throws InterruptedException{
		
		// init/start the test
		test = report.startTest("Verify application Title");

		fdriver.manage().window().maximize();
		// add a note to the test
		test.log(LogStatus.INFO, "Browser started");
		fdriver.get("http://thedemosite.co.uk/");
		Thread.sleep(2000);
		String title = fdriver.getTitle();
		System.out.println(title);

		if (title.equals(
				"FREE example PHP code and online MySQL database - example username password protected site")) {
			// report the test as a pass
			test.log(LogStatus.PASS, "Opened website");
		} else {
			test.log(LogStatus.FAIL, "Cannot open webpage");
		}
		
		fdriver.findElement(By.xpath("//*[text() = '3. Add a User']")).click();
		Thread.sleep(2000);
		WebElement searchbox = fdriver.findElement(By.name("username"));
		
		// call method from another class: since method is static it can be called like this
		ReadExcelFile.readFile();
		
		searchbox.sendKeys(ReadExcelFile.readData.get(1));
		
		fdriver.findElement(By.name("password")).sendKeys(ReadExcelFile.readData.get(4));
		fdriver.findElement(By.name("FormsButton2")).click();
		Thread.sleep(5000);
		
		fdriver.findElement(By.xpath("//*[text() = '4. Login']")).click();
		Thread.sleep(2000);
		fdriver.findElement(By.name("username")).sendKeys(ReadExcelFile.readData.get(1));
		fdriver.findElement(By.name("password")).sendKeys(ReadExcelFile.readData.get(4));
		fdriver.findElement(By.name("FormsButton2")).click();
		Thread.sleep(5000);
		
		boolean text1 = fdriver.getPageSource().contains("Successful Login");
		if (text1)
		{
			System.out.println("It exists");
			test.log(LogStatus.PASS, "Successful Login");
		}else {
			test.log(LogStatus.FAIL, "Login failed");
		}
		
		report.endTest(test);

	}
	
	
	
	@AfterClass
	public static void afterClassMethod()
	{
		report.flush();
		driver.quit();
		fdriver.quit();
	}
}
