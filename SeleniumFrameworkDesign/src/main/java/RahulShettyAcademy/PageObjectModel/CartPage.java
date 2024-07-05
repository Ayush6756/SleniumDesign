package RahulShettyAcademy.PageObjectModel;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItem;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	@FindBy(css=".form-group input")
	WebElement selectCountry;
	
	@FindBy(xpath= "//a[text()='Place Order ']")
	WebElement placeOrder;
	
	@FindBy(css= ".hero-primary")
	WebElement thankyou;
	
//	@FindBy(css=".ta-item:nth-of-type(2)")
//	WebElement country;
	By country = By.cssSelector(".ta-item:nth-of-type(2)");
	
	By Toast = By.cssSelector("#toast-container");

	
//	public void addProductToCart(String productName) {
//		WebElement prod = productName(productName);
//		prod.findElement(AddToCart).click();
//		findElementBy(Toast);
//		findElementsByInvisibility(waitTime);
//
//	}
//	List<WebElement> cartItem = driver.findElements(By.cssSelector(".cartSection h3"));
//	Boolean cartProduct = cartItem.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(item));
//	Assert.assertTrue(cartProduct);
	
	public Boolean cartItemsAvailable(String productName) {
		//WebElement cartProduct = cartItem;
		Boolean cartItems = cartItem.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
		return cartItems;
	}
	
//	public void checkOut(String countryName ) {
//		checkoutButton.click();
//		selectCountry.sendKeys(countryName);
////		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////	        WebElement countryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(country));
//		selectCountry.findElement(country).click();
//		
//		//driver.findElement(By.cssSelector(".form-group input")).sendKeys(country);
//		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")))).click();
//	}
	public void checkOut(String countryName) {
	    checkoutButton.click();
	    selectCountry.sendKeys(countryName);
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(country));
	    
	    List<WebElement> countryElements = driver.findElements(country);
	    System.out.println("Number of country elements found: " + countryElements.size());
	    
	    if (!countryElements.isEmpty()) {
	        countryElements.get(0).click();
	    } else {
	        System.out.println("No country elements found.");
	    }
	}
	
	public String confirmationPage() {
		
		placeOrder.click();
		
		//driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		String thanks = thankyou.getText();
		return thanks;
//				driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		
		
	}
	

}
