package resources;

import pojo.ProductListingById;

public class TestDataBuild {

	public ProductListingById ProductListingByID_Payload(String Classified_ID) {
		ProductListingById body=new ProductListingById();
		body.setClassified_id(Classified_ID);
		return body;
	}
	
}
