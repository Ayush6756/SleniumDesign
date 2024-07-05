package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.PageObjectModel.CartPage;
import RahulShettyAcademy.PageObjectModel.LandingPage;
import RahulShettyAcademy.PageObjectModel.ProductCatalogue;
import RahulShettyAcademy.TestComponent.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException {

		// WeDriverManager.chromedriver().setup();

		String item = "ZARA COAT 3";
		String country = "india";

	    landingPage = launchApplication();

		landingPage.loginApplication("ayushs7598@gmail.com", "xyz");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

		//driver.close();
	}

}
