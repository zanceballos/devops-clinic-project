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

		username.sendKeys("someTest");
		password.sendKeys("test123");

		// driver.findElement(By.id("sign-in-button"));
		driver.findElement(By.cssSelector("button[type=submit]")).click();

		// wait for login
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void create_user() {

		// define the drive instance
		driver = new ChromeDriver();

		driver.manage().window().setSize(new Dimension(1920, 1080));

		// perform register
		driver.get("http://localhost:8090/ClinicJavaWebEE/register.jsp");

		// enter a valid username
		WebElement username = driver.findElement(By.name("username"));

		WebElement full_name = driver.findElement(By.name("full_name"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement contact_number = driver.findElement(By.name("contact_number"));
		Select role = new Select(driver.findElement(By.name("role")));
		WebElement password = driver.findElement(By.name("password"));

		username.sendKeys("someTest");
		full_name.sendKeys("testname");
		email.sendKeys("someMail@mail.com");
		contact_number.sendKeys("987987651");
		role.selectByValue("patient");
		password.sendKeys("test123");

		WebElement webElementTab = driver.findElement(By.name("password"));
		webElementTab.sendKeys(Keys.TAB);
		webElementTab.sendKeys(Keys.ENTER);

		Assert.assertEquals("http://localhost:8090/ClinicJavaWebEE/login.jsp", driver.getCurrentUrl().toString());

		driver.quit();
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
		// driver.findElement(By.linkText("Account")).click();

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
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// System.out.println("Second Log: " + driver.getCurrentUrl());
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
				Assert.assertTrue(driver.getCurrentUrl()
						.contains("http://localhost:8090/ClinicJavaWebEE/UserServlet/account-details?id="));
			} catch (Exception e) {
				Assert.assertTrue(false);
			}
		} else {
			Assert.assertTrue(false);
		}

		driver.quit();
	}

	@Test
	public void zdelete_patient_details() {
		// define the drive instance
		driver = new ChromeDriver();

		driver.manage().window().setSize(new Dimension(1920, 1080));
		// perform login
		login_patient_user();
		driver.findElement(By.linkText("My Account")).click();

		driver.findElement(By.linkText("Delete Account")).click();
		
		Assert.assertEquals("http://localhost:8090/ClinicJavaWebEE/",driver.getCurrentUrl().toString());

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
