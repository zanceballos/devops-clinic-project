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

public class ClinicDoctorCRUDTest {
	// declare Selenium WebDriver
	private WebDriver driver;

	// Reusable function to login the user to perform CRUD Operation
	public void login_doctor_user() {
		// perform login
		driver.get("http://localhost:8090/ClinicJavaWebEE/login.jsp");
		// enter a valid username
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));

		username.sendKeys("marcos");
		password.sendKeys("test");

		// driver.findElement(By.id("sign-in-button"));
		driver.findElement(By.cssSelector("button[type=submit]")).click();

		// wait for login
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void get_clinic() {
		// define the drive instance
		driver = new ChromeDriver();

		driver.manage().window().setSize(new Dimension(1920,1080));
		// perform login
		login_doctor_user();

		// Find Clinics listings page
		driver.findElement(By.linkText("Clinics")).click();

		// driver quit
		driver.quit();
	}

	@Test
	public void doctor_add_clinic() {

		driver = new ChromeDriver();

		driver.manage().window().setSize(new Dimension(1920,1080));

		login_doctor_user();

		driver.findElement(By.linkText("Clinics")).click();

		driver.findElement(By.linkText("Add Clinic")).click();

		if (driver.getCurrentUrl().contains("http://localhost:8090/ClinicJavaWebEE/CreateClinic.jsp")) {
			Assert.assertTrue(true);

			WebElement clinic_name = driver.findElement(By.name("clinic_name"));
			WebElement address = driver.findElement(By.name("address"));
			WebElement location_name = driver.findElement(By.name("location_name"));
			WebElement image = driver.findElement(By.name("image"));
			WebElement description = driver.findElement(By.name("description"));
			WebElement opening_hours = driver.findElement(By.name("opening_hours"));
			WebElement opening_days = driver.findElement(By.name("opening_days"));
			WebElement contact_number = driver.findElement(By.name("contact_number"));

			clinic_name.sendKeys("Woodlands Clinic");
			address.sendKeys("01-204, 131 Marsiling Rise, 730131");
			location_name.sendKeys("woodlands");
			image.sendKeys("https://www.onecaremedical.com.sg/wp-content/uploads/2019/07/OCTM.png");
			description.sendKeys("New Clinic");
			opening_hours.sendKeys("10am to 7pm");
			opening_days.sendKeys("Monday to Saturday");
			contact_number.sendKeys("68347700");

			// select element
			WebElement webElementTab = driver.findElement(By.name("contact_number"));
			webElementTab.sendKeys(Keys.TAB);
			webElementTab.sendKeys(Keys.ENTER);

			// driver.findElement(By.cssSelector("button[type=submit]")).click();

			Assert.assertEquals("http://localhost:8090/ClinicJavaWebEE/ClinicServlet/dashboard",
					driver.getCurrentUrl());

			driver.quit();
		}

	}

	@Test
	public void update_clinic() {
		// define the drive instance
		driver = new ChromeDriver();

		driver.manage().window().setSize(new Dimension(1920,1080));
		// perform login
		login_doctor_user();
 
		// Find Clinics listings page
		driver.findElement(By.linkText("Clinics")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if (driver.getPageSource().contains("Nothing To See Here!") == false) {
			// select the update button from the dropdown
			// driver.findElement(By.xpath(".//button[@data-toggle='dropdown']")).click();
			driver.findElement(By.cssSelector(".card-body .dropdown .dropdown-toggle")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.linkText("Update")).click();

			if (driver.getCurrentUrl().contains("ClinicServlet/edit?id=")) {
				Assert.assertTrue(true);

				// Change the location
				WebElement location_name = driver.findElement(By.name("location_name"));
				location_name.sendKeys("Parkway");

				// select element
				WebElement webElementTab = driver.findElement(By.name("contact_number"));
				webElementTab.sendKeys(Keys.TAB);
				webElementTab.sendKeys(Keys.ENTER);

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				try {
					// Verify Page with assert true
					Assert.assertTrue(driver.getCurrentUrl().contains("ClinicServlet/dashboard"));
				} catch (Exception e) {
					Assert.assertTrue(false);
				}

			} else {
				Assert.assertTrue(false);
			}

		} else {
			Assert.assertTrue(true);
		}

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
