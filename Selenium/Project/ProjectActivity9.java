package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProjectActivity9 {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		// Initialize driver
		driver = new FirefoxDriver();
		// Initialize wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Open the page
		driver.get("https://alchemy.hguy.co/jobs/wp-admin");
	}

	@DataProvider(name = "Credentials")
	public static Object[][] creds() {
		return new Object[][] { { "root", "pa$$w0rd" } };
	}

@Test(dataProvider = "Credentials")
public void loginTest(String username, String password) {
 // Find the input fields and the login button
  WebElement usernameField = driver.findElement(By.id("user_login"));
  WebElement passwordField = driver.findElement(By.id("user_pass"));
  WebElement loginButton = driver.findElement(By.id("wp-submit"));
  // Clear the input fields
  usernameField.clear();
  passwordField.clear();
  // Enter the credentials and click Log in
  usernameField.sendKeys(username);
  passwordField.sendKeys(password);
  loginButton.click();
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Job Listings"))).click();

	//Click Add New
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add New"))).click();
	// Close popup
	try {
		WebElement popupClose = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Close dialog']")));
		popupClose.click(); 
	     } catch (Exception e) {
	     System.out.println("No popup appeared.");
	     }
// Post job
	WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("post-title-0")));
	titleField.sendKeys("Senior Banking");
// first Publish 
WebElement firstPublishButton = wait.until(ExpectedConditions.elementToBeClickable(
    By.xpath("//button[contains(@class,'editor-post-publish-button')]")));
firstPublishButton.click();
//second Publish 
WebElement confirmPublishButton = wait.until(ExpectedConditions.elementToBeClickable(
    By.xpath("//button[contains(@class,'editor-post-publish-button') and text()='Publish']")));
confirmPublishButton.click();

// Wait for confirmation message
WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(
    By.xpath("//div[contains(text(),'Post published')]")));
Assert.assertTrue(confirmation.isDisplayed(), "Job listing was not published.");

}

@AfterClass
public void tearDown() {
	driver.quit();
	    
}
}
