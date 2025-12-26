package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}


@FindBy(xpath="//ul[@class='list-inline']//li[@class='dropdown']") WebElement Myaccount_link;
@FindBy(xpath="//a[normalize-space()='Register']") WebElement Register_link;
@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") WebElement Login_link;

public void clickMyAccount()
{
	Myaccount_link.click();
}
public void clickRegitration()
{
	Register_link.click();
}
public void clickLogin()
{
	Login_link.click();
}



}
