package com.login.Verification;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import init.AbstractPage;
import init.Common;

public class loginVerification extends AbstractPage {

	public loginVerification(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath=".//*[@class='banner']")
	private WebElement login_page_banner;
	
	@FindBy(xpath=".//*[@class='nU n1']")
	private WebElement index;
	
	public boolean loginPage_verify()
	{
		
		Common.highlightElement(driver, login_page_banner);
		return login_page_banner.isDisplayed();
		
		
	}
	
	public boolean login_verify()
	{
		Common.highlightElement(driver, index);
		return index.isDisplayed();
		
		
	}

}
