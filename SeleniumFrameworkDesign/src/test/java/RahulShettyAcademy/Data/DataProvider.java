//package RahulShettyAcademy.Data;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class DataProvider {
//	
//	public List<HashMap<String, String>> getJsonDataMap() throws IOException {
//		
//		// convert JSOn to String add dependency common.io
//		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+ "//test//java//RahulShettyAcademy//Data//PurchaseData.json"),
//				StandardCharsets.UTF_8);
//		
//		// Now conevrt String to Hashmap for this add a dependency jackson bind
//		
//		ObjectMapper mapper = new ObjectMapper();
//		
//		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
//		return data;
//	}
//
//}
