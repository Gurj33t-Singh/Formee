package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {

	
	public RequestSpecification requestSpec() throws IOException {
		PrintStream ps=new PrintStream(new FileOutputStream("RequestStream.txt"));
		
		RequestSpecification ReqSpec=new RequestSpecBuilder()
				.addFilter(RequestLoggingFilter.logRequestTo(ps))
				.setBaseUri(getProperty("URI"))
				.build();
		return ReqSpec;
	}
	
	
	public String getProperty(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("/home/gurjeet/eclipse-workspace/Formee/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	
	public static String getJsonVal(String Response, String Key) {
		JsonPath js=new JsonPath(Response);
		return js.get(Key).toString();
	}
	
}
