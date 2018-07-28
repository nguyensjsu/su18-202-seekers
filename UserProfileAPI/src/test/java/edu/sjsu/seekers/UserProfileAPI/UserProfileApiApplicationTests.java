package edu.sjsu.seekers.UserProfileAPI;

import edu.sjsu.seekers.UserProfileAPI.Request.*;
import edu.sjsu.seekers.UserProfileAPI.Response.ForgotUsernameResponse;
import edu.sjsu.seekers.UserProfileAPI.Response.GenericResponse;
import edu.sjsu.seekers.UserProfileAPI.service.UserProfileServiceAPI;
import edu.sjsu.seekers.UserProfileAPI.service.impl.UserProfileServiceAPIImpl;
import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.Address;
import edu.sjsu.seekers.starbucks.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
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

  // Successful login after validating the credentials

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


	// Failed test case - In valid Password

	@Test
	public void SignInTestCase2()
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


   //  invalid username

	@Test
	public void SignOutTestcase1()
	{
		UserSignOutRequest request = new UserSignOutRequest();
		request.setUserName("SindhuPati");

		Optional<User> userByUserName = userDAO.findUserByUsername(request.getUserName());
		GenericResponse response = userProfileServiceAPI.signOutUser(request);

		if (userByUserName.isPresent()) {
			if (userByUserName.get().getIsLoggedIn().equals("Y")) {
				userByUserName.get().setIsLoggedIn("N");
				userDAO.save(userByUserName.get());

				assert HttpStatus.OK.toString().equals(response.getStatusCode());
				assert (response.getMessage().contains("Successfully Logged out"));

			} else {
				assert HttpStatus.OK.toString().equals(response.getStatusCode());
				assert (response.getMessage().contains("Cannot Logout"));

			}
		}
	}

   // Not a active user

	@Test
	public void SignOutTestCase2() {
		UserSignOutRequest request = new UserSignOutRequest();
		request.setUserName("Subhash");

		Optional<User> userByUserName = userDAO.findUserByUsername(request.getUserName());
		GenericResponse response = userProfileServiceAPI.signOutUser(request);

		if (userByUserName.isPresent() && userByUserName.get().getUserName() != null) {
			if (userByUserName.get().getIsLoggedIn().equals("Y")) {
				userByUserName.get().setIsLoggedIn("N");
				userDAO.save(userByUserName.get());

				assert HttpStatus.OK.toString().equals(response.getStatusCode());
				assert (response.getMessage().contains("Successfully Logged out"));

			} else {
				assert HttpStatus.OK.toString().equals(response.getStatusCode());
				assert (response.getMessage().contains("Cannot Logout"));

			}
		}
	}

	// ResetPassword when user is already logged in and password and confirm password match exactly

   @Test
	public void ResetPasswordTest1()
   {

		ResetUserPasswordRequest request = new ResetUserPasswordRequest();
		request.setUserName("SindhuPatil");
		request.setPassword("Alphabet1234");
		request.setConfirmPassword("Alphabet1234");

		Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
		GenericResponse response = userProfileServiceAPI.resetExisitngUserPassword(request);

		if (request.getUserName().equals(existingUser.get().getUserName())) {
			if (request.getPassword().equals(request.getConfirmPassword())) {
				if (existingUser.get().getIsLoggedIn().equals("Y")) {
					existingUser.get().setPassword(request.getPassword());
					userDAO.save(existingUser.get());

					assert HttpStatus.OK.toString().equals(response.getStatusCode());
					assert (response.getMessage().contains("Password updated successfully for " + request.getUserName()));

				}
				else
					{
						assert HttpStatus.OK.toString().equals(response.getStatusCode());
						assert (response.getMessage().contains("User " + request.getUserName() + " not logged in"));
				}
			}
			else
				{
					assert HttpStatus.OK.toString().equals(response.getStatusCode());
					assert (response.getMessage().contains("Password and Confirm Password do not match"));
			}
		}
		else
			{
				assert HttpStatus.OK.toString().equals(response.getStatusCode());
				assert(response.getMessage().contains("Username: " + request.getUserName() + "does not exist"));
		 	}

		 	}

       // Resetpassword when user is already logged in and password and confirm password do not match

	@Test
	public void ResetPasswordTest2()
	{

		ResetUserPasswordRequest request = new ResetUserPasswordRequest();

		request.setUserName("SindhuPatil");
		request.setPassword("Alphabet123");
		request.setConfirmPassword("Alphabet1234");

		Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
		GenericResponse response = userProfileServiceAPI.resetExisitngUserPassword(request);

		if (request.getUserName().equals(existingUser.get().getUserName()))
		{
			if (request.getPassword().equals(request.getConfirmPassword()))
			{
				if (existingUser.get().getIsLoggedIn().equals("Y"))
				{
					existingUser.get().setPassword(request.getPassword());
					userDAO.save(existingUser.get());

					assert HttpStatus.OK.toString().equals(response.getStatusCode());
					assert (response.getMessage().contains("Password updated successfully for " + request.getUserName()));

				}
				else
				{
					assert HttpStatus.OK.toString().equals(response.getStatusCode());
					assert (response.getMessage().contains("User " + request.getUserName() + " not logged in"));
				}
			}
			else
			{
				assert HttpStatus.OK.toString().equals(response.getStatusCode());
				assert (response.getMessage().contains("Password and Confirm Password do not match"));
			}
		}
		else
		{
			assert HttpStatus.OK.toString().equals(response.getStatusCode());
			assert(response.getMessage().contains("Username: " + request.getUserName() + "does not exist"));
		}

	}

	//Forgot password -  allows user who has logged in and has forgot password

	@Test
	public void ForgotpasswordTest1()
	{
		GenericResponse forgotPassword = new GenericResponse();

		ForgotPasswordRequest request = new ForgotPasswordRequest();
		request.setEmailId("sssindhupatil@gmail.com");
		request.setUserName("SindhuPatil");

		Optional<User> existingUser = userDAO.findUserByEmailId(request.getEmailId());
		forgotPassword= userProfileServiceAPI.forgotPassword(request);

		if(request.getEmailId().equals(existingUser.get().getEmailId()) && request.getUserName().equals(existingUser.get().getUserName()))
		{
				Random rand = new Random();
				String authenticationCode = String.valueOf(rand.nextInt(10000) + 1);
				existingUser.get().setVerificationCode(authenticationCode);
				userDAO.save(existingUser.get());

				assert HttpStatus.OK.toString().equals(forgotPassword.getStatusCode());
			assert(forgotPassword.getMessage().contains(("Verification code sent to email id: " + existingUser.get().getEmailId())));

		}
		else
		{
			assert HttpStatus.OK.toString().equals(forgotPassword.getStatusCode());
			assert(forgotPassword.getMessage().contains("Enter correct email id"));
		}
	}


	//Forgot password - Here the input is not correct. Therefore prompts user to enter input
	@Test
	public void ForgotpasswordTest2()
	{
		GenericResponse forgotPassword = new GenericResponse();

		ForgotPasswordRequest request = new ForgotPasswordRequest();
		request.setEmailId("sssindhupatil@gmail.com");
		request.setUserName("SindhuPati");

		Optional<User> existingUser = userDAO.findUserByEmailId(request.getEmailId());
		forgotPassword= userProfileServiceAPI.forgotPassword(request);

		if(request.getEmailId().equals(existingUser.get().getEmailId()) && request.getUserName().equals(existingUser.get().getUserName()))
		{
			Random rand = new Random();
			String authenticationCode = String.valueOf(rand.nextInt(10000) + 1);
			existingUser.get().setVerificationCode(authenticationCode);
			userDAO.save(existingUser.get());

			assert HttpStatus.OK.toString().equals(forgotPassword.getStatusCode());
			assert(forgotPassword.getMessage().contains(("Verification code sent to email id: " + existingUser.get().getEmailId())));

		}
		else
		{
			assert HttpStatus.OK.toString().equals(forgotPassword.getStatusCode());
			assert(forgotPassword.getMessage().contains("Enter correct email id"));
		}
	}


	// ForgotPasswordTest1 test case would send verification code to the entered email id.
	// The code  is 1901. This will reset the password for the user Sindhupatil

	@Test
	public void setPasswordTest1()
	{
		GenericResponse setPassword = new GenericResponse();
		SetPasswordRequest request = new SetPasswordRequest();
		request.setUserName("SindhuPatil");
		request.setAuthenticationCode("1901");
		request.setPassword("Alphabet333");
		request.setConfirmPassword("Alphabet333");

		Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
		setPassword = userProfileServiceAPI.setPassword(request);

		if(request.getUserName().equals(existingUser.get().getUserName()) )
		{
			if (request.getAuthenticationCode().equals(existingUser.get().getVerificationCode())) {

				if (request.getPassword().equals(request.getConfirmPassword()))
				{
					existingUser.get().setPassword(request.getPassword());
					userDAO.save(existingUser.get());

					assert HttpStatus.OK.toString().equals(setPassword.getStatusCode());
					assert(setPassword.getMessage().contains("Password updated successfully for " + request.getUserName()));
				}
				else
				{
					assert HttpStatus.OK.toString().equals(setPassword.getStatusCode());
					assert(setPassword.getMessage().contains("Password and Confirm Password does not match"));
				}
			}
			else
			{
				assert HttpStatus.OK.toString().equals(setPassword.getStatusCode());
				assert(setPassword.getMessage().contains("Enter correct Verification code sent to the email id "+ existingUser.get().getEmailId()));

			}
		}
		else
		{
			assert HttpStatus.OK.toString().equals(setPassword.getStatusCode());
			assert(setPassword.getMessage().contains("Username: " +request.getUserName() + "does not exist"));
		}
	}


	@Test
	public void setPasswordTest2()
	{
		GenericResponse setPassword = new GenericResponse();
		SetPasswordRequest request = new SetPasswordRequest();
		request.setUserName("SindhuPatil");
		request.setAuthenticationCode("1900");
		request.setPassword("Alphabet333");
		request.setConfirmPassword("Alphabet333");

		Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
		setPassword = userProfileServiceAPI.setPassword(request);

		if(request.getUserName().equals(existingUser.get().getUserName()) )
		{
			if (request.getAuthenticationCode().equals(existingUser.get().getVerificationCode())) {

				if (request.getPassword().equals(request.getConfirmPassword()))
				{
					existingUser.get().setPassword(request.getPassword());
					userDAO.save(existingUser.get());

					assert HttpStatus.OK.toString().equals(setPassword.getStatusCode());
					assert(setPassword.getMessage().contains("Password updated successfully for " + request.getUserName()));
				}
				else
				{
					assert HttpStatus.OK.toString().equals(setPassword.getStatusCode());
					assert(setPassword.getMessage().contains("Password and Confirm Password does not match"));
				}
			}
			else
			{
				assert HttpStatus.OK.toString().equals(setPassword.getStatusCode());
				assert(setPassword.getMessage().contains("Enter correct Verification code sent to the email id "+ existingUser.get().getEmailId()));

			}
		}
		else
		{
			assert HttpStatus.OK.toString().equals(setPassword.getStatusCode());
			assert(setPassword.getMessage().contains("Username: " +request.getUserName() + "does not exist"));
		}
	}

	@Test
	public void ForgotUsermameTest1()
	{
		GenericResponse forgotUsername = new GenericResponse();
		ForgotUsernameRequest request = new ForgotUsernameRequest();
		request.setEmailId("sssindhupatil@gmail.com");

		Optional<User> existingUser = userDAO.findUserByEmailId(request.getEmailId());
		forgotUsername = userProfileServiceAPI.forgotUsername(request);

		if(request.getEmailId().equals(existingUser.get().getEmailId()))
		{
			assert HttpStatus.OK.toString().equals(forgotUsername.getStatusCode());
			assert(forgotUsername.getMessage().contains("Username sent to email id: " + existingUser.get().getEmailId()));

		}
		else
		{
			assert HttpStatus.OK.toString().equals(forgotUsername.getStatusCode());
			assert(forgotUsername.getMessage().contains("Enter correct email id"));
		}
	}

	// update user details with proper/existing username- SindhuPatil

	@Test
	public void updateUserTest1()
	{
		GenericResponse updateUser = new GenericResponse();
		UpdateExistingUserRequest request = new UpdateExistingUserRequest();
		request.setUserName("Sindhupatil");
		request.setAddressLine1("Kiely Blvd");
		Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
		updateUser = userProfileServiceAPI.updateExistingUser(request);

		if(existingUser.isPresent())
		{
			Optional<Address> existingUserAddress = addressDAO.get(existingUser.get().getAddressKey().getAddressKey());
			System.out.println(existingUser);

			if(existingUser.get().getUserName().equals(request.getUserName()))
			{
				if (existingUser.get().getIdActiveCustomer().equals("Y"))
				{
					if(existingUser.get().getIsLoggedIn().equals("Y")) {

						if (request.getFullName() != null)
							existingUser.get().setFullName(request.getFullName());
						if (request.getPhoneNumber() != null)
							existingUser.get().setPhoneNumber(request.getPhoneNumber());
						if (request.getDateOfBirth() != null)
							existingUser.get().setDateOfBirth(request.getDateOfBirth());
						if (request.getAddressLine1() != null)
							existingUserAddress.get().setAddressLine1(request.getAddressLine1());
						if (request.getAddressLine2() != null)
							existingUserAddress.get().setAddressLine2(request.getAddressLine2());
						if (request.getCity() != null)
							existingUserAddress.get().setCity(request.getCity());
						if (request.getState() != null)
							existingUserAddress.get().setState(request.getState());
						if (request.getCountry() != null)
							existingUserAddress.get().setCountry(request.getCountry());
						if (request.getZipCode() != null)
							existingUserAddress.get().setZipCode(request.getZipCode());
						addressDAO.save(existingUserAddress.get());
						userDAO.save(existingUser.get());

						assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
						assert(updateUser.getMessage().contains("User Details updated successfully for " + request.getUserName()));
					}
					else
					{
						assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
						assert(updateUser.getMessage().contains("User " + request.getUserName() + " not logged in"));
					}
				}
				else
				{
					assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
					assert(updateUser.getMessage().contains("User " + request.getUserName() + " not active"));
				}
			}
			else
			{
				assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
				assert(updateUser.getMessage().contains("User with " + request.getUserName()+ " Details doesnot exist."));
			}
		}
		else
		{
			assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
			assert(updateUser.getMessage().contains("User with " + request.getUserName()+ " Details doesnot exist."));
			// updateUser.setMessage("User Details Cannot be updated. Try again");
		}
	}

	// Update user test case for not a valid username - SindhuPati (does not exist)

	@Test
	public void updateUserTest2()
	{
		GenericResponse updateUser = new GenericResponse();
		UpdateExistingUserRequest request = new UpdateExistingUserRequest();
		request.setUserName("Sindhupati");
		request.setAddressLine1("Kiely Blvd");
		Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
		updateUser = userProfileServiceAPI.updateExistingUser(request);

		if(existingUser.isPresent())
		{
			Optional<Address> existingUserAddress = addressDAO.get(existingUser.get().getAddressKey().getAddressKey());
			System.out.println(existingUser);

			if(existingUser.get().getUserName().equals(request.getUserName()))
			{
				if (existingUser.get().getIdActiveCustomer().equals("Y"))
				{
					if(existingUser.get().getIsLoggedIn().equals("Y")) {

						if (request.getFullName() != null)
							existingUser.get().setFullName(request.getFullName());
						if (request.getPhoneNumber() != null)
							existingUser.get().setPhoneNumber(request.getPhoneNumber());
						if (request.getDateOfBirth() != null)
							existingUser.get().setDateOfBirth(request.getDateOfBirth());
						if (request.getAddressLine1() != null)
							existingUserAddress.get().setAddressLine1(request.getAddressLine1());
						if (request.getAddressLine2() != null)
							existingUserAddress.get().setAddressLine2(request.getAddressLine2());
						if (request.getCity() != null)
							existingUserAddress.get().setCity(request.getCity());
						if (request.getState() != null)
							existingUserAddress.get().setState(request.getState());
						if (request.getCountry() != null)
							existingUserAddress.get().setCountry(request.getCountry());
						if (request.getZipCode() != null)
							existingUserAddress.get().setZipCode(request.getZipCode());
						addressDAO.save(existingUserAddress.get());
						userDAO.save(existingUser.get());

						assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
						assert(updateUser.getMessage().contains("User Details updated successfully for " + request.getUserName()));
					}
					else
					{
						assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
						assert(updateUser.getMessage().contains("User " + request.getUserName() + " not logged in"));
					}
				}
				else
				{
					assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
					assert(updateUser.getMessage().contains("User " + request.getUserName() + " not active"));
				}
			}
			else
			{
				assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
				assert(updateUser.getMessage().contains("User with " + request.getUserName()+ " Details doesnot exist."));
			}
		}
		else
		{
			assert HttpStatus.OK.toString().equals(updateUser.getStatusCode());
			assert(updateUser.getMessage().contains("User with " + request.getUserName()+ " Details doesnot exist."));
			// updateUser.setMessage("User Details Cannot be updated. Try again");
		}
	}


	// Delete user test case

	@Test
	public void deleteUserTest1()
	{
		GenericResponse deleteUserAccount = new GenericResponse();
		DeleteUserRequest request = new DeleteUserRequest();
		request.setUserName("SindhuPatil");
		request.setPassword("Alphabet333");
		Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
		deleteUserAccount = userProfileServiceAPI.deleteUser(request);

		if(existingUser.isPresent())
		{

			if (request.getUserName().equals(existingUser.get().getUserName()) && request.getPassword().equals(existingUser.get().getPassword())) {
				existingUser.get().setIdActiveCustomer("N");
				userDAO.save(existingUser.get());

				assert HttpStatus.OK.toString().equals(deleteUserAccount.getStatusCode());
				assert(deleteUserAccount.getMessage().contains("Account deleted for " + request.getUserName()));
			}
			else {
				assert HttpStatus.OK.toString().equals(deleteUserAccount.getStatusCode());
				assert(deleteUserAccount.getMessage().contains("Username: " + request.getUserName() + "does not exist"));
			}
		}
		else
		{
			assert HttpStatus.OK.toString().equals(deleteUserAccount.getStatusCode());
			assert(deleteUserAccount.getMessage().contains("Username: " + request.getUserName() + "does not exist"));
		}

	}
}


