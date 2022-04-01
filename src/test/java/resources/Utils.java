package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	
	
	public XSSFSheet getSheet() throws IOException {
		
		//loaded the sheet
		FileInputStream fis=new FileInputStream("/home/gurjeet/eclipse-workspace/Formee/TestSheet.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet = null;
		
		//got count of sheets 
		int sheetcount=workbook.getNumberOfSheets();
		
		//finding the desired sheet based on name
		for (int i=0; i<sheetcount; i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase("LoginData")) {
				sheet=workbook.getSheetAt(i);
			}
		}
		
		return sheet;
	}
	
	
	public ArrayList<String> getRowData(int rowNum) throws IOException {
		ArrayList<String> array=new ArrayList<>();
				
		//get row iterator from sheet 
		Iterator<Row> rows=getSheet().rowIterator();
		
		while(rows.hasNext()) {
		
			//moving to row and getting its cell iterator
			Iterator<Cell> cells=rows.next().cellIterator();
			
			//getting cell data
			while(cells.hasNext()) {
				//move to first cell
				Cell cellVal=cells.next();
				
				//iterate through cells based on row number
				if(cellVal.getNumericCellValue()==rowNum) {
					if(cellVal.getCellType()==CellType.NUMERIC) {
						array.add(NumberToTextConverter.toText(cellVal.getNumericCellValue()));
						
					}
					else{
						array.add(cellVal.getStringCellValue());						
					}			
				}
				else
					break;
			}
		}	
		return array;
	}
}






