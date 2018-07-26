package com.igenico.testscripts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.genric.support.BaseTest;
import com.genric.support.DataProviderUtils;
import com.genric.support.EmailReport;
import com.genric.support.EnvironmentPropertiesReader;
import com.genric.support.Log;
import com.genric.support.WebDriverFactory;
import com.igenico.checkout.CheckOutRequest;
import com.igenico.pages.APIKeysPage;
import com.igenico.pages.Dashboard;
import com.igenico.pages.LoginPage;
import com.igenico.pages.SelectPaymentProductPage;
import com.igenico.pages.VisaPaymentProductPage;
import com.relevantcodes.extentreports.ExtentTest;


@Listeners(EmailReport.class)
public class IngenicoPayment_CheckOutService extends BaseTest {

	String notes = null;
	String testLinkResult = null;
	protected String status = null;
	EnvironmentPropertiesReader environmentPropertiesReader ;
	private String workbookName = "testdata\\data\\Regression_PoC.xls";
	private String sheetName = "Demo";

	@Test(description = "Making payment using Ingenico Hosted Checkout service for registered customers", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TcHostedCheckout001(String browser) throws Exception {
		
		environmentPropertiesReader = new EnvironmentPropertiesReader();
		//** Loading the test data from excel using the test case id */
		HashMap<String, String> testData = initTestData(workbookName, sheetName);	
		String Description = "Making payment using Ingenico Hosted Checkout service for registered customers";
		String cardNumber = testData.get("CardNumber");
		String cardExpireDate = testData.get("CardExpireDate");
		String cvvNumber = testData.get("CvvNumber");
		ExtentTest extentedReport = Log.testCaseInfo(Description +"[" + browser + " ]", "Aspire Test Automation" , "Ingenico", "Payment Service");
		final WebDriver driver = WebDriverFactory.get(browser);	
	    String site = "https://sandbox.account.ingenico.com/#/login";
	    
		try {
		
	    LoginPage login = new LoginPage(driver,site).get();
	    Log.messageWithExtentScreenshot("Step 1. Ingenico login page loaded successfully!! ", driver, extentedReport, true);
		Dashboard dashboard = login.loginIntoIngenico(driver);
		Log.messageWithExtentScreenshot("Step 2. Dashboard page displayed after login into ingenico with valid credentials!!", driver, extentedReport, true);
		APIKeysPage apikeys = dashboard.clickMyAPIlink(driver);
		Log.messageWithExtentScreenshot("Step 3. Able to view APIKey: "+apikeys.getRequestAPI(driver)+" SeceretKey: "+apikeys.getSecretAPI(driver)+" successfully!! ", driver, extentedReport, true);
				
		//Create object for checkout service and call
		CheckOutRequest checkoutRequest = new CheckOutRequest(extentedReport);
		checkoutRequest.callCheckoutService(extentedReport);		
		String partialRedirectURL = checkoutRequest.getValueFromResponse("partialRedirectUrl");
		site = environmentPropertiesReader.getProperty("webSite") + partialRedirectURL;				
		
		// Get the web driver instance
			
		SelectPaymentProductPage selectPayment = new SelectPaymentProductPage(driver, site).get();
		Log.messageWithExtentScreenshot("Step 4. Navigated to Select Payment product Page!", driver, extentedReport, true);

		selectPayment.clickVisaDebitOption();
		Log.messageWithExtentScreenshot("Step 5. Selected Visa Debit option from the pyament product page", driver, extentedReport, true);
			 
		VisaPaymentProductPage visaPaymentPage =  new VisaPaymentProductPage(driver);
			
		visaPaymentPage.EnterVisaCardNumber(cardNumber);
		Log.messageExtentReport("Step 6. Entered Visa Card Number details", extentedReport);
			
		visaPaymentPage.EnterVisaCardExpireDate(cardExpireDate);
		Log.messageExtentReport("Step 7. Entered Visa Card ExpireDate details", extentedReport);
			
		visaPaymentPage.EnterVisaCardCvvNumber(cvvNumber);
		Log.messageWithExtentScreenshot("Step 8. Entered Visa Card cvvNumber details",driver, extentedReport, true);
			
		visaPaymentPage.clickPayButton();
		Log.messageExtentReport("Step 9. Navigated to Confirm Payment Page!", extentedReport);
			
		visaPaymentPage.verifyPaymentStatus();
		Log.messageWithExtentScreenshot("Step 10. Verified Payment Confirmation message ", driver, extentedReport, true);
			
		Log.testCaseResultExtentReport(extentedReport);

		}// try
		catch (Exception e) {
			Log.exception(e, driver);
		}// catch
		finally {
			Log.endTestCase();
			driver.quit();
		}// finally

	}// tcTAFrameworkJAVADemo01
	
}// AspireTA_Java_Framework_Demo