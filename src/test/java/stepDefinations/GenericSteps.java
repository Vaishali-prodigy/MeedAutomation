package stepDefinations;

import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import io.appium.java_client.*;
import io.appium.java_client.touch.offset.PointOption;
import meed.base.MeedBasePage;
import net.prodigylabs.config.ObjectRepository;
import net.prodigylabs.driver.CapabilitiesGenerator;
import net.prodigylabs.handlers.ScreenshotHandler;
import net.prodigylabs.test.BaseTest;

import org.junit.Assert;


public class GenericSteps extends BaseTest{

	//public AndroidDriver<MobileElement> driver;
	WebDriver driver;
    public WebDriverWait wait;
    
    MeedBasePage meedbasepage = null;

    DesiredCapabilities caps = new DesiredCapabilities();
	ScreenshotHandler screenshot = null;
	String sName = null;
    
	@Before
	public void setup(Scenario scenario) throws Exception {		
		System.out.println("Executing Before of Step Defination");
		sName=scenario.getName();
		meedbasepage = new MeedBasePage(driver);
	}
	
	@After()
	public void tearDown() throws Exception {		
		System.out.println("Executing After of Step Defination");
        Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
		driver.quit();
	}
    
    @Given("^user launches the app in \"(.*?)\" device$")
    public void user_launches_the_app_in_device(String arg1) throws Throwable {    	 
        
        driver = CapabilitiesGenerator.getInstance().launchApp(arg1);
        screenshot = new ScreenshotHandler(driver);
    	Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
	
    }
    
    @Given("^user enters email \"(.*?)\"$")
    public void user_enters_email(String arg1) throws Throwable {
    	wait = new WebDriverWait(driver, 40);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.EditText"))).sendKeys(arg1);
    	Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
    }
    
    
    @Given("^user clicks on button \"([^\"]*)\"$")
    public void user_clicks_on_button(String button_name) throws Throwable {
    	wait.until(ExpectedConditions.visibilityOfElementLocated
    			(ObjectRepository.getobjectLocator(button_name))).click();  
    	Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
    	
    }
    
    @Given("^user clicks on label \"([^\"]*)\"$")
    public void label(String label_name) throws Throwable {    	 
    	wait.until(ExpectedConditions.visibilityOfElementLocated
    			(ObjectRepository.getobjectLocator(label_name))).click();  
    	Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
    	
    }
   
    @Given("^user enters text \"([^\"]*)\" in textbox \"([^\"]*)\"$")
    public void user_enters_text_in_textbox(String text_value, String textbox_name) throws Throwable {
    	wait.until(ExpectedConditions.visibilityOfElementLocated
    			(ObjectRepository.getobjectLocator(textbox_name))).clear();
    	wait.until(ExpectedConditions.visibilityOfElementLocated
                (ObjectRepository.getobjectLocator(textbox_name))).sendKeys(text_value);
    	Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
    }
   
    
    @Given("^user selects option \"([^\"]*)\"  from the dropdown \"([^\"]*)\"$")
    public void user_selects_option_from_the_dropdown(String dropdown_value, String dropdown_name) throws Throwable {
    	 wait.until(ExpectedConditions.visibilityOfElementLocated
    			 (ObjectRepository.getobjectLocator(dropdown_name))).click();
         //Select Weekly Radio button
         wait.until(ExpectedConditions.visibilityOfElementLocated
        		 (ObjectRepository.getobjectLocator(dropdown_value))).click();
         Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
          
    }
    
    
 /*   @Given("^user select radio button \"([^\"]*)\"$")
    public void user_select_radio_button(String recipient) throws Throwable {
        
    	driver.findElement(By.xpath("//android.widget.CheckBox[@text='" +"receipient']")).click();
    	
    	utility.captureScreenShot(driver);
    	
    }
    */

    @Given("^user validates \"([^\"]*)\" field with expected value as \"([^\"]*)\"$")
    public void user_validates_field_with_expected_value_as(String actual, String expected) throws Throwable {
    	Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
    	actual = actual+ "_value";
    	String actual_argument = wait.until(ExpectedConditions.visibilityOfElementLocated
   			 (ObjectRepository.getobjectLocator(actual))).getText();
    
    		Assert.assertEquals(expected, actual_argument);
    		
    }
   
    
    @Given("^user swipes right to select \"([^\"]*)\"$")
    public void user_swipes_right_to_select(String account) throws Throwable {
    	
    	 TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);    //for touch actions on mobile devices
    	if(account.equalsIgnoreCase("SAVINGS"))
	{
    	//action.press(startx,starty).waitAction(1000).moveTo(startx,endy).release().perform();    	
        touchAction.longPress(PointOption.point(290, 309)).moveTo(PointOption.point(0, 309)).release().perform();
    }
    	else
    	{
    		touchAction.longPress(PointOption.point(290, 540)).moveTo(PointOption.point(0, 540)).release().perform();	
    	}
    	Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
    }
    
    
    @Given("^user clicks on link \"([^\"]*)\"$")
    public void user_clicks_on_link(String linkname) throws Throwable {
       
    	 wait.until(ExpectedConditions.visibilityOfElementLocated
    			 (ObjectRepository.getobjectLocator(linkname))).click();
    	 Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
    	
    }
    
    @Given("^user selects checkbox \"([^\"]*)\"$")
    public void user_selects_checkbox(String checkbox_name) throws Throwable {
    	wait.until(ExpectedConditions.visibilityOfElementLocated
   			 (ObjectRepository.getobjectLocator(checkbox_name))).click();
    	Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
        
    }

    @Given("^user waits for \"(.*?)\" seconds$")
    public void user_waits_for_seconds(long arg1) throws Throwable 
    {
       arg1 = arg1*1000;
    	Thread.sleep(arg1);
    	Reporter.addScreenCaptureFromPath(screenshot.captureScreenShot(sName));  
    } 
    
    
    @Given("^user enters text in textbox with index \"([^\"]*)\"$")
    public void user_enters_text_in_textbox_with_index(int index) throws Throwable {
    	List<MobileElement> elements = driver.findElements(By.className("android.widget.EditText"));
        System.out.println("Number of elements:" +elements.size());

        for (int i=0; i<elements.size();i++)
        {
          System.out.println("button text:" + elements.get(i).getAttribute("text"));
          elements.get(index).sendKeys("meedqe75@yopmail.com");
          elements.get(index).click();
         
        }
    }
    
    
    
    
    
    @Given("^user wats for \"([^\"]*)\" seconds$")
    public void user_wats_for_seconds(String arg1) throws Throwable {
    	List<MobileElement> elements = driver.findElements(By.className("android.widget.EditText"));
        System.out.println("Number of elements:" +elements.size());

        for (int i=0; i<elements.size();i++)
        {
          System.out.println("button text:" + elements.get(i).getAttribute("text"));
          elements.get(1).sendKeys("meedqe75@yopmail.com");
          elements.get(1).click();
         
        }
    }
    
}	



    
	

