package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC002_LoginTest extends BaseTestClass{
	
	
	@Test(groups={"Sanity","Master"})
	public void verifyLogin()
	{
		logger.info("***Starting LoginTest tet case execution ***");
		MyAccountPage myacct=new MyAccountPage(driver);
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.enter_Email(prop.getProperty("email"));
		lp.enter_Password(prop.getProperty("password"));
		lp.click_loginBtn();
		
		boolean acct_status=myacct.isMyAccountExists();
		if(acct_status==true)
		{
			System.out.println("Logged in Successfully");
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}}
		catch(Exception e)
		
		{
			Assert.fail();
		}
		
		finally {
		myacct.clickMyAccount();
		myacct.click_Logout();
		logger.info("***User Logged Out***");
		}
		
	}
}
