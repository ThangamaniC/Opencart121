package utilities;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseTestClass;

public class ExtentReportUtlity implements ITestListener
{
public ExtentSparkReporter sparkreporter;
public ExtentReports exreports;
public ExtentTest extest;
String reportname;

public void onStart(ITestContext context)  //creating template for report as part of onStart
//ITestContext --this will get which test method is being executed 
{
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	 reportname="Test-Report" + timeStamp + ".html";
	//sparkreporter =new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/" + reportname);
	 sparkreporter =new ExtentSparkReporter(".//reports//" + reportname);
// sparkreporter.config()context.setDocumentTitle("Automation Report");
sparkreporter.config().setReportName("Automation Test Report");
sparkreporter.config().setDocumentTitle("Test Execution Report");
exreports=new ExtentReports();
exreports.attachReporter(sparkreporter);
exreports.setSystemInfo("Environment", "QA");
exreports.setSystemInfo("UserName", System.getProperty("user.name"));
//we can get OS,browser details from the parameters passed under parameters tag in testng.xml file 
String os=context.getCurrentXmlTest().getParameter("os");
exreports.setSystemInfo("OS",os);
String browser=context.getCurrentXmlTest().getParameter("Browser");
exreports.setSystemInfo("Browser", browser);

List<String> groups=context.getCurrentXmlTest().getIncludedGroups();
if(!groups.isEmpty())
{
	exreports.setSystemInfo("Groups", groups.toString());
	}

} 

		public void onTestSuccess(ITestResult result) {
			 // extest = exreports.createTest(result.getClass().getName()); //to get name of test class exeucted and result creates a test entry in the report with that classname 
			  extest = exreports.createTest(result.getMethod().getMethodName());//to get name of test method executed and result creates a test entry in the report with that methodname 
			  extest.log(Status.PASS, "Test Passed " + result.getMethod().getMethodName());
		  }

		
		   
		  public void onTestFailure(ITestResult result) {
			  //extest = exreports.createTest(result.getClass().getName()); //to get name of test class exeucted and result creates a test entry in the report with that classname 
			  extest = exreports.createTest(result.getMethod().getMethodName());//to get name of test method executed  and result creates a test entry in the report with that methodname
			  extest.log(Status.FAIL, "Test Failed " +result.getMethod().getMethodName());
		        extest.fail(result.getThrowable());
		       
		        try {
		        //adding code for capturing screenshot of failure 
		        BaseTestClass base=new BaseTestClass(); 
		        String imgPath=base.captureScreenshot(result.getName());
		        //getting name from result object and passing to capturing screenshot method which is in basetestclass
		        //this capturescreenshot is accessed using object of BaseTestclass and store the imgpath returned from capture screenshot method
		        extest.addScreenCaptureFromPath(imgPath);//adding captured image to test
		        }
		        catch(Exception e)
		        {
		        System.out.println(e.getMessage());	
		        }
		}

		
		   
		  public void onTestSkipped(ITestResult result) {
			  extest = exreports.createTest(result.getMethod().getMethodName());
			  extest.log(Status.SKIP, "Test Skipped " + result.getMethod().getMethodName());
		  }
		 
		  public void onFinish(ITestContext context) {
			  exreports.flush();
			  //The flush() method in Extent Reports is a crucial command used to write or ..
			  //...update all the test information collected during the execution to the destination report file
			  
			  
			  //we can add code for multiple options here like...
			  //..automatically opening the report in browser immediately test execution is completed 
			  //...automatically sending report to the mail 
			  
			/*  try {
				  File file=new File(System.getProperty("user.dir")+"/reports/"+reportname);
				  HtmlEmail email = new HtmlEmail();
				  email.setHostName("smtp.gmail.com");
				  email.setSmtpPort(587);
				  email.setAuthenticator(new DefaultAuthenticator("thangamcmca@gmail.com","YOUR_APP_PASSWORD"));
				  email.setStartTLSEnabled(true);

				  email.setFrom("thangamcmca@gmail.com");
				  email.setSubject("Test Results");
				  email.addTo("thangamcmca@gmail.com");
				  DataSource source = new FileDataSource(file);
				  email.attach(source, reportname, "Please check report");
				  email.send();
				  System.out.println("Email sent successfully!");
			  }
			  catch(Exception e)
			  {
				  System.out.println(e.getLocalizedMessage());
			  }*/
				  
		  }
		  }


