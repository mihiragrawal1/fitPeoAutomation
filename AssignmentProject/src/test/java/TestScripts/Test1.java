package TestScripts;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseComponent.BaseClass;
import PageObjects.RevenueCalculator;
import Utility.RetryAnalyzer;

public class Test1 extends BaseClass {

	RevenueCalculator rc;
	List<String> cptCodes;
	String expectedTotalReimbursement;
	int xOffset;
	int yOffset;
	String setSlider;
	int expectedVal;

	@BeforeMethod
	public void setup() {
		rc = new RevenueCalculator(driver);
		cptCodes = new ArrayList<String>();
		cptCodes.add("CPT-99091");
		cptCodes.add("CPT-99453");
		cptCodes.add("CPT-99454");
		cptCodes.add("CPT-99474");
		expectedTotalReimbursement = "$110700";
		xOffset = 93;
		yOffset = 0;
		setSlider = "560";
		expectedVal = 820;

	}

	// added retyanalyzer to retry failed tset case automatically
	@Test(retryAnalyzer = RetryAnalyzer.class, priority = 1, description = "task1 and 2-to set slider to 820 and then to 560 by entering value to input feild and validate")
	public void go_to_revenueCalculator_AdjustSlider() throws InterruptedException {
		// task one-set slider to value 820
		rc.goToRevenueCalculator(); // navigates to revenue calculator page
		rc.dragAndDropSlider(93, 0); // drag the slider to required value
		int textFeildValue = rc.getTextFromBelowTextFeild(); // extract value from input feild
		Assert.assertEquals(textFeildValue, expectedVal); // testng assertion to validate value

		// second task to write in inputbox value-560 and that value should be reflected on slider

		rc.writeInSlidervalueInputFeild(setSlider); // write value into input box below slider
		int CurrentValueAttriute = rc.getValueAttributeForSlider(); // get value attribute of slider to get slider value
		Assert.assertEquals(CurrentValueAttriute, Integer.parseInt(setSlider)); // assertion to validate slider value

	}

	@Test(retryAnalyzer = RetryAnalyzer.class, priority = 2, description = "task 3- to select the cpt codes and get total reimbursement value and validate")
	public void task_three_toSelectCardsAndValidateAmount() {
		rc.goToRevenueCalculator();
		rc.dragAndDropSlider(xOffset, yOffset);
		int textFeildValue = rc.getTextFromBelowTextFeild();
		System.out.println("value from textfeild is -> " + rc.getTextFromBelowTextFeild());
		Assert.assertEquals(textFeildValue, expectedVal);
		// task 3 select cpt code
		rc.selectCptCodes(cptCodes); // select the given cpt-Codes
		String TotalReimbursement = rc.getTotalReimbursement(); // get the Total-Reimbursement
		Assert.assertEquals(TotalReimbursement, expectedTotalReimbursement); // assertion to validate Total-Reimbursement

																				
	}

}
