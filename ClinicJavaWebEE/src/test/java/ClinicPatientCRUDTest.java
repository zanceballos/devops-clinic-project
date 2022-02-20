import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.awt.Button;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ClinicPatientCRUDTest {
	private WebDriver driver;
	
	public void login_patient_user() {
		// perform login
		driver.get("http://localhost:8090/ClinicJavaWebEE/login.jsp");
		// enter a valid username
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));

		username.sendKeys("test");
		password.sendKeys("test123");

		// driver.findElement(By.id("sign-in-button"));
		driver.findElement(By.cssSelector("button[type=submit]")).click();

		// wait for login
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
  @Test
  public void getClinic() {
	  driver = new ChromeDriver();
	  
	  driver.manage().window().setSize(new Dimension(1920,1080));
	  
	  login_patient_user();
	  
	  driver.findElement(By.linkText("Clinics")).click();
	  
	  Assert.assertTrue(driver.getCurrentUrl().contains("http://localhost:8090/ClinicJavaWebEE/ClinicServlet/all-clinics"));
	  
	  driver.quit();
  }
  
  @Test
  public void viewClinic() {
	  driver = new ChromeDriver();
	  
	  driver.manage().window().setSize(new Dimension(1920,1080));
	  
	  login_patient_user();
	  
	  driver.findElement(By.linkText("Clinics")).click();
	  
	  driver.findElement(By.linkText("View More")).click();
	  
	  Assert.assertTrue(driver.getCurrentUrl().contains("http://localhost:8090/ClinicJavaWebEE/ClinicServlet/clinic-details?id="));
	  
	  driver.quit();
  }
  @BeforeTest
  public void beforeTest() {
	 System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
  }

  @AfterTest
  public void afterTest() {
  }

}
