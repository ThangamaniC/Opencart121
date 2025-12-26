package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;
import utilities.DataProviders;

public class TC003_LoginDatadrivenTest extends BaseTestClass{

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups={"datadriven","Master"}) 
	//dataProvider is present in DataProviders.class hence we are specifying class name along with dataprovder name
	public void test_Login(String email,String password,String result)
	{
		logger.info("**Test execution started");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.enter_Email(email);
		lp.enter_Password(password);
		lp.click_loginBtn();
		MyAccountPage myacct=new MyAccountPage(driver);		
		boolean acct_status=myacct.isMyAccountExists();
		
		/*Data is valid   -- Login success test pass --Valid
		  					 Login failed  test fail  --invalid
		  					 
		  Data is Invalid  -- Login success test fail -- invalid 
		  					  Login failed test pass --valid
		 */
		if(result.equalsIgnoreCase("Valid"))
		{
			if(acct_status==true)
			{
				Assert.assertTrue(true);
				myacct.clickMyAccount();
				myacct.click_Logout();
			}
			else
			{
				Assert.assertFalse(true);
			}
		}
		if(result.equalsIgnoreCase("Invalid"))
		{
			if(acct_status==true)
			{
				Assert.assertFalse(true);
				myacct.clickMyAccount();
				myacct.click_Logout();
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			Assert.fail();
		}
		logger.info("**Test execution completed");
	}
}
