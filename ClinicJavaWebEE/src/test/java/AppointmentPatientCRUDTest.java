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

public class AppointmentPatientCRUDTest {

	// declare Selenium WebDriver
	private WebDriver driver;

	// Reusable function to login the user to perform CRUD Operation
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
	public void show_selected_clinic_details() {
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_patient_user();

		// Find Clinics listings page
		driver.findElement(By.linkText("Clinics")).click();

		// Click on a view more button
		driver.findElement(By.linkText("View More")).click();
		// Click on a view more button
		driver.findElement(By.linkText("Book an Appointment")).click();

		if (driver.getCurrentUrl().contains("AppointmentServlet/AppointmentClinic?id=")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		// close driver
		driver.quit();
	}

	@Test
	public void patient_book_appointment() {
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_patient_user();

		// Go to Home Page
		driver.get("http://localhost:8090/ClinicJavaWebEE/UserServlet/home");
		System.out.println("First Log: " + driver.getCurrentUrl());
		// Find Clinics listings page
		driver.findElement(By.linkText("Clinics")).click();

		// Click on a view more button
		driver.findElement(By.linkText("View More")).click();

		// Click on a view more button
		driver.findElement(By.linkText("Book an Appointment")).click();

		// Booking form
		Select appointment_type = new Select(driver.findElement(By.name("appointment_type")));
		WebElement date = driver.findElement(By.name("date"));
		WebElement time = driver.findElement(By.name("time"));

		appointment_type.selectByValue("Consultation");
		date.sendKeys("03152022");
		time.sendKeys("0200PM");

		WebElement webElementTab = driver.findElement(By.name("time"));
		webElementTab.sendKeys(Keys.TAB);
		webElementTab.sendKeys(Keys.ENTER);
		// submit booking form
		// driver.findElement(By.cssSelector("button[type=submit]")).click();

		// wait for submit
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// check if page has success text
		driver.findElement(By.className("success-Section"));

		// assert if it reaches the success page
		Assert.assertTrue(driver.getCurrentUrl().equals("http://localhost:8090/ClinicJavaWebEE/BookSuccess.jsp"));

		driver.quit();
	}

	@Test
	public void get_patient_appointment() {

		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_patient_user();

		driver.get("http://localhost:8090/ClinicJavaWebEE/UserServlet/home");
		System.out.println("Second Log: " + driver.getCurrentUrl());
		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if (driver.getCurrentUrl().contains("AppointmentServlet/PatientAppointments?userid=")) {
			System.out.println("Correct!");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		driver.quit();
	}

	@Test
	public void show_patient_appointment_details() {

		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_patient_user();

		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Assert.assertTrue(driver.getPageSource().contains("AppointmentServlet/PatientAppointments?userid="));
		// conditional statement here to check whether the list is empty or not before
		// going to update button
		if (driver.getPageSource().contains("Nothing To See Here!") == false) {
			// select the update button from the dropdown
			// driver.findElement(By.xpath(".//button[@data-toggle='dropdown']")).click();
			driver.findElement(By.cssSelector(".card-body .dropdown .dropdown-toggle")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

		driver.quit();
	}

	@Test
	public void update_patient_appointment_details() {
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_patient_user();

		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// driver.findElement(By.linkText("Manage")).click();
		Assert.assertTrue(driver.getPageSource().contains("AppointmentServlet/PatientAppointments?userid="));
		if (driver.getPageSource().contains("Nothing To See Here!") == false) {
			// select the update button from the dropdown
			// driver.findElement(By.xpath(".//button[@data-toggle='dropdown']")).click();
			driver.findElement(By.cssSelector(".card-body .dropdown .dropdown-toggle")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
				status.selectByValue("cancelled");
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
							driver.getCurrentUrl().contains("AppointmentServlet/PatientAppointments?userid="));
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

		// quit driver
		driver.quit();
	}

	@Test
	public void delete_patient_appointment() {
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_patient_user();

		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Assert.assertTrue(driver.getPageSource().contains("AppointmentServlet/PatientAppointments?userid="));
		if (driver.getPageSource().contains("Nothing To See Here!") == false) {
			Assert.assertTrue(true);
			// perform update of appointment
			driver.findElement(By.cssSelector(".card-body .dropdown .dropdown-toggle")).click();

			driver.findElement(By.linkText("Delete")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			try {
				// Verify Page with assert true
				Assert.assertTrue(driver.getCurrentUrl().contains("AppointmentServlet/PatientAppointments?userid="));
			} catch (Exception e) {
				Assert.assertTrue(false);
			}

		} else {
			Assert.assertTrue(false);
		}

		// quit driver
		driver.quit();
	}

	@Test
	public void error_update_patient_appointment() {
		// define the drive instance
		driver = new ChromeDriver();
		// perform login
		login_patient_user();

		// Find Clinics listings page
		driver.findElement(By.linkText("Appointments")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Assert.assertTrue(driver.getPageSource().contains("AppointmentServlet/PatientAppointments?userid="));

		if (!driver.getPageSource().contains("Nothing To See Here!")) {
			Assert.assertTrue(true);

			driver.findElement(By.cssSelector(".card-body .dropdown .dropdown-toggle")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.linkText("Update")).click();

			// perform update of appointment
			Select appointment_type = new Select(driver.findElement(By.name("appointment_type")));
			Select status = new Select(driver.findElement(By.name("status")));
			WebElement date = driver.findElement(By.name("date"));

			appointment_type.selectByValue("Consultation");
			status.selectByValue("cancelled");
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

		// quit driver
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
