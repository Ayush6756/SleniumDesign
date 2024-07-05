package RahulShettyAcademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyAcademy.PageObjectModel.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties(); // we are calling this Properties class becuase we need to invoke
											// .Properties class(GlobalData.properties)
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\RahilShettyAcademy\\resources\\GlobalData.properties");
		// FileInputStream is called because properties class returns this method
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		}

		if (browserName.equalsIgnoreCase("edge")) {
			// Edge
		}

		if (browserName.equalsIgnoreCase("chrome")) {
			// firefox
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataMap(String filePath) throws IOException {

		// convert JSOn to String add dependency common.io
		String jsonContent = FileUtils.readFileToString(
				new File(filePath),
				StandardCharsets.UTF_8);

		// Now conevrt String to Hashmap for this add a dependency jackson bind

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	public String getScreenshots(String testCaseName, WebDriver driver) throws IOException {
		// Step 1> grab screenshot to driver mode 		
			TakesScreenshot ts = (TakesScreenshot)driver;
			
			//Step 2> now call screenshot method and assigne the output type and store it in File 
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			// Step 3> now after taking ss copy the ss image to a file for that we will use FileUtility package 
			File file = new File(System.getProperty("user.dir") + "//reports//" +testCaseName+ ".png");
			FileUtils.copyFile(source, source);
			
			// Step 4> return the same path 
			return System.getProperty("user.dir") + "//reports//" +testCaseName+ ".png";
		}

	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
