# su18-202-seekers

# Design Decisions

1.	Implemented the following components:
	* Sign In / Sign Up API
    	* Order Management API
	* Card Management API
    	* Payment API
	
2.	Designed persistence layer as a single jar plugin. Created a single Spring Boot project in which each team member contributed for the above components to create the Data access layer.
3.	Created four Java Spring projects as business logic components which uses Spring REST controllers for exposing the API endpoints.
4.	Included Persistence layer as a plugin in each of the above project component.
5.	Written unit test cases using JUnit for these components.
6.	In order to follow Microservices architecture, all the APIs has been deployed to individual AWS cloud environments to have a proper decoupling between them.
7.	Deployment:
	* Deployed Order Management and Card Management API to AWS Docker Containers using ECS.
	* Deployed Sign In / Sign Up and Payment API to AWS in an Auto Scaled EC2 Cluster with Load Balancer.


# Architecture Diagram 

![Architecture Diagram](https://user-images.githubusercontent.com/31361314/43311542-9c81e6f4-913f-11e8-9258-5918507f583c.png)


# Database Design - ER diagram

<img width="851" alt="data_model" src="https://user-images.githubusercontent.com/21271829/43332354-0c1163f4-917d-11e8-8ff1-5b690e9b26ec.png">


# Feature Set

## User Profile API

1.	Create User 
    -	Accept user inputs and perform data quality test.
    -	Provide relevant messages for invalid and not acceptable inputs.
    -	Profile validation.
    -	User Profile creation

2.	Sign in and Sign out
    -	Accept username and password, validate the credentials. 
    -	Display appropriate message when user logs in – “successfully logged in”,” username and/or password does not match”. 
    -	If successfully logged in, a status is created and maintained until user logs off.

3.	Update user details
    -	Display user profile. User can update the editable fields like address, phone number, full name and password. 
    -	Username and Email Id are unique and cannot be edited.

4.	Reset Password
    -	User can change his password if already logged in.
    -	The application prompts for new password and confirm password. If they match, new password will be updated.

5.	Forgot Username
    -	User can retrieve the username in case he/she forgets.
    -	An email will be sent to the registered email id which contains username.

6. 	Delete Account
    -	If user decides to delete his account.
    -	 The user details will be marked as not active in the database.

7. 	Forgot Password
    -	User can reset the password, if forgotten
    -	Username and registered email will be accepted.
    -	Verification code is sent to the email id
    -	User can set the new password by entering correct verification code and new valid password.


## Order Management API
    
1.  Menu Catalogue
    -	 Authenticate the user.
    -	 Once the user logs in, menu of all items with their names, description and pictures will be shown to the user.
    -  User can select an item and look into its details like item name, picture, description, size, price and reward points          allocated for the item.

2.	Add to Cart
     -	Authenticate the user.	
     -	User can select the size, quantity and any extra toppings for multiple items.
     -	And proceed to add it to their cart.

3. Store Details 
    -	 Authenticate the user.
    -	 User will be displayed with a list of all stores showing store name and address.
    
4. Select Order Pickup Store
    -	 Ability to select one store from the stores list after adding items to the cart to define order pickup store

5.	Review Cart
    -  Authenticate the user.
    -	 A list of all user added items is shown in the cart with name, description, image, price, quantity, size and                  toppings details.

6.	Edit cart
    -  Authenticate the user.
    -	 The users can edit the cart to add new items or remove exiting ones only if the user has an active cart i.e. if user          had added items in the cart earlier.
   
   
 ## PayemntCardDetails API
 
 1.	Save Cards
    -	Authenticate the user.
    - Once user is authenticated, user can add cards by giving card number, CVV, Expiration Month and Expiration Year.
    - User can also add billing address and shipping address.
    
2.	Card Details for a specific user
    -	Authenticate the user.
    - User will have all the cards attached to username and can check all the cards available for payment.
    - All card details fields are mandatory. Field missing will throw an error and data won’t be saved in database.
    - Card Number is set to 9 digits and CVV is set to 3 digits. Application prompts to input correct credentials if details are violated.
    
3.	Update card details
    - Display card details. User can update the editable fields like card number, billing address, CVV and expiration month and year.
    - Username is unique and cannot be edited.
    
4.	Delete card Details
    - User can delete card details.
    - Card details will be marked inactive in database.

     
## Payment API

1. Payment Methods 
    -	Validate if user is authentic
    -	If user has items in his cart and proceeds to pay, display the Card Numbers that user has already added 
  -	Also display the reward points available for the user

2.	Payment card verification
    - If payment method is by ‘Card’, ask user to input Card key and CVV. Validate this input before proceeding with payment.
    - If payment method is ‘Rewards’, check if the reward points are sufficient to make the transaction.

3.	Review Order
    -	Allow the user to review the order he has placed before making the payment.
    -	The review order be a list of items with each item having Product Name, Product description, Size, Quantity, Toppings and Amount.

4.	Confirm Order
    -	Validate if user is authentic
    -	If user confirms the order with Rewards, fetch the Order that is in progress and update it to complete and deduct the reward points for that user.
    -	If user confirms the order with Card, fetch the Order that is in progress and update it to complete and add reward points for that user depending upon the transactions made.
     -	Update the payment Id for the user, displaying order successfully placed.

5.	Clear cart
     -	At any given point in time, the user can clear the cart i.e. orders that are in progress
