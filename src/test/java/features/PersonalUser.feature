Feature: Personal user scenarios  

Scenario Outline: Verify that user is able to login 
Given "<email>" and "<password>" for user
When User calls "login" api 
Then "msg" key value is "login_successfully"
And "data.role_slug" key value is "<role>"
And "data.token" is captured

Examples: 
	|email								|password		|role		|
	|gpavan524@gmail.com				|12345678		|private	|
	|william_retail_vendor@yopmail.com	|12345678		|merchant	|


Scenario: Verify that user is able to get product listings 
Given User is logged in with "Personal" user
And User has payload for "product-listing-by-id" 
When User calls "product-listing-by-id" api
Then "message" key value is "Products details by id listed successfully"

@Priority
Scenario: Verify that user is able to add items to cart 
Given User is logged in with "Personal" user
And User has payload for "add_to_cart" 
When User calls "add_to_cart" api 
Then "msg" key value is "Product added to cart successfully."

@Priority
Scenario: Verify that user is able to get cart items
Given User is logged in with "Personal" user
And User has payload for "cart" 
When User calls "cart" api 
Then "message" key value is "cart list fetched successfully"


@Priority
Scenario: Verify that user is able to place order 
Given User is logged in with "Personal" user
And User has payload for "place-orders-api" 
When User calls "place-orders-api" api 
Then "message" key value is "Order placed successfully"




