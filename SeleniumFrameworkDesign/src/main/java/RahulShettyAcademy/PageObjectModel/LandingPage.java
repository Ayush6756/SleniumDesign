package RahulShettyAcademy.PageObjectModel;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

//		 POM, is a design pattern in Selenium that creates an object repository for storing all web elements. It helps reduce code duplication and 
//		improves test case maintenance. 

	// Steps to update the code in POM

	// 1st Create a constructor : - The main agenda of creating a constructor is to
	// invoke the "driver" class , see what we are going to do is
	// we will restore all the locators of Landing page for that we need to write
	// driver.findElement(By.id("username")) but here driver is not intialized
	// we have initialzed the driver in "StandAloneTest.java" file. Also in
	// "StandAloneTest.java" file we have to create a boject an object and pass
	// 'drive' as parameter
	// Note : - always create constructor at top before writing any other code it
	// coz this will be launched at first and driver class will be invoked

	WebDriver driver; // this.driver refers to this driver

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// 2nd step now write all your Landing page Locators here
	// but here we will learn a new concept of "PageFactory" this will help in
	// writing the code in much more compact way

	// WebElement username = driver.findElement(By.id("userEmail")); this is the
	// normal way of writing , we can use this as well

	@FindBy(id = "userEmail") // now the question comes everything is fine , but from where the driver will be
								// invoked for that we have to use
	WebElement username; // "PageFactory.initElement()" method which will take two parameter driver and
							// this. Also we have to invoke this method in
							// Constructor because that will be invoked first in any class that's why

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut'")
	WebElement errorMessage;

	// Create Action class email, pass, and submit
	// Note : - Never assign value to any of the above in POM , all this need to be
	// done in your TC class

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMessage() {
		visibilityOfWebElement(errorMessage);
		return errorMessage.getText();
	}

	public void loginApplication(String name, String pass) {
		username.sendKeys(name);
		password.sendKeys(pass);
		submit.click();
	}

}
