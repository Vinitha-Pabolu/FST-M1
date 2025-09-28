package project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ProjectActivity2 {
WebDriver driver;
    
    // Setup function
    @BeforeClass
    public void setUp() {
        // Initialize Firefox driver
        driver = new FirefoxDriver();
        // Open the page
        driver.get("https://alchemy.hguy.co/jobs");
    }
    // Test function
    @Test(priority = 1)
    public void testCase1() {
        //Get the heading of page
        WebElement heading=driver.findElement(By.xpath("//h1[contains(text(),'Welcome to Alchemy Jobs')]"));
    	String headingText=heading.getText();
        //Print
        System.out.println("Page Heading is: "+headingText);
       Assert.assertEquals(headingText, "Welcome to Alchemy Jobs");
    }
 // Teardown function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}

