package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.PageObjectModel.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
	//	WeDriverManager.chromedriver().setup();
		
		
		String item = "ZARA COAT 3";
		String country = "india";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("ayushs7598@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ayush1234");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[routerlink*='cart']")))).click();
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartItem = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean cartProduct = cartItem.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(item));
		Assert.assertTrue(cartProduct);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector(".form-group input")).sendKeys(country);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")))).click();
		
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		String thankyou = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(thankyou.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		}

}
