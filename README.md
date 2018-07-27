# su18-202-seekers

# Database Design - ER diagram

![data_model_er_diagram](https://user-images.githubusercontent.com/21271829/42901469-758f2dc4-8a80-11e8-97e4-c5c3552b6c6b.jpg)

# Architecture Diagram 

![Architecture Diagram](https://user-images.githubusercontent.com/31361314/43311542-9c81e6f4-913f-11e8-9258-5918507f583c.png)

# Feature Set

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
