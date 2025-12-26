package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestClass {
//This class will contain common methods used by multiple test cases
	
public  WebDriver driver;
public Logger logger;
public Properties prop;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters("browser")
	public void setup(String browser) throws IOException
	{
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		prop=new Properties();  //creating object for Properties class
		prop.load(file); //loading properties file
		
		logger=LogManager.getLogger(this.getClass()); // this.getclass() refers to the current class which is being executed
		
		//LogManager.getLogger() will get the logs of class executed and loads the logs to xml file
		String env=prop.getProperty("execution_env");
		if(env.equalsIgnoreCase("local"))
		{
		switch(browser.toLowerCase())
		{
		case "chrome" :
		WebDriverManager.chromedriver().setup();	
		driver= new ChromeDriver();
		break;
		case "firefox":
		WebDriverManager.firefoxdriver().setup();
	    driver=new FirefoxDriver();
		break;
		case "edge": 
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		break;
		default :throw new IllegalArgumentException("Invalid browser" + browser);
		//it will exit out of the method as browser itself invalid
		}
		}
		//env
		if(env.equals("remote"))
		{
			   switch(browser.toLowerCase())
					{
				case "chrome": 
					ChromeOptions ch_options = new ChromeOptions();
			        driver = new RemoteWebDriver(new URL("http://localhost:4444"),ch_options);
				    break;
				case "edge": 
					EdgeOptions ed_options = new EdgeOptions();
     		        driver = new RemoteWebDriver(new URL("http://localhost:4444"),ed_options);
	      			break;
				/*case "firefox": 
					FirefoxOptions fi_options = new FirefoxOptions();
		            driver = new RemoteWebDriver(new URL("http://localhost:4444"),fi_options);
				    break;*/
				default :
					throw new IllegalArgumentException("Invalid browser" + browser);
				//it will exit out of the method as browser itself invalid
					}
			
			//since all web browser drivers are comes under Remote webdriver class, we are creating object as common for all browser drivers
			
		}
		driver.manage().deleteAllCookies();
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(prop.getProperty("appURL")); //reading appURL from properties file
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
				
	}
	@AfterClass (groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		if (driver != null) {
	        driver.quit();
	    }
	}
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(6);
		return generatedString;
	}
	public String randomNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	public String randomAlphaNumeric()
	{
		//String generatedString=RandomStringUtils.randomAlphabetic(6);
		String generatedAlphaNumeric=RandomStringUtils.randomAlphanumeric(10);
		return generatedAlphaNumeric;
	}
	public String captureScreenshot(String testName) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//to save timestamp of failure
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sourcefile=ts.getScreenshotAs(OutputType.FILE); //getting screenshot in File format and return as File
		String targetfilepath=(System.getProperty("user.dir")+"\\screenshots\\"+testName + "_" + timeStamp + ".png");
		//File targetfile=new File("C:\\Users\\BHARATHI\\eclipse-workspace\\SeleniumLearning\\screenshots\\fullpage.png");
		File targetfile=new File(targetfilepath);
		sourcefile.renameTo(targetfile);
		return targetfilepath;
		
	}
}
