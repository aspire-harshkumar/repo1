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


/**
 * 
 * @author lakshmi.nagandla
 * 
 */
public class Dashboard extends LoadableComponent <Dashboard> {


	private WebDriver driver;
	private boolean isPageLoaded;


	/**********************************************************************************************
	 ********************************* WebElements of Home Page ***********************************
	 **********************************************************************************************/


	@FindBy(css = "a[href*='apikey']")
	WebElement lnkMyAPIKey;

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
	public Dashboard(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		PageFactory.initElements(driver, this);
		Utils.waitForPageLoad(driver);
		Log.message("Dashboard page Launched successfully!! ", driver);
	}// HomePage

	@Override
	protected void isLoaded() {

		
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);		

		if (isPageLoaded && !(Utils.waitForElement(driver, lnkMyAPIKey))) {
			Log.fail("Unable to navigate to Dashboard page. Site might be down.", driver);
		}
		
		//searchProduct("Dress");

	}// isLoaded

	@Override
	protected void load() {

		isPageLoaded = true;
		Utils.waitForPageLoad(driver);

	}// load

	public APIKeysPage clickMyAPIlink(WebDriver driver) {
		Utils.waitForElement(driver, lnkMyAPIKey);
		if(lnkMyAPIKey.isEnabled()) {
			lnkMyAPIKey.click();
			Log.message("MyAPIKey link is clicked:");
			Utils.waitForPageLoad(driver);
			return new APIKeysPage(driver);
		}
		return new APIKeysPage(driver);
	}
	
}// DashboardPage
