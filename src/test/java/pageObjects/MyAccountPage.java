package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
		
}
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement myacctHeader;

	@FindBy(xpath="//ul[@class='list-inline']//li[@class='dropdown']") WebElement Myaccount_link;


@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']") WebElement Logout_Link; //refer step 6.4S in demo frmwrk word doc
	
	
	public boolean isMyAccountExists()
	{
		return myacctHeader.isDisplayed();
	}
	public void clickMyAccount()
	{
		Myaccount_link.click();
	}

	public void click_Logout()
	{
			Logout_Link.click();
	}
}
