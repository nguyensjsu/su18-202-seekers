# su18-202-seekers

# Database Design - ER diagram

![data_model_er_diagram](https://user-images.githubusercontent.com/21271829/42901469-758f2dc4-8a80-11e8-97e4-c5c3552b6c6b.jpg)

# Architecture Diagram 

![Architecture Diagram](https://user-images.githubusercontent.com/31361314/43311542-9c81e6f4-913f-11e8-9258-5918507f583c.png)

# Feature Set

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
