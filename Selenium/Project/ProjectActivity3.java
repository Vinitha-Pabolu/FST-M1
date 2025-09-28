package project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectActivity3 {
WebDriver driver;
    
    // Setup function
    @BeforeClass
    public void setUp() {
        // Initialize Firefox driver
        driver = new FirefoxDriver();
        // Open the page
        driver.get("https://alchemy.hguy.co/jobs");
    }
    @Test
    public void testCase1() {
    	WebElement headerImage=driver.findElement(By.xpath("//img[contains(@class,'wp-post-image')]"));
    	//Get URL
    	String imageUrl=headerImage.getAttribute("src");
    	//Print
    	System.out.println("Header Image URL is: "+imageUrl);
		Assert.assertTrue(headerImage.isDisplayed(), " Image is not visible");
    }
 // Teardown function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}
