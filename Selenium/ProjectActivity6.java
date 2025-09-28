package project;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ProjectActivity6 {
	 WebDriver driver; 
	 WebDriverWait wait;
	    // Setup function
	    @BeforeClass
	    public void setUp() {
	        // Initialize Firefox driver
	        driver = new FirefoxDriver();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        // Open the page
	        driver.get("https://alchemy.hguy.co/jobs");
	    }
@Test
public void applyToBankingJob() {
     //Open the page
     driver.get("https://alchemy.hguy.co/jobs");
     // Click on Jobs
     driver.findElement(By.linkText("Jobs")).click();
     //Search
     WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_keywords")));
     searchBox.sendKeys("Banking");
     // Click Search button
     driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();
     // wait for listings to show
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='job_listings']/li")));
     // click on first job
     List<WebElement> jobListings = driver.findElements(By.xpath("//ul[@class='job_listings']/li"));
     if (!jobListings.isEmpty()) {
    	 jobListings.get(0).click();
    	 } else {
    		 System.out.println("No Banking job listings found.");
    		 return;
        }
     // click Apply
     WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("application_button")));
     applyButton.click();
     // Print
     WebElement emailApply = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("job_application_email")));
     String email = emailApply.getText();
     System.out.println("Application Email: " + email);
    }

@AfterClass
    public void tearDown() {
        // Close broswer
        driver.quit();
    }
}
