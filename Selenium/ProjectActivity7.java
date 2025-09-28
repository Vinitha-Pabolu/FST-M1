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
import org.testng.annotations.Test;

public class ProjectActivity7 {
WebDriver driver;
WebDriverWait wait;
    
// Setup function
@BeforeClass
public void setUp() {
    // Initialize Firefox driver
    driver = new FirefoxDriver();
    wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    // Open the page
    driver.get("https://alchemy.hguy.co/jobs");
    }
@Test
public void testCase1() {
    //Locate the navigation menu and click
    WebElement jobMenuItem=driver.findElement(By.linkText("Post a Job"));
    jobMenuItem.click();
    }
@Test
public void testCase2() {
    driver.findElement(By.id("create_account_email")).sendKeys("PV@yahoo.com");
    driver.findElement(By.id("job_title")).sendKeys("Application Developer");
    driver.findElement(By.id("application")).sendKeys("xyz@yahoo.com");
    driver.findElement(By.id("company_name")).sendKeys("ABC");
    WebElement iframe=driver.findElement(By.id("job_description_ifr"));
    driver.switchTo().frame(iframe);
    WebElement descBody=driver.findElement(By.id("tinymce"));
    descBody.clear();
    descBody.sendKeys("Looking for Developer");
    driver.switchTo().defaultContent();
    //click preview
    driver.findElement(By.name("submit_job")).click();
    //click Submit Listing
    driver.findElement(By.id("job_preview_submit_button")).click();
    }
@Test
public void testCase3() {
    //Jobs page
    driver.findElement(By.linkText("Jobs")).click();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement jobListing = wait.until(ExpectedConditions.visibilityOfElementLocated(
    		By.xpath("//h3[contains(text(),'Application Developer')]")));
    Assert.assertTrue(jobListing.isDisplayed(), "Job listing not found!");
    }
@AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}
