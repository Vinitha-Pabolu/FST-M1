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

public class ProjectActivity8 {
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
	        return new Object[][] { 
	            { "root", "pa$$w0rd"}
	        };
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
	  //Verify login
      WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpadminbar")));
      Assert.assertTrue(dashboard.isDisplayed(), "Login failed or Dashboard not visible.");
 }

 @AfterClass
	    public void tearDown() {
	        // Close the browser
	        driver.quit();
	    }
}


