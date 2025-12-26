package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{


	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

@FindBy(xpath="//input[@id='input-firstname']") WebElement firstName;
@FindBy(xpath="//input[@id='input-lastname']") WebElement lastName;
@FindBy(xpath="//input[@id='input-email']") WebElement userEmail;
@FindBy(xpath="//input[@id='input-telephone']") WebElement telephone;
@FindBy(xpath="//input[@id='input-password']") WebElement password;
@FindBy(xpath="//input[@id='input-confirm']") WebElement confirmPassword;
@FindBy(xpath="//input[@name='agree']") WebElement agreeConditions;
@FindBy(xpath="//input[@value='Continue']") WebElement continueButton;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement confMsg;

public void setFirstName(String fname)
{
	firstName.sendKeys(fname);
}
public void setlastName(String lname)
{
	lastName.sendKeys(lname);
}
public void setEmail(String email)
{
	userEmail.sendKeys(email);
}
public void setTelephone(String phone)
{
	telephone.sendKeys(phone);
}
public void setpassword(String pwd)
{
	password.sendKeys(pwd);
}
public void setconfirmPassword(String pwd)
{
	confirmPassword.sendKeys(pwd);
}
public void agreeConditions()
{
	agreeConditions.click();
}
public void continueButton()
{ 
	continueButton.click();
}
public String registrationSuccess()
{
	return confMsg.getText();
}
}
