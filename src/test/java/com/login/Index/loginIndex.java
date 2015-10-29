package com.login.Index;

import init.SeleniumInit;

import org.testng.annotations.Test;

public class loginIndex extends SeleniumInit

{

	@Test(priority=1)
	public void login_tarpan_credential() throws Exception
	{
		log("Step 1 : Open url:<a>"+testUrl+"</a>");
		log("Verify that Gmail login page is open or not.");
		
		l_verify = l_index_page.loginPage();
		if(l_verify.loginPage_verify())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
	//		Common.makepassScreenshot(driver,"valid_pass");
		}
		else
		{
			log("Fail");
		}
		
		
		l_verify = l_index_page.loginTarpan(userName, pass);
	
		if(l_verify.login_verify())
		{
			log("<Strong><font color=#008000>Pass</font></strong>");
	//		Common.makepassScreenshot(driver,"valid_pass");
		}
		else
		{
			log("Fail");
		}
		
	
	}
	
}
