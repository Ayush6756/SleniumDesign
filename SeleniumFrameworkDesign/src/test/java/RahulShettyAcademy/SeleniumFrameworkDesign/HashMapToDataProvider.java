package RahulShettyAcademy.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyAcademy.PageObjectModel.CartPage;
import RahulShettyAcademy.PageObjectModel.ProductCatalogue;
import RahulShettyAcademy.TestComponent.BaseTest;

public class HashMapToDataProvider extends BaseTest {
	
	//String item = "ZARA COAT 3";

	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// WeDriverManager.chromedriver().setup();

		
		String country = "india";

	    landingPage = launchApplication();

		landingPage.loginApplication(input.get("email"), input.get("password"));

		ProductCatalogue pc = new ProductCatalogue(driver);
		List<WebElement> products = pc.getProductList();


		pc.addProductToCart(input.get("item"));

		pc.addToCartPage();

		CartPage cp = new CartPage(driver);
		Boolean match = cp.cartItemsAvailable(input.get("item"));
		Assert.assertTrue(match);
		cp.checkOut(country);

		String confirmationMessage = cp.confirmationPage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		//driver.close();
	}
//	
//	@Test (dependsOnMethods={"submitOrder"})
//	public void orderHistoryTest() {
//		landingPage.loginApplication("ayushs7598@gmail.com", "Ayush1234");
//		OrderHistoryPage op = new OrderHistoryPage(driver);
//		op.orderHeader();
//		Assert.assertTrue(op.orderHistoryPage(input.get("item")));
//	}
	
	/* How to convert JSON data to string 
	 * > Create a package in Test folder related to data 
	 * > Create a file with.json extension
	 * > Create a class DataProvide (here we will write all the steps to convert json to string and then back to HashMap*/
	
	
	
	
	
	@DataProvider	
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataMap(System.getProperty("user.dir") + "//test//java//RahulShettyAcademy//Data//PurchaseData.json"); // this mwthod is returning list so encapsulate it with List<String<HashMap
		return new Object [][] {{data.get(0)},{data.get(1)}};
	
	}
	
//	@DataProvider	
//	public Object[][] getData() {
//		// Here it is the second method of using Hasmap to dataprovider
//		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "ayushs7598@gmail.com");
//		map.put("password", "Ayush1234");
//		map.put("item", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "ayushsrivastava7755@gmail.com");
//		map1.put("password", "Ayush1234");
//		map1.put("item", "ADIDAS ORIGINAL");
//		
//		
//		
//		return new Object [][] {{map},{map1}};
//	}

}
