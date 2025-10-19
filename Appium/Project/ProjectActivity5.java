package Project;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ProjectActivity5 {
    // Driver Declaration
    AndroidDriver driver;
    WebDriverWait wait;
    
    static final PointerInput pointer = new PointerInput(Kind.TOUCH,"pointer");

    // Set up method
    @BeforeTest
    public void setUp() throws MalformedURLException, URISyntaxException {
    	 	
    	UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();
     // Set the Appium server URL
        URL serverURL = new URI("http://localhost:4723").toURL();
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://training-support.net/webelements");
        }
    
 // Test method
    @Test (priority = 1)
    public void validCred() {
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='WebElements']")));
        
    	Dimension dims = driver.manage().window().getSize();
    	Point start = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .90));
    	Point end = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .50));

        Sequence swipe = new Sequence(pointer, 1)
                .addAction(pointer.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(pointer.createPointerDown(LEFT.asArg()))
                .addAction(pointer.createPointerMove(ofMillis(300), viewport(), end.getX(), end.getY()))
                .addAction(pointer.createPointerUp(LEFT.asArg()));
        
        driver.perform(Arrays.asList(swipe));
    	
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Login Form']"))).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']"))).sendKeys("admin");
        
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys("password");
        
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Submit']")).click();
        
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AppiumBy.xpath("//android.widget.TextView[@text='Login Success!']")));
        
        String message = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Login Success!']")).getText();
        
        Assert.assertEquals(message, "Login Success!");          
       		
    }
    
    @Test (priority = 2)
    public void invalidCred() {
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='WebElements']")));
        
    	Dimension dims = driver.manage().window().getSize();
    	Point start = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .90));
    	Point end = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .50));

        Sequence swipe = new Sequence(pointer, 1)
                .addAction(pointer.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(pointer.createPointerDown(LEFT.asArg()))
                .addAction(pointer.createPointerMove(ofMillis(300), viewport(), end.getX(), end.getY()))
                .addAction(pointer.createPointerUp(LEFT.asArg()));
        
        driver.perform(Arrays.asList(swipe));
    	
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Login Form']"))).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']"))).sendKeys("adm");
        
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys("password");
        
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Submit']")).click();
        
        
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='subheading']")));
        String actualMessage = message.getText();

        
        Assert.assertEquals(actualMessage, "Invalid credentials");
    }     
       	
 
 
    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}
