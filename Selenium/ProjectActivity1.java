package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectActivity1 {
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
	        //Get the title of page
	        String pageTitle=driver.getTitle();
	        //Print
	        System.out.println("Website Title is: "+pageTitle);
	        // Assert page title
	        Assert.assertEquals(driver.getTitle(), "Alchemy Jobs – Job Board Application");
	    }
	 // Teardown function
	    @AfterClass
	    public void tearDown() {
	        // Close the browser
	        driver.quit();
	    }

	}



