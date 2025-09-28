package project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ProjectActivity4 {
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
    @Test
    public void testCase1() {
        //Get the heading of page
        WebElement heading=driver.findElement(By.xpath("//h2"));
    	String headingText=heading.getText();
        //Print
        System.out.println("Page Second Heading is: "+headingText);
       Assert.assertEquals(headingText, "Quia quis non");
    }
 // Teardown function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}

