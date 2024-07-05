package RahulShettyAcademy.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	// we are writing the above locator here , here it is findElement(s) so we
	// cannot use WebElement directly
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement waitTime;
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement AddToCartPage;
	
//	@FindBy(css = "tr td:nth-child(3)")
//	List<WebElement> orderHistoryPage;	
//	

	By productBy = By.cssSelector(".mb-3");
	By AddToCart = By.cssSelector(".card-body button:last-of-type");
	By Toast = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		// Apply the wait method
		findElementBy(productBy);
		return products;
	}

	// WebElement prod =
	// products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals("ZARA
	// COAT 3")).findFirst().orElse(null);
	// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

	public WebElement productName(String productName) {

		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = productName(productName);
		prod.findElement(AddToCart).click();
		findElementBy(Toast);
		findElementsByInvisibility(waitTime);

	}
	
//	public void oderHistory(String productName) throws InterruptedException {
//		WebElement prod = productName(productName);
//		prod.findElements(orderHistoryPage).click();
//	
//
//	}
	
	

}
