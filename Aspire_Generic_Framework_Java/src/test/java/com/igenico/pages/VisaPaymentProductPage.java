package com.igenico.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.genric.support.*;


public class VisaPaymentProductPage extends LoadableComponent <VisaPaymentProductPage> {

	
	private WebDriver driver;
	private boolean isPageLoaded;

	/**********************************************************************************************
	 ********************************* WebElements of Home Page ***********************************
	 **********************************************************************************************/


	@FindBy(id = "cardNumber")
	WebElement txtCardNumber;
	
	@FindBy(id = "expiryDate")
	WebElement txtCardExpireDate;
	
	@FindBy(id = "cvv")
	WebElement txtCardCvvNumber;
			
	@FindBy(id = "primaryButton")
	WebElement btnPay;	
	
	@FindBy(css = ".paymentoptions>p")
	WebElement txtPaymentStatus;

	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
	 **********************************************************************************************/

	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 * 
	 * @param url
	 *            : UAT URL
	 */
	public VisaPaymentProductPage(WebDriver driver) {		
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// SelectPaymentProduct

	@Override
	protected void isLoaded() {
		
		if (!isPageLoaded) {
			Assert.fail();
		}
		Utils.waitForPageLoad(driver);		

		if (isPageLoaded && !(Utils.waitForElement(driver, txtCardNumber))) {
			Log.fail("Visa Debit payment product page did not open up. Site might be down.", driver);
		}

	}// isLoaded
	
	public void EnterVisaCardNumber(String cardNumber) {		
		BrowserActions.typeOnTextField(txtCardNumber,cardNumber, driver, "Visa Debit card number");
		Utils.waitForPageLoad(driver);	
	}
	
	public void EnterVisaCardExpireDate(String expireDate) {
		
		BrowserActions.typeOnTextFieldWithoutClearing(txtCardExpireDate,expireDate, driver, "Visa Debit card expireDate");
		Utils.waitForPageLoad(driver);	
	}
	
	public void EnterVisaCardCvvNumber(String cvvNumber) {		
		BrowserActions.typeOnTextField(txtCardCvvNumber,cvvNumber, driver, "Visa Debit card cvvNumber");
		Utils.waitForPageLoad(driver);	
	}
	
	public void clickPayButton() {		
		BrowserActions.clickOnButton(btnPay, driver, "Pay button in the Visa Debit card Payment page");
		Utils.waitForPageLoad(driver);	
	}
	
	public void verifyPaymentStatus(){
		String expectedSuccessMsg = "Your payment is successful.";
		String actualSuccessMsg = BrowserActions.getText(driver, txtPaymentStatus, "Payment status message");
		Assert.assertEquals(expectedSuccessMsg,actualSuccessMsg);
	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

}// Select Payment Page
