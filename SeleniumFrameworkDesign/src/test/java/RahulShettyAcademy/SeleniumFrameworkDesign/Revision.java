package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Revision {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String username = "ayushs7598@gmail.com";
		String password = "Ayush1234";
		String item = "IPHONE 13 PRO";
		String country = "India";
		
		
		// Login Page 
		driver.findElement(By.cssSelector("#userEmail")).sendKeys(username);
		driver.findElement(By.cssSelector("#userPassword")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		
		// Clicking on Item
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(item)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		// Checkout Page 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")))).click();
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Checkout']")))).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(country);
		
		List<WebElement> countryName = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']"));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Place Order ']")))).click();
	
		
		

	}

}
