package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyAcademy.PageObjectModel.CartPage;
import RahulShettyAcademy.PageObjectModel.OrderHistoryPage;
import RahulShettyAcademy.PageObjectModel.ProductCatalogue;
import RahulShettyAcademy.TestComponent.BaseTest;

public class StandAloneTest_POMReference extends BaseTest {
	
	String item = "ZARA COAT 3";

	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(String email, String password, String item) throws IOException, InterruptedException {

		// WeDriverManager.chromedriver().setup();

		
		String country = "india";

	    landingPage = launchApplication();

		landingPage.loginApplication(email, password);

		ProductCatalogue pc = new ProductCatalogue(driver);
		List<WebElement> products = pc.getProductList();


		pc.addProductToCart(item);

		pc.addToCartPage();

		CartPage cp = new CartPage(driver);
		Boolean match = cp.cartItemsAvailable(item);
		Assert.assertTrue(match);
		cp.checkOut(country);

		String confirmationMessage = cp.confirmationPage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		//driver.close();
	}
	
	@Test (dependsOnMethods={"submitOrder"})
	public void orderHistoryTest() {
		landingPage.loginApplication("ayushs7598@gmail.com", "Ayush1234");
		OrderHistoryPage op = new OrderHistoryPage(driver);
		op.orderHeader();
		Assert.assertTrue(op.orderHistoryPage(item));
	}
	
	@DataProvider
	
	public Object[][] getData() {
		
		return new Object [][] {{"ayushs7598@gmail.com", "Ayush1234","ZARA COAT 3"},{"ayushs7598@gmail.com", "Ayush1234", "ADIDAS ORIGINAL"}};
	}

}
