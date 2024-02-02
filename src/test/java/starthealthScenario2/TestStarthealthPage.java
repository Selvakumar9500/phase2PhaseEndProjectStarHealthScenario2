package starthealthScenario2;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestStarthealthPage {
	
	WebDriver driver;
	
	@BeforeClass
	public void startBrowser()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\selva\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.starhealth.in/");
	}
	
	@Test(priority='1')
	public void click_onPlans() throws InterruptedException
	{
		Actions a = new Actions(driver);
		WebElement plan = driver.findElement(By.xpath("//div[@class='Header_nav-link-container__nlUUp dropdown-active flex only-desktop']/descendant::img[2]"));
		a.moveToElement(plan).build().perform();
		Thread.sleep(1500);
	}
	
	@Test(priority='2')
	public void click_on_option() throws InterruptedException
	{
		WebElement formyfamilylink = driver.findElement(By.xpath("//div[@class='ant-popover-inner']/descendant::a[2]"));
		formyfamilylink.click();
		Thread.sleep(4000);
	}
	
	@Parameters({"Name","Mobile","pin"})
	@Test(priority='3')
	public void enter_user_details(String Name, String Mobile, String pin)
	{
		driver.findElement(By.id("name")).sendKeys(Name);
		driver.findElement(By.id("phoneNumber")).sendKeys(Mobile);
		driver.findElement(By.id("pinCode")).sendKeys(pin);
		
		// click on get quite button
		//driver.findElement(By.xpath("//*[@id=\"get-quotes-form\"]/div/div[3]/div/div[2]/div/button/span[1]")).click();
	}
	
//@Test(priority='4',dataProvider = "testdata")
	//public void navigate_back() throws InterruptedException
	//{
		//driver.navigate().back();
	//	Thread.sleep(3000);
	//}//
	
 /*@Test(priority='5',dataProvider = "testdata")
		public void navigate_back1(String expectedname, String expectedphone,String expectedpin ) throws InterruptedException
		{	
	 Thread.sleep(3000);
	String actualname =	driver.findElement(By.id("name")).getText();
	Assert.assertEquals(actualname, expectedname);
	
	String actualphone =	driver.findElement(By.id("phoneNumber")).getText();
	Assert.assertEquals(actualphone, expectedphone);

	String actualpin =	driver.findElement(By.id("pinCode")).getText();
	Assert.assertEquals(actualpin, expectedpin);
	
		
	}*/
	@Test(priority = '6') 
	public void clickTwitterLogo() throws InterruptedException { 
		Thread.sleep(5000); 
		driver.findElement(By.tagName("body")).sendKeys(org.openqa.selenium.Keys.CONTROL, org.openqa.selenium.Keys.END); 
		// Click on the Twitter logo present in the footer 
		WebElement twitterLogo = driver.findElement(By.xpath("(//img)[103]")); 
		twitterLogo.click(); 
		} 

	@Test(priority = '7') 
	public void validateTwitterPage() { 
		String parentWindowHandle = driver.getWindowHandle(); 
		for (String windowHandle : driver.getWindowHandles()) { 
			if (!windowHandle.equals(parentWindowHandle)) { 
				driver.switchTo().window(windowHandle); 
				break; 
			} 
		} 
		String twitterPageUrl = driver.getCurrentUrl(); 
		Assert.assertFalse(twitterPageUrl.contains("starhealthins"), "Twitter page URL doesn't contain 'starhealthins'"); 
		driver.switchTo().window(parentWindowHandle);
	}
	
	@DataProvider(name="testdata")
	public   Object[][] datasupply() throws FileNotFoundException
	{
		// get the data from excel sheet 
		
		Object[][] inputdata = Xls_DataProvider.getTestData("Sheet1");
		
		return inputdata;
		
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		driver.close();
	}

}