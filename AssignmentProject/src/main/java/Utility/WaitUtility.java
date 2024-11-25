package Utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

	WebDriver driver;

	public WaitUtility(WebDriver driver) {
		this.driver = driver;

	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public void waitForVisibilityOfElementLocated(By ele) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}

	public void waitForElementVisibility(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

}
