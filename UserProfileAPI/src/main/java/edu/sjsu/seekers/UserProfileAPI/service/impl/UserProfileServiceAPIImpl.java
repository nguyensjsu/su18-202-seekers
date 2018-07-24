package edu.sjsu.seekers.UserProfileAPI.service.impl;

import edu.sjsu.seekers.UserProfileAPI.Request.*;
import edu.sjsu.seekers.UserProfileAPI.Response.ForgotUsernameResponse;
import edu.sjsu.seekers.UserProfileAPI.Response.GenericResponse;
import edu.sjsu.seekers.UserProfileAPI.service.UserProfileServiceAPI;
import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.Address;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserProfileServiceAPIImpl implements UserProfileServiceAPI {

    @Autowired
    UserDAO userDAO;

    @Autowired
    AddressDAO addressDAO;

    @Override
    public GenericResponse authenticateUser(UserSignInRequest request) {

        GenericResponse userDetails = new GenericResponse();
        Optional<User> userByUserName = userDAO.findUserByUsername(request.getUserName());
        System.out.println(userByUserName.get().getPassword());
        if (userByUserName.get().getPassword().equals(request.getpassword()) && userByUserName.get().getUserName().equals(request.getUserName()) ) {
            if(userByUserName.get().getIdActiveCustomer().equals("Y")) {
                userByUserName.get().setIsLoggedIn("Y");
                userDAO.save(userByUserName.get());
                userDetails.setMessage("Login Successful");
            }
            else
            {
                userDetails.setMessage("Account deleted. Cannot login");
            }
        } else {
            userDetails.setMessage("Login Failed");

        }
        userDetails.setStatusCode(HttpStatus.OK.toString());
        return userDetails;
    }

    @Override
    public GenericResponse signOutUser(UserSignOutRequest request) {

        GenericResponse userDetails = new GenericResponse();
        Optional<User> userByUserName = userDAO.findUserByUsername(request.getUserName());

        if(userByUserName.get().getIsLoggedIn().equals("Y"))
        {
            userByUserName.get().setIsLoggedIn("N");
            userDAO.save(userByUserName.get());
            userDetails.setMessage("Successfully Logged out");
        }
        else
         {
            userDetails.setMessage("Cannot Logout");

        }
        userDetails.setStatusCode(HttpStatus.OK.toString());
        return userDetails;

}

    @Override
    public GenericResponse createNewUser(CreateNewUserRequest request) {

        GenericResponse createUser = new GenericResponse();
        User newUser = new User();
        Address newAddress = new Address();
        Random rand = new Random();
        String authenticationCode = String.valueOf(rand.nextInt(10000) + 1);
        if(request.getUserName()!=null && request.getPassword()!= null && request.getEmailId()!= null ) {
            newAddress.setAddressLine1(request.getAddressLine1());
            newAddress.setAddressLine2(request.getAddressLine2());
            newAddress.setCity(request.getCity());
            newAddress.setState(request.getState());
            newAddress.setCountry(request.getCountry());
            newAddress.setZipCode(request.getZipCode());
            addressDAO.save(newAddress);
            Integer lastInsertedAddressKey = addressDAO.getLastInsertedRow();
            newUser.setFullName(request.getFullName());
            newUser.setUserName(request.getUserName());
            newUser.setPassword(request.getPassword());
            newUser.setEmailId(request.getEmailId());
            newUser.setPhoneNumber(request.getPhoneNumber());
            newUser.setDateOfBirth(request.getDateOfBirth());
            newUser.setIsLoggedIn("N");
            newUser.setIdActiveCustomer("N");
            newUser.setDefaultStoreKey(1);
            newUser.setRewardPoints(0.00);
            newUser.setAddressKey(lastInsertedAddressKey);
            newUser.setIdAccountVerified(authenticationCode);
            userDAO.save(newUser);
            createUser.setMessage("User created successfully");
            }
        else
        {
            createUser.setMessage("Could not create user");
        }
        createUser.setStatusCode(HttpStatus.OK.toString());

        return createUser;
    }

    @Override
    public GenericResponse updateExistingUser(UpdateExistingUserRequest request) {

        GenericResponse updateUser = new GenericResponse();
        Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
        System.out.println(existingUser.get().getUserKey());
        Optional<Address> existingUserAddress = addressDAO.get(existingUser.get().getAddressKey());
        System.out.println(existingUserAddress.get().getAddressKey());
        if(existingUser!= null && existingUserAddress!=null ) {
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
            updateUser.setMessage("User Details updated successfully");
        }
        else
        {
            updateUser.setMessage("User Details Cannot be updated");
        }
        updateUser.setStatusCode(HttpStatus.OK.toString());
        return updateUser;
    }

    @Override
    public GenericResponse resetExisitngUserPassword(ResetUserPasswordRequest request) {

        GenericResponse resetPassword = new GenericResponse();
        Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
        System.out.println("username :" + request.getUserName());
        System.out.println("DB username :" + existingUser.get().getUserName());
        if(request.getUserName().equals(existingUser.get().getUserName()) )
        {
            if(request.getPassword().equals(request.getConfirmPassword()))
            {
                existingUser.get().setPassword(request.getPassword());
                userDAO.save(existingUser.get());
                resetPassword.setMessage("Password updated successfully");
            }
            else
            {
                resetPassword.setMessage("Password and Confirm Password does not match");
            }
        }
        else
        {
            resetPassword.setMessage("Username does not exist");
        }
        resetPassword.setStatusCode(HttpStatus.OK.toString());
        return resetPassword;
    }

    @Override
    public GenericResponse verifyAccount(VerifyAccountRequest request) {

        GenericResponse verifyAccount = new GenericResponse();
        Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());

        if(request.getUserName().equals(existingUser.get().getUserName()))
        {
            if(request.getAuthenticationCode().equals(existingUser.get().getIdAccountVerified()))
            {
                existingUser.get().setIdActiveCustomer("Y");
                userDAO.save(existingUser.get());
                verifyAccount.setMessage("Account Verified");
            }
            else
            {
                verifyAccount.setMessage("Enter correct Authentication code");
            }
        }
        else
        {
            verifyAccount.setMessage("Username does not exist");

        }
        verifyAccount.setStatusCode(HttpStatus.OK.toString());
        return verifyAccount;
    }

    @Override
    public ForgotUsernameResponse forgotUsername(ForgotUsernameRequest request) {

        ForgotUsernameResponse forgotUsername = new ForgotUsernameResponse();
        Optional<User> existingUser = userDAO.findUserByEmailId(request.getEmailId());

        if(request.getEmailId().equals(existingUser.get().getEmailId()))
        {
            forgotUsername.setUserName(existingUser.get().getUserName());
            forgotUsername.toString();
            forgotUsername.setMessage("Username sent to the email id ");

        }
        else
        {
            forgotUsername.setMessage("Enter correct email id");
        }
        forgotUsername.setStatusCode(HttpStatus.OK.toString());
        return forgotUsername;
    }

    @Override
    public GenericResponse deleteUser(DeleteUserRequest request) {

        GenericResponse deleteUserAccount = new GenericResponse();
        Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());

        if(request.getUserName().equals(existingUser.get().getUserName()) && request.getPassword().equals(existingUser.get().getPassword()))
        {
            existingUser.get().setIdActiveCustomer("N");
            userDAO.save(existingUser.get());
            deleteUserAccount.setMessage("Account deleted");
        }
        else
        {
            deleteUserAccount.setMessage("Username does not exist");
        }

        deleteUserAccount.setStatusCode(HttpStatus.OK.toString());
        return deleteUserAccount;
    }

}

