package com.login.IndexPage;

import init.AbstractPage;
import init.Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.login.Verification.loginVerification;

public class loginIndexpage extends AbstractPage
{

	public loginIndexpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath=".//*[@id='Email']")
	private WebElement email_id_field;
	
	@FindBy(xpath=".//*[@id='next']")
	private WebElement next_btn;
	
	@FindBy(xpath=".//*[@id='Passwd']")
	private WebElement paswd_field;
	
	@FindBy(xpath=".//*[@id='signIn']")
	private WebElement signin_btn;
	
	
	public loginVerification loginPage()
	
	{
				
		return new loginVerification(driver);
		
	}
	
	public loginVerification loginTarpan(String email_id, String passwd)
	
	{
		
		Common.clickOn(driver, email_id_field);
		log("Step : Enter email id."+email_id);
		Common.type(email_id_field, email_id);
		System.out.println(email_id);
		log("Step : Click on Next button.");
		Common.clickOn(driver, next_btn);
		
		Common.clickOn(driver, paswd_field);
		log("Step : Enter password."+passwd);
		Common.type(paswd_field, passwd);
		log("Step : Click on Sign in button.");
		
		Common.clickOn(driver, signin_btn);
		/*jhcvjhchscvjhsgcvyjhsdygcsdyug*/		

		
		return new loginVerification(driver);
		
	}
	
}
