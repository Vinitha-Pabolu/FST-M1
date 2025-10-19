package activities;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import io.appium.java_client.AppiumDriver;
 

public class ActionsBase {
	static PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

	    public static void doSwipe(AppiumDriver driver, Point start, Point end, int duration) {
	        Sequence swipe = new Sequence(finger, 1)
	            .addAction(finger.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
	            .addAction(finger.createPointerDown(LEFT.asArg()))
	            .addAction(finger.createPointerMove(ofMillis(duration), viewport(), end.getX(), end.getY()))
	            .addAction(finger.createPointerUp(LEFT.asArg()));
	        driver.perform(Arrays.asList(swipe));
	    }
	 // Create the long press action function
	    public static void doLongPress(AppiumDriver driver, Point start) {
	        // Create s sequence of actions
	        Sequence longPress = new Sequence(finger, 1)
	                .addAction(finger.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
	                .addAction(finger.createPointerDown(LEFT.asArg()))
	                .addAction(new Pause(finger, Duration.ofMillis(600)))
	                .addAction(finger.createPointerUp(LEFT.asArg()));

	        // Perform the actions
	        driver.perform(Arrays.asList(longPress));
	    }
	 // Create the long press action function
	    public static void doTap(AppiumDriver driver, Point start) {
	        // Create s sequence of actions
	        Sequence tap = new Sequence(finger, 1)
	                .addAction(finger.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
	                .addAction(finger.createPointerDown(LEFT.asArg()))
	                .addAction(new Pause(finger, Duration.ofMillis(80)))
	                .addAction(finger.createPointerUp(LEFT.asArg()));

	        // Perform the actions
	        driver.perform(Arrays.asList(tap));
	    }
	}


