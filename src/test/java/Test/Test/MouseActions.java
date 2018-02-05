package Test.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseActions {
	
	public static WebDriver driver;
	
	@Before
	public void setUp() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver",
				 "C:/Users/Admin/Downloads/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.get("http://demoqa.com/");
	}
	
	@Ignore
	@Test
	public void testActions() throws InterruptedException{
		
		driver.findElement(By.id("menu-item-140")).click();		
		Thread.sleep(3000);
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(driver.findElement(By.id("draggable")), 300, 200).perform();
		Thread.sleep(2000);		
	}
	
	@Ignore
	@Test
	public void droppableMethod() throws InterruptedException {
		driver.findElement(By.id("menu-item-141")).click();
		Thread.sleep(1000);
		Actions builder = new Actions(driver);
		builder.dragAndDrop(driver.findElement(By.cssSelector("#draggableview")), driver.findElement(By.cssSelector("#droppableview"))).perform();
		Thread.sleep(2000);
	}
	
	@Ignore
	@Test
	public void slider() throws InterruptedException {
		driver.findElement(By.id("menu-item-97")).click();
		Thread.sleep(1000);
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(driver.findElement(By.cssSelector("#slider-range-max > span")), 100, 0).perform();
		Thread.sleep(2000);
	}
	
	@Ignore
	@Test
	public void framesWindows() throws InterruptedException {
		driver.findElement(By.id("menu-item-148")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id=\"tabs-1\"]/div/p/a")).click();
		Thread.sleep(2000);
	}
	
	@Test
	public void cookies() throws InterruptedException {
		driver.get("https://en-gb.facebook.com/login/");
		driver.findElement(By.cssSelector("#email")).sendKeys("shirleyyang_95@hotmail.com");
		driver.findElement(By.cssSelector("#pass")).sendKeys("roo1995");
		driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();
		Thread.sleep(2000);
		
		File f = new File("browser.data");
		
		driver.findElement(By.cssSelector("#userNavigationLabel")).click();
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[text() = 'Log out']")).click();
		Thread.sleep(2000);
	}
	
	@After
	public void afterMethod()
	{
		driver.quit();
	}
}
