package com.igenico.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.genric.support.*;


public class SelectPaymentProductPage extends LoadableComponent <SelectPaymentProductPage> {

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;

	/**********************************************************************************************
	 ********************************* WebElements of Home Page ***********************************
	 **********************************************************************************************/

	@CacheLookup
	@FindBy(id = "paymentoptionslist")
	WebElement listPaymentOptions;

	@CacheLookup	
	@FindBy(css="li[data-sortablelisttext='Visa Debit'] button")
	WebElement btnVisaDebit;	

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
	public SelectPaymentProductPage(WebDriver driver, String url) {
		appURL = url;
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

		if (isPageLoaded && !(Utils.waitForElement(driver, listPaymentOptions))) {
			Log.fail("Select payment product page did not open up. Site might be down.", driver);
		}

	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);		
		Utils.waitForPageLoad(driver);		

	}// load

	public void clickVisaDebitOption() {
		
		BrowserActions.clickOnButton(btnVisaDebit, driver, "Visa Debit payment option");
		Utils.waitForPageLoad(driver);	
	}

}// Select Payment Page
