package edu.sjsu.seekers.UserProfileAPI;

import edu.sjsu.seekers.UserProfileAPI.Request.UserSignInRequest;
import edu.sjsu.seekers.UserProfileAPI.Response.GenericResponse;
import edu.sjsu.seekers.UserProfileAPI.service.UserProfileServiceAPI;
import edu.sjsu.seekers.UserProfileAPI.service.impl.UserProfileServiceAPIImpl;
import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.Address;
import edu.sjsu.seekers.starbucks.model.User;
import edu.sjsu.seekers.UserProfileAPI.Request.CreateNewUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserProfileApiApplicationTests {

	@Autowired
	UserDAO userDAO;

	@Autowired
	AddressDAO addressDAO;

	@Autowired
	UserProfileServiceAPI userProfileServiceAPI;


	/*
	*
		 HAPPY FLOW
	*
	*/

	//user created successfully

	@Test
	public void CreateUserTest1()
	{
		//User details provided
		User newUser = new User();
		Address newAddress = new Address();
		CreateNewUserRequest request = new CreateNewUserRequest();
		Random rand = new Random();
		String authenticationCode = String.valueOf(rand.nextInt(10000) + 1);
		newAddress.setAddressLine1("Kiley Blvd");
		newAddress.setAddressLine2("Apt 111");
		newAddress.setCity("San Jose");
		newAddress.setState("California");
		newAddress.setCountry("USA");
		newAddress.setZipCode(95129);
		addressDAO.save(newAddress);
		System.out.println("Address added");
		Optional<Address> lastInsertedAddress = addressDAO.getLastInsertedRow();
		newUser.setFullName("Subhash");
		newUser.setUserName("Subhash Patil");
		newUser.setPassword("Subhash@123");
		newUser.setEmailId("subhashpatilgs@gmail.com");
		newUser.setPhoneNumber("4089123455");
		newUser.setDateOfBirth("2001/10/8");
		newUser.setIsLoggedIn("N");
		newUser.setIdActiveCustomer("N");
		newUser.setDefaultStoreKey(1);
		newUser.setRewardPoints(0.00);
		newUser.setAddressKey(lastInsertedAddress.get());
		newUser.setVerificationCode(authenticationCode);
		userDAO.save(newUser);


			//ResponseEntity<GenericResponse> response = userProfileServiceAPI.createNewUser();
	}

	@Test
	public void SignInTestCase1()
	{

		UserSignInRequest request = new UserSignInRequest();
		request.setUserName("SindhuPatil");
		request.setpassword("Alphabet123");

		Optional<User> userByUserName = userDAO.findUserByUsername(request.getUserName());
		GenericResponse response = userProfileServiceAPI.authenticateUser(request);

		if(userByUserName.get().getUserName().equals(request.getUserName()) && userByUserName.get().getPassword().equals(request.getpassword()))
		{
			assert HttpStatus.OK.toString().equals(response.getStatusCode());
			assert (response.getMessage().contains(request.getUserName() +" already logged in"));
		}
		else
		{
			assert HttpStatus.OK.toString().equals(response.getStatusCode());
			assert (response.getMessage().contains("Login Failed. Enter correct Username and Password"));
		}

	}

}
