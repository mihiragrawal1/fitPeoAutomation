package Utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer{
	
	private int retyCount=0;
	private int maxRetryCount=3;
	
	
	@Override
	public boolean retry(ITestResult result) {
		if(retyCount<maxRetryCount)
		{
			retyCount++;
			return true;
		}
		return false;
	}
	
	

}
