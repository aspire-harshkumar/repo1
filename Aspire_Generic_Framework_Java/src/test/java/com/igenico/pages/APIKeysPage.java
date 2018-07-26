package com.igenico.pages;

import java.util.List;

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
public class APIKeysPage extends LoadableComponent <APIKeysPage> {

	
	private WebDriver driver;
	private boolean isPageLoaded;
	public static String APIKey = null;
	public static String SeceretKey= null;
	


	/**********************************************************************************************
	 ********************************* WebElements of Home Page ***********************************
	 **********************************************************************************************/


	@FindBy(css = "table[ng-repeat='apikey in GC.keys'] div[class*='panel-key']")
	List<WebElement> lblAPIKeys;
	
	@FindBy(css="button[ng-click='requestNewApiKey()']")
	WebElement btnRequestAPI;
	
	@FindBy(css="input[type='password'][id='ownpassword']")
	List<WebElement> btnPassword;
	
	
	@FindBy(css=".modal-footer span[translate*='apiKeyPage']")
	List<WebElement> mdlBtnRequestApI;
	
	

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
	public APIKeysPage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		Log.message("API Keys page Launched successfully!! ", driver);
	}// HomePage

	@Override
	protected void isLoaded() {

		
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);		

		if (isPageLoaded && !(Utils.waitForElement(driver, btnRequestAPI))) {
			Log.fail("Unable to navigate to Dashboard page. Site might be down.", driver);
		}
		
		//searchProduct("Dress");

	}// isLoaded

	@Override
	protected void load() {

		isPageLoaded = true;
		Utils.waitForPageLoad(driver);

	}// load

	public void clickRequestAPI(WebDriver driver) {
		Utils.waitForElement(driver, btnRequestAPI);
		if(btnRequestAPI.isEnabled()) {
			btnRequestAPI.click();
			Log.message("RequestAPI button is clicked:");
		}
	}
	
	public void enterPassword() throws Exception {
		try {
			Utils.waitForPageLoad(driver);
			btnPassword.get(0).click();
			btnPassword.get(0).sendKeys("");
			
		} catch(Exception e){
			throw new Exception("Unable to identify password field: "+e);
		}
	}
	
	public void clickMdlRequestAPI(WebDriver driver) throws Exception {
		try{
		
			mdlBtnRequestApI.get(0).click();
			Utils.waitForPageLoad(driver);
			Log.message("RequestAPI button is clicked:");
		
	}catch(Exception e){
		throw new Exception("Unable to identify password field: "+e);
	}
	}
		
		public String getRequestAPI(WebDriver driver) {
			Utils.waitForPageLoad(driver);
			APIKey= lblAPIKeys.get(0).getText();
			return APIKey;
			
		}
		
		public String getSecretAPI(WebDriver driver) {
			Utils.waitForPageLoad(driver);
			SeceretKey= lblAPIKeys.get(1).getText();
			return SeceretKey;
			
		}
		
	}
	
// APIKeys Page
