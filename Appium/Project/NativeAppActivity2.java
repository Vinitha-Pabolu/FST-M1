package Project;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

public class NativeAppActivity2 {
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
	    public void editTask() {
	    	WebElement firstTask = driver.findElement(AppiumBy.xpath(
	    			"//android.widget.TextView[@resource-id='com.app.todolist:id/tv_exlv_task_name' and @text='Complete Activity1 with priority High']"));
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
	     // Click the deadline
	        WebElement deadlineField = driver.findElement(AppiumBy.id("com.app.todolist:id/tv_todo_list_deadline")); 
	        deadlineField.click();  
	     // Calculate next Saturday
	        LocalDate today = LocalDate.now();
	        int daysUntilSaturday = DayOfWeek.SATURDAY.getValue() - today.getDayOfWeek().getValue();
	        if (daysUntilSaturday <= 0) {
	            daysUntilSaturday += 7;
	        }
	        LocalDate nextSaturday = today.plusDays(daysUntilSaturday);
	     // Format as '25 October 2025' 
	        String formattedDate = nextSaturday.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));

	        driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='" + formattedDate + "']")).click();

	        // Click OK button to confirm date
	        driver.findElement(AppiumBy.id("com.app.todolist:id/bt_deadline_ok")).click();
	        driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();
	        //Assert
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        String expectedDeadline = "Deadline: " + nextSaturday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

	        WebElement deadlineElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            AppiumBy.xpath("//android.widget.TextView[@resource-id='com.app.todolist:id/tv_exlv_task_deadline' and @text='"+ expectedDeadline +"']")));
	        String deadlineText = deadlineElement.getText();
	        Assert.assertEquals(deadlineText, expectedDeadline);
	        	        
	    			
	    }
	 // Tear down method
	    @AfterClass
	    public void tearDown() {
	        // Close the app
	        driver.quit();
	    }

	     
	    }



