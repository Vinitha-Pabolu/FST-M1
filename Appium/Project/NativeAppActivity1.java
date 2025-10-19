package Project;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class NativeAppActivity1 {
	AndroidDriver driver;
	WebDriverWait wait;

    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {
        // Desired Capabilities
    	File apkFile= new File("src/test/resources/ts-todo-list-v1.apk");
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setApp(apkFile.getAbsolutePath());
        options.noReset();

        // Server Address
        URL serverURL = new URI("http://localhost:4723").toURL();

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
 // Test method
 	@Test
 	public void addTask() {
 		 // Define tasks and priorities
        String[][] tasks = {
            {"Complete Activity1 with priority High"},
            {"Complete Activity2 with priority Medium"},
            {"Complete Activity3 with priority Low"}
        };

        for (String[] task : tasks) {
            String title = task[0];

 	// Wait for the "Add Task" button and click it
 	    wait.until(ExpectedConditions.visibilityOfElementLocated(
 	            AppiumBy.xpath("//android.widget.ImageButton[@resource-id='com.app.todolist:id/fab_new_task']"))).click();

        // Input Task Title
        WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("com.app.todolist:id/et_new_task_name"))); 

        titleInput.click();  
        titleInput.clear();  
        titleInput.sendKeys(title);  

        // Save Task
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.app.todolist:id/bt_new_task_ok"))); 
        saveBtn.click();

        
    }

    // Validate all tasks were added
    List<WebElement> taskTitles = driver.findElements(AppiumBy.id("com.app.todolist:id/tv_exlv_task_name")); // 🔁 Replace ID

    Assert.assertEquals(taskTitles.size(), 3, "Expected 3 tasks in the list");

    Assert.assertEquals(taskTitles.get(0).getText(), "Complete Activity1 with priority High");
    Assert.assertEquals(taskTitles.get(1).getText(), "Complete Activity2 with priority Medium");
    Assert.assertEquals(taskTitles.get(2).getText(), "Complete Activity3 with priority Low");
}
 	   

    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }

}
