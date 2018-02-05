package Test.Test;

import org.testng.annotations.*;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestNG {
	public static WebDriver driver;
	static ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public static void beforeClassMethod() {
		// where to create the report file
		report = new ExtentReports("C:\\Users\\admin\\Desktop\\automationreport2.html", true);
	}

	@BeforeTest
	public void setUp() {
		 System.setProperty("webdriver.chrome.driver",
				 "C:/Users/Admin/Downloads/chromedriver_win32/chromedriver.exe");
				

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\Admin\\Downloads\\geckodriver-v0.19.1-win32\\geckodriver.exe");

	}

	@Test (dataProvider="Authentication")
	public void verifyHomePageTitle(String aUsername, String aPassword, String aBrowser) throws InterruptedException {
		
		if (aBrowser.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else
		{
			driver = new FirefoxDriver();
		}
		// init/start the test
		test = report.startTest("Using TestNG to verify website login for " + aBrowser);
		

		//driver.manage().window().maximize();
		// add a note to the test
		test.log(LogStatus.INFO, "Browser started");
		driver.get("http://thedemosite.co.uk/");
		Thread.sleep(1000);
		String title = driver.getTitle();
		//System.out.println(title);

		if (title
				.equals("FREE example PHP code and online MySQL database - example username password protected site")) {
			// report the test as a pass
			test.log(LogStatus.PASS, "Opened website");
		} else {
			test.log(LogStatus.FAIL, "Cannot open webpage");
		}

		driver.findElement(By.xpath("//*[text() = '3. Add a User']")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys(aUsername);

		// call method from another class: since method is static it can be called like this
		ReadExcelFile.readFile();


		driver.findElement(By.name("password")).sendKeys(aPassword);
		driver.findElement(By.name("FormsButton2")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[text() = '4. Login']")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys(aUsername);
		driver.findElement(By.name("password")).sendKeys(aPassword);
		driver.findElement(By.name("FormsButton2")).click();
		Thread.sleep(1000);

		boolean text1 = driver.getPageSource().contains("Successful Login");
		if (text1) {
			//System.out.println("It exists");
			test.log(LogStatus.PASS, "Successful Login");
		} else {
			test.log(LogStatus.FAIL, "Login failed");
		}

		report.endTest(test);
		
		driver.quit();

	}
	
	@DataProvider
	public Object[][] Authentication() throws Exception{
		Object[][] testObjArray = ReadExcelFile.readIntoArray("C:\\Users\\Admin\\Desktop\\thedemosite.xlsx", "Sheet1");
//		for (Object[] ar : testObjArray) {
//			for (Object s: ar) {
//				System.out.print(s + ",");
//			}
//			System.out.println();
//		}
		return testObjArray;
	}
	
	@AfterTest
	public static void afterClassMethod() {
		report.flush();
	}
}
