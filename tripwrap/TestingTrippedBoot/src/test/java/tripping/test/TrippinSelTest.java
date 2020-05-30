package tripping.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TrippinSelTest {

	private WebDriver driver; // These were public static, pay attention to when it can be private. Dont be
								// public if it doesnt need to be.
	private WebDriverWait wait;
	static {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\bisher.kunbargi\\\\Desktop\\\\chromedriver.exe");
	}

	@BeforeTest
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void openHome() throws InterruptedException {
		wait = new WebDriverWait(driver, 5);
		driver.get("http://localhost:88/TestingTrippedFront/");
		Assert.assertEquals(driver.getTitle(), "Home Page");
//		WebElement entryButton = driver.findElement(By.id("entryButton"));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("entryButton")));

		driver.findElement(By.id("entryButton")).click();
		List<WebElement> entryButtonGone = driver.findElements(By.id("entryButton"));
		Assert.assertEquals(entryButtonGone.size(), 0);
		// ExpectedConditions
	}

	// Slight logic spill over, consolidate.

	@Parameters({ "testParam" })
	@Test(priority = 2, dependsOnMethods = "openHome")
	public void testSubmit(String param) throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		Assert.assertEquals(driver.getTitle(), "Trip Editor");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("location-input")));
		driver.findElement(By.id("location-input")).clear();
		driver.findElement(By.id("location-input")).sendKeys(param);

	}

	@Parameters({ "testParam" })
	@Test(priority = 3, dependsOnMethods = "openHome")
	public void submitButton(String param) {
		String text = driver.findElement(By.id("location-input")).getAttribute("value");
		Assert.assertEquals(text, param);
		WebElement goBack = driver.findElement(By.id("locationSubmit")); // goBack for submit???
		goBack.click();
		List<WebElement> leftHomePage = driver.findElements(By.id("locationSubmit"));
		Assert.assertEquals(leftHomePage.size(), 0);
		Assert.assertEquals(driver.getTitle(), "Trip Created!"); // Assert that content appears on page.
	}

	@Parameters({ "testParam" })
	@Test(priority = 4, dependsOnMethods = "submitButton")
	public void testCreate(String param) throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		String durationToSend = "10 Days";
		String costToSend = "1000";
		String dateToSend = "10/08/1992";
		driver.findElement(By.id("createBack")).click();

		// Clear and Send Keys can be consolidated into one function. repetetive code

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("detailsButton-" + param)));
		driver.findElement(By.id("detailsButton-" + param)).click();
		WebElement durationInput = driver.findElement(By.id("duration"));
		durationInput.clear();
		durationInput.sendKeys(durationToSend);
		WebElement costInput = driver.findElement(By.id("cost"));
		costInput.clear();
		costInput.sendKeys(costToSend);
		WebElement dateInput = driver.findElement(By.id("date"));
		dateInput.clear();
		dateInput.sendKeys(dateToSend);
		driver.findElement(By.id("updateButton")).click();

//		List<WebElement> backHomePage = driver.findElements(By.id("createBack"));
//		Assert.assertEquals(backHomePage.size(), 0);
		String duration = durationInput.getAttribute("value");
		String cost = costInput.getAttribute("value");
		String date = dateInput.getAttribute("value");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(param + "datedetails")));
		String dateDetails = driver.findElement(By.id(param + "datedetails")).getText();
		String durationDetails = driver.findElement(By.id(param + "durationdetails")).getText();
		String costDetails = driver.findElement(By.id(param + "costdetails")).getText();

		Assert.assertEquals(dateDetails, date);
		Assert.assertEquals(durationDetails, duration);
		Assert.assertEquals(costDetails, cost);

		Assert.assertEquals(durationToSend, duration);
		Assert.assertEquals(costToSend, cost);
		Assert.assertEquals(dateToSend, date);
	}

	@Parameters({ "testParam" })
	@Test(priority = 5, dependsOnMethods = "testCreate")
	public void testDetailsPage(String param) throws InterruptedException {
		wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(param + "PageDirection")));
		driver.findElement(By.id(param + "PageDirection")).click();

		List<WebElement> detailsPage = driver.findElements(By.id("updateButton"));
		Assert.assertEquals(detailsPage.size(), 0);
		Assert.assertEquals(driver.getTitle(), "Trip Page");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("pageBackButton")));
		driver.findElement(By.id("pageBackButton")).click();

	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		driver.quit();
	}

}
