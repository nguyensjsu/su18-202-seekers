package edu.sjsu.seekers.PaymentAPI;

import edu.sjsu.seekers.PaymentAPI.Request.ReviewOrderRequest;
import edu.sjsu.seekers.PaymentAPI.Response.ConfirmOrderResponse;
import edu.sjsu.seekers.PaymentAPI.Response.GenericResponse;
import edu.sjsu.seekers.PaymentAPI.Response.PaymentOptionsResponse;
import edu.sjsu.seekers.PaymentAPI.service.PaymentService;
import edu.sjsu.seekers.starbucks.dao.*;
import edu.sjsu.seekers.starbucks.model.*;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentApiApplicationTests {
	@Autowired
	UserDAO userDAO;

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	OrderDetailsDAO orderDetailsDAO;

	@Autowired
	PaymentService paymentService;

	@Autowired
	AddressDAO addressDAO;

	@Autowired
	PaymentCardDetailsDAO paymentCardDetailsDAO;

	@Autowired
	PaymentDetailsDAO paymentDetailsDAO;

	String username ="Ashwini_TestCaseUser";


	@Test
	public void clearCartTestCase1() {
		// Login to the account
		Optional<User> userByUserName = userDAO.findUserByUsername(username);
		if(!userByUserName.isPresent()) assert(false);
		userByUserName.get().setIsLoggedIn("Y");
		userDAO.save(userByUserName.get());


		// Add things to the Orders table
		Orders newOrder = new Orders();
		newOrder.setUserKey(userByUserName.get());
		newOrder.setOrderDate(new Date());
		newOrder.setOrderStatus("InProgress");
		newOrder.setOrderAmount(100.98);
		newOrder.setRewardsEarned(10.10);
		Orders currentOrder = orderDAO.save(newOrder);

		// Add things to OrderDetails Table
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setNetPrice(100.98);
		orderDetails.setOrderKey(newOrder);
		orderDetails.setOrderQuantity(1);
		Products tempProducts = productDAO.getProductByProductName("Latte");
		if(tempProducts == null) assert(false);
		orderDetails.setProductKey(tempProducts);
		orderDetails.setToppings("Ashwini ClearCart TestCase");
		orderDetailsDAO.save(orderDetails);

		// Call paymentService API
		ResponseEntity<GenericResponse> response = paymentService.clearCart(username);

		// Validate that the cart is cleared
		Optional<Orders> orders = orderDAO.findIncompleteOrdersByUserKey(userByUserName.get().getUserKey());
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());
		if(orders.isPresent()){
			assert (false);
		}
		else
		{
			assert HttpStatus.OK.toString().equals(response.getStatusCode().toString());
			assert (response.getBody().getResponseMessage().contains("Cart Cleared!"));
		}



	}

	@Test
	public void clearCartTestCase2() {
		// Login to the account
		Optional<User> userByUserName = userDAO.findUserByUsername(username);
		if(!userByUserName.isPresent()) assert(false);
		userByUserName.get().setIsLoggedIn("Y");
		userDAO.save(userByUserName.get());

		// Call paymentService API
		ResponseEntity<GenericResponse> response =paymentService.clearCart(username);
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());

		assert HttpStatus.EXPECTATION_FAILED.toString().equals(response.getStatusCode().toString());
		assert (response.getBody().getResponseMessage().contains("Cart could not be cleared as there are no products in the cart!"));
	}


	@Test
	public void clearCartTestCase3() {
		//LOGIN CHECK TEST

		// Login to the account
		Optional<User> userByUserName = userDAO.findUserByUsername(username);
		if(!userByUserName.isPresent()) assert(false);
//		userByUserName.get().setIsLoggedIn("Y");
		userDAO.save(userByUserName.get());
		// Call paymentService API
		ResponseEntity<GenericResponse> response = paymentService.clearCart(username);

		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());

		assert HttpStatus.EXPECTATION_FAILED.toString().equals(response.getStatusCode().toString());
		assert (response.getBody().getResponseMessage().contains("Cart could not be cleared as the user was not authenticated or couldn't find the user!"));


	}


	@Test
	public void getPaymentOptionsTestCase1(){
		Optional<User> userByUserName = userDAO.findUserByUsername(username);
		if(!userByUserName.isPresent()) assert(false);
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());

		ResponseEntity<PaymentOptionsResponse> response = paymentService.getPaymentOptions(username);
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());
		assert HttpStatus.EXPECTATION_FAILED.toString().equals(response.getStatusCode().toString());
		assert (response.getBody().getResponseMessage().contains("User is not present or user has not signed in."));

	}


	@Test
	public void getPaymentOptionsTestCase2(){
		Optional<User> userByUserName = userDAO.findUserByUsername(username);
		if(!userByUserName.isPresent()) assert(false);
		userByUserName.get().setIsLoggedIn("Y");
		userDAO.save(userByUserName.get());

		ResponseEntity<PaymentOptionsResponse> response = paymentService.getPaymentOptions(username);
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());
		assert HttpStatus.EXPECTATION_FAILED.toString().equals(response.getStatusCode().toString());
		assert (response.getBody().getResponseMessage().contains("No items in the cart to proceed."));

	}

	@Test
	public void getPaymentOptionsTestCase3(){
		Optional<User> userByUserName = userDAO.findUserByUsername(username);
		if(!userByUserName.isPresent()) assert(false);
		userByUserName.get().setIsLoggedIn("Y");
		userDAO.save(userByUserName.get());

		// Add things to the Orders table
		Orders newOrder = new Orders();
		newOrder.setUserKey(userByUserName.get());
		newOrder.setOrderDate(new Date());
		newOrder.setOrderStatus("InProgress");
		newOrder.setOrderAmount(100.98);
		newOrder.setRewardsEarned(10.10);
		Orders currentOrder = orderDAO.save(newOrder);

		// Add things to OrderDetails Table
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setNetPrice(100.98);
		orderDetails.setOrderKey(newOrder);
		orderDetails.setOrderQuantity(1);
		Products tempProducts = productDAO.getProductByProductName("Latte");
		if(tempProducts == null) assert(false);
		orderDetails.setProductKey(tempProducts);
		orderDetails.setToppings("Ashwini ClearCart TestCase");
		orderDetailsDAO.save(orderDetails);

		ResponseEntity<PaymentOptionsResponse> response = paymentService.getPaymentOptions(username);

		paymentService.clearCart(username);

		// Validate that the cart is cleared
		Optional<Orders> orders = orderDAO.findIncompleteOrdersByUserKey(userByUserName.get().getUserKey());
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());
		assert HttpStatus.EXPECTATION_FAILED.toString().equals(response.getStatusCode().toString());
		assert (response.getBody().getResponseMessage().contains("User has no Cards. Please add a card."));


		if(!orders.isPresent())
			assert true;
		else
			assert false;

	}



	@Test
	public void getPaymentOptionsTestCase4(){
		Optional<User> userByUserName = userDAO.findUserByUsername(username);
		if(!userByUserName.isPresent()) assert(false);
		userByUserName.get().setIsLoggedIn("Y");
		userDAO.save(userByUserName.get());

		// Add things to the Orders table
		Orders newOrder = new Orders();
		newOrder.setUserKey(userByUserName.get());
		newOrder.setOrderDate(new Date());
		newOrder.setOrderStatus("InProgress");
		newOrder.setOrderAmount(100.98);
		newOrder.setRewardsEarned(10.10);
		Orders currentOrder = orderDAO.save(newOrder);

		// Add things to OrderDetails Table
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setNetPrice(100.98);
		orderDetails.setOrderKey(newOrder);
		orderDetails.setOrderQuantity(1);
		Products tempProducts = productDAO.getProductByProductName("Latte");
		if(tempProducts == null) assert(false);
		orderDetails.setProductKey(tempProducts);
		orderDetails.setToppings("Ashwini ClearCart TestCase");
		orderDetailsDAO.save(orderDetails);

		PaymentCardDetails cardDetails = new PaymentCardDetails();
		Address newAddress = new Address();

		Optional<Address> lastInsertedAddressKey = addressDAO.getLastInsertedRow();
		cardDetails.setAddressKey(lastInsertedAddressKey.get());
		cardDetails.setIsActiverCard("Y");
		cardDetails.setIsDefaultpaymentcardKey("Y");
		cardDetails.setUserKey(userByUserName.get());
		cardDetails.setCardNumber("1234567812345678");
		cardDetails.setExpirationMonth("07");
		cardDetails.setExpirationYear("2020");
		cardDetails.setCcvCode("123");
		paymentCardDetailsDAO.save(cardDetails);
		ResponseEntity<PaymentOptionsResponse> response = paymentService.getPaymentOptions(username);
		paymentService.clearCart(username);
		// Validate that the cart is cleared
		Optional<Orders> orders = orderDAO.findIncompleteOrdersByUserKey(userByUserName.get().getUserKey());
		paymentCardDetailsDAO.delete(cardDetails);
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());
		assert HttpStatus.OK.toString().equals(response.getStatusCode().toString());
		assert !response.getBody().getPaymentCards().isEmpty();
		assert response.getBody().getPaymentCards().containsValue("************5678");
		assert response.getBody().getRewardPoints()>=100;
		assert response.getBody().getResponseMessage().equals("Success!");
		if(!orders.isPresent())
			assert true;
		else
			assert false;

	}

	@Test
	public void doConfirmOrderTestCase1(){
		Optional<User> userByUserName = userDAO.findUserByUsername(username);
		if(!userByUserName.isPresent()) assert(false);
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());
		ResponseEntity<ConfirmOrderResponse> response = paymentService.doConfirmOrder("yes",username);
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());
		assert HttpStatus.EXPECTATION_FAILED.toString().equals(response.getStatusCode().toString());
		assert (response.getBody().getResponseMessage().contains("User either not Authenticated or not present."));
	}

	@Test
	public void doConfirmOrderTestCase2(){
		Optional<User> userByUserName = userDAO.findUserByUsername(username);
		if(!userByUserName.isPresent()) assert(false);
		userByUserName.get().setIsLoggedIn("Y");
		userDAO.save(userByUserName.get());


		PaymentCardDetails cardDetails = new PaymentCardDetails();

		Optional<Address> lastInsertedAddressKey = addressDAO.getLastInsertedRow();
		cardDetails.setAddressKey(lastInsertedAddressKey.get());
		cardDetails.setIsActiverCard("Y");
		cardDetails.setIsDefaultpaymentcardKey("Y");
		cardDetails.setUserKey(userByUserName.get());
		cardDetails.setCardNumber("1234567812345678");
		cardDetails.setExpirationMonth("07");
		cardDetails.setExpirationYear("2020");
		cardDetails.setCcvCode("123");
		paymentCardDetailsDAO.save(cardDetails);

//		paymentCardDetailsDAO.get();


		// Add things to the Orders table
		Orders newOrder = new Orders();
		newOrder.setUserKey(userByUserName.get());
		newOrder.setOrderDate(new Date());
		newOrder.setOrderStatus("InProgress");
		newOrder.setOrderAmount(100.98);
		newOrder.setRewardsEarned(10.10);
		newOrder.setCardKey(cardDetails);
		Orders currentOrder = orderDAO.save(newOrder);

		// Add things to OrderDetails Table
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setNetPrice(100.98);
		orderDetails.setOrderKey(newOrder);
		orderDetails.setOrderQuantity(1);
		Products tempProducts = productDAO.getProductByProductName("Latte");
		if(tempProducts == null) assert(false);
		orderDetails.setProductKey(tempProducts);
		orderDetails.setToppings("Ashwini ClearCart TestCase");
		orderDetailsDAO.save(orderDetails);


		ResponseEntity<ConfirmOrderResponse> response = paymentService.doConfirmOrder("yes",username);
		Optional<Orders> order = orderDAO.get(newOrder.getOrderKey());

		newOrder.setOrderStatus("InProgress");
		orderDAO.save(newOrder);
//		Optional<PaymentDetails> paymentline = paymentDetailsDAO.get(response.getBody().getPaymentId());
		// delete should be in sequence
		paymentService.clearCart(username);

		paymentCardDetailsDAO.delete(cardDetails);
//		paymentDetailsDAO.delete(paymentline.get());
		userByUserName.get().setIsLoggedIn("N");
		userDAO.save(userByUserName.get());

		assert HttpStatus.OK.toString().equals(response.getStatusCode().toString());
		assert (response.getBody().getResponseMessage().contains("Congratulations! Order success with Payment ID: "+response.getBody().getPaymentId()));
//		assert paymentline.isPresent();
//		assert order.get().getOrderStatus().equals("Completed");
	}


}
