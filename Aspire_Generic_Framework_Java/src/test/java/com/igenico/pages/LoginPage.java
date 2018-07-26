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
public class LoginPage extends LoadableComponent <LoginPage> {

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	private String username = "lakshmi.nagandla@aspiresys.com";
	private String password = "May@2018";

	/**********************************************************************************************
	 ********************************* WebElements of Home Page ***********************************
	 **********************************************************************************************/


	@FindBy(css = "input[id='loginName']")
	WebElement txtuserName;

	@FindBy(css="input[id='loginPassword']")
	WebElement txtpassword;

	@FindBy(css = "button[ng-click='login()']")
	WebElement btnLogin;

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
	public LoginPage(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(driver, this);
		Utils.waitForPageLoad(driver);
		PageFactory.initElements(finder, this);
		Log.message("Login page Launched successfully!! ", driver);
	}// HomePage

	@Override
	protected void isLoaded() {

		
		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);		

		if (isPageLoaded && !(Utils.waitForElement(driver, btnLogin))) {
			Log.fail("Login Page did not open up. Site might be down.", driver);
		}
		
		//searchProduct("Dress");

	}// isLoaded

	@Override
	protected void load() {

		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);

	}// load

	public void enterUserName(WebDriver driver) {
		Utils.waitForElement(driver, txtuserName);
		if(txtuserName.isDisplayed()) {
			txtuserName.clear();
			txtuserName.sendKeys(username);
			Log.message("Username entered in username field is: " + username );
		}
		
	}
	
	
	public void enterPassword(WebDriver driver) {
		Utils.waitForElement(driver, txtpassword);
		if(txtpassword.isDisplayed()) {
			txtpassword.clear();
			txtpassword.sendKeys(password);
			Log.message("Password entered in password field is: " +password);
		}
		
	}
	
	public void clickLogin(WebDriver driver) {
		Utils.waitForElement(driver, btnLogin);
		if(btnLogin.isEnabled()) {
			btnLogin.click();
			Utils.waitForPageLoad(driver);
			Log.message("Loign button is clicked:");
		}
	}
	
	public Dashboard loginIntoIngenico(WebDriver driver) throws Exception {
		try {
		Utils.waitForPageLoad(driver);
		enterUserName(driver);
		enterPassword(driver);
		clickLogin(driver);
		
		return new Dashboard(driver);
		} catch(Exception e){
			throw new Exception("Unable to login into ingenico application: "+e);
		}
			
		
	}//searchProduct

	
}// HomePage
