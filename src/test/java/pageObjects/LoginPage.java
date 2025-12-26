package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}

@FindBy(xpath="//input[@id='input-email']") WebElement email;
@FindBy(xpath="//input[@id='input-password']") WebElement password;
@FindBy(xpath="//input[@value='Login']") WebElement loginBtn;

public void enter_Email(String mail)
{
	email.sendKeys(mail);
}
public void enter_Password(String pwd)
{
	password.sendKeys(pwd);
}
public void click_loginBtn()
{
	loginBtn.click();
}

}
