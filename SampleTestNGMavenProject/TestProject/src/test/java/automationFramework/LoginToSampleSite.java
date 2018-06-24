package automationFramework;

import org.testng.annotations.Test;
import junit.framework.Assert;
import org.testng.annotations.BeforeSuite;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;

public class LoginToSampleSite {
	public WebDriver driver;
	
  @Test
  public void login() throws InterruptedException {
      driver.get("http://opensource.demo.orangehrmlive.com/");
      
      driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
      
	  WebElement username = driver.findElement(By.id("txtUsername"));
	  username.sendKeys("admin");
	  
	  WebElement password = driver.findElement(By.id("txtPassword"));
	  password.sendKeys("admin");
	  
	  driver.findElement(By.name("Submit")).click();

	  Thread.sleep(5000);
	  
	  if(!driver.findElement(By.id("welcome")).isDisplayed()) {
		  Assert.fail("Unable to login.");
	  }
	  
  }
  
  @Test
  public void logout() throws InterruptedException {
	  if(driver.findElement(By.id("welcome")).isDisplayed()) {
		  WebElement welcomeLabel = driver.findElement(By.id("welcome"));
		  welcomeLabel.click();
	  }
	  
	  if(driver.findElement(By.id("welcome-menu")).isDisplayed()) {
		  WebElement logout = driver.findElement(By.linkText("Logout"));
		  logout.click();
		  Thread.sleep(2000);
	  }
	  
	  if(!driver.findElement(By.id("txtUsername")).isDisplayed()) {
		  Assert.fail("Unable to logout."); 
	  }
  }
  
  @BeforeSuite (alwaysRun = true)
  public void setUp() {
	  
	  // Setting Chrome driver path
	  String driverPath = "src\\test\\java\\lib\\";
	  System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
	  
	  // Create a new instance of the Chrome driver
      driver = new ChromeDriver();
 
      //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 
      driver.manage().deleteAllCookies();
      
      driver.manage().window().maximize();
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() {
	// Close the driver
	driver.quit();
  }

}
