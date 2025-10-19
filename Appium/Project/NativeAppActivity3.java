package Project;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import activities.ActionsBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class NativeAppActivity3 {
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
    public void completeTask() {
    	//Mark first two tasks as complete
        List<WebElement> checkboxes = driver.findElements(AppiumBy.id(
        		"com.app.todolist:id/cb_task_done"));
     
        checkboxes.get(0).click();
        checkboxes.get(1).click();
        //Long press
        WebElement firstTask = driver.findElement(AppiumBy.xpath(
    			"//android.widget.TextView[@resource-id='com.app.todolist:id/tv_exlv_task_name' and @text='Complete Activity3 with priority Low']"));
        Point location = firstTask.getLocation();
        Dimension size = firstTask.getSize();
        int centerX = location.getX() + size.getWidth() / 2;
        int centerY = location.getY() + size.getHeight() / 2;
        Point taskPoint = new Point(centerX, centerY);
        // Long press using ActionsBase helper
        ActionsBase.doLongPress(driver, taskPoint);
        //Click edit task
        wait.until(ExpectedConditions.visibilityOfElementLocated(
 	            AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']"))).click();
        //Edit progress bar
        Dimension dims = driver.manage().window().getSize();
		// Set the start and end points
    	Point start = new Point((int) (dims.getWidth() * .28), (int) (dims.getHeight() * .54));
		Point end = new Point((int) (dims.getWidth() * .60), (int) (dims.getHeight() * .54));
		// Perform swipe
		ActionsBase.doSwipe(driver, start, end, 2000);
             
        driver.findElement(AppiumBy.id("bt_new_task_ok")).click(); 
        
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView[@content-desc='More options']"))).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/title\" and @text='Completed tasks']"))).click();
        
        List<WebElement> checkboxcount = driver.findElements(AppiumBy.className("android.widget.CheckBox"));
        
        Assert.assertEquals(2, checkboxcount.size());
                
    }
    
    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }

     
    }
    


