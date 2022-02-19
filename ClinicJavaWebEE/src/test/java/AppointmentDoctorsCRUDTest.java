import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.awt.Button;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class AppointmentDoctorsCRUDTest {

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
	public void get_clinic_appointments() { 
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_doctor_user();

		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();

		// Find Clinics listings page
		driver.findElement(By.linkText("Manage")).click();

		if (driver.findElement(By.className("clinic-appointment")) != null) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		// driver quit
		driver.quit();
	}

	@Test
	public void show_doctor_update_appointment_form() {
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_doctor_user();

		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Manage")).click();

		// Assert.assertTrue(driver.getPageSource().contains("AppointmentServlet/ClinicAppointments?clinicid=")
		// == true);

		if (driver.getPageSource().contains("No Appointments Yet!") == false) {
			driver.findElement(By.cssSelector("td .dropdown .dropdown-toggle")).click();

			driver.findElement(By.linkText("Update")).click();

			// check whether in correct page
			if (driver.getCurrentUrl().contains("AppointmentServlet/ShowAppointmentDetails?id=")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}

		} else {
			// assert true since there are no appointments to be updated
			Assert.assertTrue(true);
		}

		// driver quit
		driver.quit();
	}

	@Test
	public void doctor_update_appointment_details() {
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_doctor_user();
		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Manage")).click();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Assert.assertTrue(driver.getPageSource().contains("AppointmentServlet/ClinicAppointments?clinicid="));

		if (driver.getPageSource().contains("No Appointments Yet!") == false) {
			driver.findElement(By.cssSelector("td .dropdown .dropdown-toggle")).click();

			driver.findElement(By.linkText("Update")).click();

			// check whether in correct page
			if (driver.getCurrentUrl().contains("AppointmentServlet/ShowAppointmentDetails?id=")) {
				Assert.assertTrue(true);

				// perform update of appointment
				Select appointment_type = new Select(driver.findElement(By.name("appointment_type")));
				Select status = new Select(driver.findElement(By.name("status")));
				WebElement date = driver.findElement(By.name("date"));
				WebElement time = driver.findElement(By.name("time"));

				appointment_type.selectByValue("Consultation");
				status.selectByValue("completed");
				date.sendKeys("06152022");
				time.sendKeys("0600PM");

				WebElement webElementTab = driver.findElement(By.name("time"));
				webElementTab.sendKeys(Keys.TAB);
				webElementTab.sendKeys(Keys.ENTER);
				// driver.findElement(By.cssSelector("button[type=submit]")).click();

				// wait for submit
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				try {
					// Verify Page with assert true
					Assert.assertTrue(
							driver.getCurrentUrl().contains("AppointmentServlet/ClinicAppointments?clinicid="));
				} catch (Exception e) {
					Assert.assertTrue(false);
				}

			} else {
				Assert.assertTrue(false);
			}

		} else {
			// assert true since there are no appointments to be updated
			Assert.assertTrue(true);
		}

		driver.quit();

	}

	@Test
	public void doctor_delete_appointment_details() {
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_doctor_user();

		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Manage")).click();

		// if statement to check if empty or not
		if (driver.getPageSource().contains("No Appointments Yet!") == false) {
			driver.findElement(By.cssSelector("td .dropdown .dropdown-toggle")).click();

			driver.findElement(By.linkText("Delete")).click();
			// wait for submit
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			try {
				// Verify Page with assert true
				Assert.assertTrue(driver.getCurrentUrl().contains("AppointmentServlet/ClinicAppointments?clinicid="));
			} catch (Exception e) {
				Assert.assertTrue(false);
			}

		} else {
			// assert true since there are no appointments to be deleted
			Assert.assertTrue(true);
		}

		driver.quit();
	}

	@Test
	public void error_doctor_update_appointment_details() {
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_doctor_user();
		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Manage")).click();

		if (!driver.getPageSource().contains("No Appointments Yet!")) {
			driver.findElement(By.cssSelector("td .dropdown .dropdown-toggle")).click();

			driver.findElement(By.linkText("Update")).click();

			// check whether in correct page
			if (driver.getCurrentUrl().contains("AppointmentServlet/ShowAppointmentDetails?id=")) {
				Assert.assertTrue(true);

				// perform update of appointment
				Select appointment_type = new Select(driver.findElement(By.name("appointment_type")));
				Select status = new Select(driver.findElement(By.name("status")));
				WebElement date = driver.findElement(By.name("date"));
				WebElement time = driver.findElement(By.name("time"));

				appointment_type.selectByValue("Consultation");
				status.selectByValue("completed");
				date.sendKeys("06152022");

				WebElement webElementTab = driver.findElement(By.name("time"));
				webElementTab.sendKeys(Keys.TAB);
				webElementTab.sendKeys(Keys.ENTER);
				// driver.findElement(By.cssSelector("button[type=submit]")).click();

				// wait for submit
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				try {
					// Verify Page with assert true
					Assert.assertTrue(driver.getCurrentUrl().contains("AppointmentServlet/ShowAppointmentDetails?id="));
					System.out.println(driver.getCurrentUrl());
					if (driver.findElement(By.className("alert")) != null) {
						Assert.assertTrue(true);
					} else {

						Assert.assertTrue(false);
					}

				} catch (Exception e) {
					Assert.assertTrue(false);
				}

			} else {
				Assert.assertTrue(false);
			}

		} else {
			// assert true since there are no appointments to be updated
			Assert.assertTrue(true);
		}
		
		driver.quit();
	}

	@BeforeTest
	public void beforeTest() {
		// this will be execute when function trigger
		// define the chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");

	}

	@AfterTest
	public void afterTest() {
		// driver quit
	}

}
