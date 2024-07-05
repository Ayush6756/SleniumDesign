package RahulShettyAcademy.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.PageObjectModel.OrderHistoryPage;

public class AbstractComponent {
	
	WebDriver driver;


	public  AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	
	
	
	// Note : - This page will be the parent class , for all the reusable codes 	
	
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	
	// To use the explicit wait everywhere if required 
	
	// Concept : - a locator starting with driver. will have return type as driver but in case if a locator doesnot have driver for e.g 
	// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); here the retyrn type will be 'By'
	// create a method
	
	public void findElementBy(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void visibilityOfWebElement(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
//	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	public void findElementsByInvisibility(WebElement ele) throws InterruptedException {
		
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void addToCartPage() { // we are writing add to cart page method in Abstract component coz this is a header tab and can reused anytime 
		cartHeader.click();
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[routerlink*='cart']")))).click();
	}
	
	public OrderHistoryPage orderHeader() {
		ordersHeader.click();
		
		OrderHistoryPage orderpage = new OrderHistoryPage(driver);
		return orderpage;
	}

}
