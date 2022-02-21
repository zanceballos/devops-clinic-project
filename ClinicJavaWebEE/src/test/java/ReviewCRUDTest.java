import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ReviewCRUDTest {

	// declare Selenium WebDriver
	private WebDriver driver;

	// Reusable function to login the user to perform CRUD Operation
	public void login_user() {
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

	@Test
	public void create_review() {
		// define drive instance
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920, 1080));

		// login user
		login_user();

		// navigate to home page
		driver.get("http://localhost:8090/ClinicJavaWebEE/UserServlet/home");

		// navigate to clinic listing page
		driver.findElement(By.linkText("Clinics")).click();

		// click view more on a clinic
		driver.findElement(By.linkText("View More")).click();

		// click on reviews
		driver.findElement(By.linkText("Reviews")).click();

		// click on review clinic
		driver.findElement(By.linkText("Review Clinic")).click();
		System.out.println("Navigated to review page " + driver.getCurrentUrl());

		// input form values
		WebElement title = driver.findElement(By.name("review_title"));
		WebElement review = driver.findElement(By.name("review"));
		WebElement rating_score = driver.findElement(By.name("rating_score"));

		title.sendKeys("Good Clinic!");
		review.sendKeys("Good environment and staff");
		rating_score.sendKeys("5");
		
		// submit form and wait to submit
		rating_score.sendKeys(Keys.TAB);
		rating_score.sendKeys(Keys.ENTER);
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// assert if user is brought back to clinic review page
		Assert.assertTrue(driver.getCurrentUrl()
				.contains("http://localhost:8090/ClinicJavaWebEE/ReviewServlet/ListClinicReviews?id="));

		driver.quit();

	}

	
	//display selected clinic reviews
	@Test
	public void display_reviews_by_clinicid() {
		// define drive instance
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920, 1080));

		// login user
		login_user();

		// navigate to home page
		driver.get("http://localhost:8090/ClinicJavaWebEE/UserServlet/home");

		// navigate to clinic listing page
		driver.findElement(By.linkText("Clinics")).click();

		// click view more on a clinic
		driver.findElement(By.linkText("View More")).click();

		// click on reviews
		driver.findElement(By.linkText("Reviews")).click();
		
		if(driver.getCurrentUrl().contains("ReviewServlet/ListClinicReviews?id=")) {
			System.out.print("Review displayed");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		
		driver.quit();

	}

	@Test
	public void display_review_details() {

	}

	@Test
	public void update_review() {
		// define drive instance
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920, 1080));

		// login user
		login_user();

		// navigate to home page
		driver.get("http://localhost:8090/ClinicJavaWebEE/UserServlet/home");

		// navigate to clinic listing page
		driver.findElement(By.linkText("Clinics")).click();

		// click view more on a clinic
		driver.findElement(By.linkText("View More")).click();

		// click on reviews
		driver.findElement(By.linkText("Reviews")).click();
		
		// find more options button and click update review
		driver.findElement(By.cssSelector(".card-body .dropdown .dropdown-toggle")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Update")).click();
		
		//check if page is correct
		if(driver.getCurrentUrl().contains("ReviewServlet/ShowUpdateForm?id=")) {

			//perform update appointment
			
			WebElement title = driver.findElement(By.name("review_title"));
			WebElement review = driver.findElement(By.name("review"));
			WebElement rating_score = driver.findElement(By.name("rating_score"));

			//replace current values with new ones
			title.sendKeys(Keys.chord(Keys.CONTROL + "a"), "Wonderful clinic!");
			review.sendKeys(Keys.chord(Keys.CONTROL + "a"), "Friendly staff");
			rating_score.sendKeys(Keys.chord(Keys.CONTROL + "a"), "5");
			
			// submit form and wait to submit
			rating_score.sendKeys(Keys.TAB);
			rating_score.sendKeys(Keys.ENTER);
			
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			//verify if user is brought back to clinic review page
			try {
				
				Assert.assertTrue(driver.getCurrentUrl()
						.contains("http://localhost:8090/ClinicJavaWebEE/ReviewServlet/ListClinicReviews?id="));
				
			} catch(Exception e) {
				Assert.assertTrue(false);
			}
			
		} else {
			Assert.assertTrue(false);
		}
		
		
		
		driver.quit();

	}

	@Test
	public void delete_review() {

		// define drive instance
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1920, 1080));

		// login user
		login_user();

		// navigate to home page
		driver.get("http://localhost:8090/ClinicJavaWebEE/UserServlet/home");

		// navigate to clinic listing page
		driver.findElement(By.linkText("Clinics")).click();

		// click view more on a clinic
		driver.findElement(By.linkText("View More")).click();

		// click on reviews
		driver.findElement(By.linkText("Reviews")).click();
		
		// find more options button and click delete
		driver.findElement(By.cssSelector(".card-body .dropdown .dropdown-toggle")).click();
		driver.findElement(By.linkText("Delete")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		try {
			
			Assert.assertTrue(driver.getCurrentUrl().contains("ReviewServlet/ListClinicReviews?id="));
			
		} catch (Exception e){
			Assert.assertTrue(false);
		}
		
		driver.quit();
	}
}
