package PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.WaitUtility;

public class RevenueCalculator extends WaitUtility {

	WebDriver driver;

	public RevenueCalculator(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(@class, 'MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary')]/input")
	WebElement slider;

	@FindBy(linkText = "Revenue Calculator")
	WebElement revenueCalculatorTab;

	@FindBy(xpath = "//div[contains(@class, 'MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-sizeSmall css-129j43u')]/input")
	WebElement sliderValueInputFeild;

	@FindBy(xpath = "//div[@class='MuiBox-root css-1eynrej']/label")
	List<WebElement> cardCheckBox;

	@FindBy(css = "div[class='MuiBox-root css-1eynrej']")
	List<WebElement> cards;

	@FindBy(css = "div[class=\"MuiBox-root css-1eynrej\"] p:first-child")
	List<WebElement> cardName;

	@FindBy(css = "div[class='MuiBox-root css-m1khva'] p:last-child")
	WebElement totalRecurringReimbursementForAllPatientsPerMonth;

	public void goToRevenueCalculator() {
		waitForElementVisibility(revenueCalculatorTab);
		revenueCalculatorTab.click();
	}

	public void dragAndDropSlider(int xOffset, int yOffset) {
		Actions a = new Actions(driver);
		waitForElementVisibility(slider);
		a.dragAndDropBy(slider, xOffset, yOffset).perform();
		;
	}

	public int getValueAttributeForSlider() {
		waitForElementVisibility(slider);
		String valueAttribute = slider.getAttribute("value");
		return Integer.parseInt(valueAttribute);
	}

	public void writeInSlidervalueInputFeild(String val) {
		waitForElementVisibility(sliderValueInputFeild);
		sliderValueInputFeild.clear();
		sliderValueInputFeild.sendKeys(val);

	}

	public int getTextFromBelowTextFeild() {
		waitForElementVisibility(sliderValueInputFeild);
		String val = sliderValueInputFeild.getAttribute("value");
		return Integer.parseInt(val);
	}

	public void selectCptCodes(List<String> cptCodes) {
		for (int i = 0; i < cards.size(); i++) {
			// Find the <p> tag inside the current card to get the card name
			WebElement cardNameElement = cards.get(i).findElement(By.tagName("p"));
			String cardName = cardNameElement.getText();
			// Check if the card name matches any of the desired names
			for (String code : cptCodes) {
				if (cardName.equalsIgnoreCase(code)) {
					System.out.println("Card name: " + cardName);
					System.out.println("Matching card found at index: " + i);

					cardCheckBox.get(i).click();
				}
			}

		}

	}

	public String getTotalReimbursement() {
		waitForElementVisibility(totalRecurringReimbursementForAllPatientsPerMonth);
		return totalRecurringReimbursementForAllPatientsPerMonth.getText();
	}

}
