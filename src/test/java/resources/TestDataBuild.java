package resources;

import pojo.AddToCart;
import pojo.PlaceOrder;
import pojo.ProductListingById;

public class TestDataBuild {

	public ProductListingById ProductListingByID_Payload(String Classified_ID) {
		ProductListingById body=new ProductListingById();
		body.setClassified_id(Classified_ID);
		return body;
	}
	
	public AddToCart AddToCard_Payload(String Size, int Classified_id, String Quantity, String Color) {
		AddToCart body=new AddToCart();
		body.setSize(Size);
		body.setClassified_id(Classified_id);
		body.setQty(Quantity);
		body.setColor(Color);
		return body;
	}
	
	public PlaceOrder PlaceOrder_Payload(String add, String fname, String lname, String deliveryOption, String promo, String message) {
		PlaceOrder body=new PlaceOrder();
		body.setCustomer_address1(add);
		body.setCustomer_fname(fname);
		body.setCustomer_lname(lname);
		body.setDelivery_option(deliveryOption);
		body.setPromo_code(promo);
		body.setShipping_message(message);
		return body;
	}
}
