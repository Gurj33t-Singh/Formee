Feature: Verify the product listings for retail user  

Scenario Outline: Verify that user is able to login 
Given "<email>" and "<password>" for user
When User calls "login" api 
Then "msg" key value is "login_successfully"
And "data.token" is captured

Examples: 
	|email						|password		|
	|gpavan524@gmail.com		|12345678	|


Scenario: Verify that retail user is able to get product listings 
Given User is logged in with "Personal" user
And User has payload with Classified ID "3594"
When User calls "product-listing-by-id" api
Then "message" key value is "Products details by id listed successfully"