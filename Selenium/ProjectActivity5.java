package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectActivity5 {
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
    public void navigateAndVerifyJobsPage() {
    	//Find navigation bar and locate Jobs menu item
    	WebElement jobsMenuItem=driver.findElement(By.linkText("Jobs"));
    	jobsMenuItem.click();
    	//Read the page title
        String actualTitle=driver.getTitle();
        System.out.println("Page title is: "+ actualTitle);
        //verify
        String expectedTitle="Jobs - Alchemy Jobs";
        Assert.assertEquals(expectedTitle,"Jobs - Alchemy Jobs");  	    	
    }
	 // Teardown function
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}

