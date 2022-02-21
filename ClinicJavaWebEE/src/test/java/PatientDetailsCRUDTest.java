import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PatientDetailsCRUDTest {
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
	public void get_patient_details() {

		// define the drive instance
		driver = new ChromeDriver();
		
		driver.manage().window().setSize(new Dimension(1920, 1080));
		// perform login
		login_patient_user();


		// Find Patients listings page
		driver.findElement(By.linkText("My Account")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElement(By.linkText("Account")).click();

		

		if (driver.getCurrentUrl().contains("http://localhost:8090/ClinicJavaWebEE/UserServlet/account-details?id=")) {
			System.out.println("Correct!");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		driver.quit();
	}
	
	@Test
	public void update_patient_details() {

		// define the drive instance
		driver = new ChromeDriver();
		
		driver.manage().window().setSize(new Dimension(1920, 1080));
		// perform login
		login_patient_user();
		
		driver.findElement(By.linkText("My Account")).click();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//System.out.println("Second Log: " + driver.getCurrentUrl());
		// Find Patients listings page
		driver.findElement(By.linkText("Edit Account")).click();

		
		if (driver.getCurrentUrl().contains("http://localhost:8090/ClinicJavaWebEE/UserServlet/showUpdateForm?id=")) {
			System.out.println("Correct!");
			Assert.assertTrue(true);

			// Change the full name
			WebElement full_name = driver.findElement(By.name("full_name"));
			full_name.sendKeys("Test Full Name New");
	
			
			// select element
			WebElement webElementTab = driver.findElement(By.name("contact_number"));
			webElementTab.sendKeys(Keys.TAB);
			webElementTab.sendKeys(Keys.ENTER);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			try {
				// Verify Page with assert true
				Assert.assertTrue(driver.getCurrentUrl().contains("http://localhost:8090/ClinicJavaWebEE/UserServlet/account-details?id="));
			} catch (Exception e) {
				Assert.assertTrue(false);
			}
		} else {
			Assert.assertTrue(false);
		}

		driver.quit();
	}
	


	 
  @Test
  public void f() {
  }
  @BeforeTest
  public void beforeTest() {
	 System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
  }
  @AfterTest
  public void afterTest() {
  }
}
