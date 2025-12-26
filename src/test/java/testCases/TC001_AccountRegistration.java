package testCases;
import testBase.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTestClass;

public class TC001_AccountRegistration extends BaseTestClass{
	
	@Test(groups={"Regression","Master"})
 	public void testLogin()
	{
		logger.info("***Starting the execution***");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegitration();
		logger.info("***Launched the homepage***");
		AccountRegistrationPage acc=new AccountRegistrationPage(driver);
		logger.info("***Providing user details***");
		acc.setFirstName(randomString());
		acc.setlastName(randomString());
		acc.setEmail(randomString() + "@gmail.com");
		acc.setTelephone(randomNumber());
		String password=randomAlphaNumeric();
		acc.setpassword(password);
		acc.setconfirmPassword(password);
		acc.agreeConditions();
		acc.continueButton();
		String conf_msg=acc.registrationSuccess();
		if(conf_msg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
			logger.info("***Account is registered successfully***");
		}
		else
		{
			logger.error("Test Failed"); 
			logger.debug("Debug logs"); //to debug the error
			Assert.assertFalse(true);
			logger.info("***Account Regiatration Failed***");
		}}
		catch(Exception e)
		{
			System.out.println(e.getMessage()); //exception message 
			Assert.fail();
		}
		
	}
	
	
}
