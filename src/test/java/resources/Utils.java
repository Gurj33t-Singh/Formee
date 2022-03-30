package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {

	RequestSpecification ReqSpec;

	public RequestSpecification requestSpec() throws IOException {
		
		if(ReqSpec==null) {
			PrintStream ReqPS=new PrintStream(new FileOutputStream("RequestStream.txt"));
			PrintStream ResPS=new PrintStream(new FileOutputStream("ResponseStream.txt"));
			SessionFilter Session=new SessionFilter();
			ReqSpec=new RequestSpecBuilder()
					.addFilter(RequestLoggingFilter.logRequestTo(ReqPS))
					.addFilter(ResponseLoggingFilter.logResponseTo(ResPS))
					.setBaseUri(getProperty("URI"))
					//.addFilter(Session)
					.build();
			return ReqSpec;
		}
		
		else {
			return ReqSpec;
		}

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
