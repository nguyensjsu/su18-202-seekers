package edu.sjsu.seekers.UserProfileAPI.service.impl;

import edu.sjsu.seekers.UserProfileAPI.Request.*;
import edu.sjsu.seekers.UserProfileAPI.Response.ForgotUsernameResponse;
import edu.sjsu.seekers.UserProfileAPI.Response.GenericResponse;
import edu.sjsu.seekers.UserProfileAPI.service.UserProfileServiceAPI;
import edu.sjsu.seekers.starbucks.dao.AddressDAO;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.Address;
import edu.sjsu.seekers.starbucks.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class UserProfileServiceAPIImpl implements UserProfileServiceAPI {

    @Autowired
    UserDAO userDAO;

    @Autowired
    AddressDAO addressDAO;

   private JavaMailSender javaMailSender;

    @Autowired
    public UserProfileServiceAPIImpl(JavaMailSender javaMailSender)
    {
        this.javaMailSender= javaMailSender;
    }

    @Override
    public GenericResponse authenticateUser(UserSignInRequest request) {

        GenericResponse userDetails = new GenericResponse();
        Optional<User> userByUserName = userDAO.findUserByUsername(request.getUserName());
        System.out.println(userByUserName.get().getPassword());
        if (userByUserName.get().getPassword().equals(request.getpassword()) && userByUserName.get().getUserName().equals(request.getUserName()) ) {
            if(userByUserName.get().getIdActiveCustomer().equals("Y"))
            {
                if(userByUserName.get().getIsLoggedIn().equals("N"))
                {
                    userByUserName.get().setIsLoggedIn("Y");
                    userDAO.save(userByUserName.get());
                    userDetails.setMessage("Login Successful");
                }
                else
                {
                    userDetails.setMessage(request.getUserName()+ " already logged in");
                }
            }
            else
            {
                userDetails.setMessage("Account with username " + request.getUserName() + " is not active yet");
            }
        }
        else
            {
            userDetails.setMessage("Login Failed. Enter correct Username and Password");

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
        if(request.getUserName()!=null && request.getPassword()!= null && request.getEmailId()!= null )
        {
            System.out.println("Username password and email id not null");

            Optional<User> existingUserByUserName = userDAO.findUserByUsername(request.getUserName());
            Optional<User> existingUserByEmailId = userDAO.findUserByEmailId(request.getEmailId());

            if (!existingUserByUserName.isPresent())
            {
                if (!existingUserByEmailId.isPresent())
                {
                    System.out.println("Username and Email id is accepted");

                    newAddress.setAddressLine1(request.getAddressLine1());
                    newAddress.setAddressLine2(request.getAddressLine2());
                    newAddress.setCity(request.getCity());
                    newAddress.setState(request.getState());
                    newAddress.setCountry(request.getCountry());
                    newAddress.setZipCode(request.getZipCode());
                    addressDAO.save(newAddress);
                    System.out.println("Address added");
                    Optional<Address> lastInsertedAddress = addressDAO.getLastInsertedRow();
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
                    newUser.setAddressKey(lastInsertedAddress.get());
                    newUser.setVerificationCode(authenticationCode);
                    userDAO.save(newUser);
                    try {
                        sendEmail(request.getEmailId(), authenticationCode);
                    } catch (MailException e)
                    {
                        System.out.println("Error: " + e.getMessage());
                    }

                    createUser.setMessage("A new user with " + request.getUserName() + "  is created. To complete the process, enter the verification code sent to " + request.getEmailId());
                }
                else
                {
                    createUser.setMessage("Email Id " + request.getEmailId() +" already exists.");
                }
            }
            else
            {
                createUser.setMessage("User by "+ request.getUserName()+ " already exists. Try again");
            }
        }
        else
        {
            createUser.setMessage("Username and/or Password cannot be null. Try again");
        }
        createUser.setStatusCode(HttpStatus.OK.toString());
        return createUser;
    }

    @Override
    public GenericResponse updateExistingUser(UpdateExistingUserRequest request) {

        GenericResponse updateUser = new GenericResponse();
        Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
        System.out.println(existingUser.get().getUserKey());
        Optional<Address> existingUserAddress = addressDAO.get(existingUser.get().getAddressKey().getAddressKey());
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
            updateUser.setMessage("User Details updated successfully for "+ request.getUserName());
        }
        else
        {
            updateUser.setMessage("User Details Cannot be updated. Try again");
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
                if(existingUser.get().getIsLoggedIn().equals("Y")) {
                    existingUser.get().setPassword(request.getPassword());
                    userDAO.save(existingUser.get());
                    resetPassword.setMessage("Password updated successfully for " + request.getUserName());
                }
                else
                {
                    resetPassword.setMessage("User " + request.getUserName() + " not logged in");
                }
            }
            else
            {
                resetPassword.setMessage("Password and Confirm Password does not match");
            }
        }
        else
        {
            resetPassword.setMessage("Username: " +request.getUserName() + "does not exist");
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
            if(request.getAuthenticationCode().equals(existingUser.get().getVerificationCode()))
            {
                existingUser.get().setIdActiveCustomer("Y");
                userDAO.save(existingUser.get());
                verifyAccount.setMessage("Account Verified for " + request.getUserName());
            }
            else
            {
                verifyAccount.setMessage("Enter correct Authentication code");
            }
        }
        else
        {
            verifyAccount.setMessage("Username: "+ request.getUserName() + "does not exist");

        }
        verifyAccount.setStatusCode(HttpStatus.OK.toString());
        return verifyAccount;
    }

    @Override
    public GenericResponse forgotUsername(ForgotUsernameRequest request) {

        GenericResponse forgotUsername = new GenericResponse();
        Optional<User> existingUser = userDAO.findUserByEmailId(request.getEmailId());

        if(request.getEmailId().equals(existingUser.get().getEmailId()))
        {
            //forgotUsername.setUserName(existingUser.get().getUserName());
            //forgotUsername.toString();
            try {
                sendUsernameByEmail(existingUser.get().getEmailId(),existingUser.get().getUserName() );
            } catch (MailException e)
            {
                System.out.println("Error: " + e.getMessage());
            }

            forgotUsername.setMessage("Username sent to email id: " + existingUser.get().getEmailId());

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
            deleteUserAccount.setMessage("Account deleted for "+request.getUserName() );
        }
        else
        {
            deleteUserAccount.setMessage("Username: "+ request.getUserName() + "does not exist");
        }

        deleteUserAccount.setStatusCode(HttpStatus.OK.toString());
        return deleteUserAccount;
    }

    @Override
    public GenericResponse forgotPassword(ForgotPasswordRequest request)
    {
        GenericResponse forgotUPassword = new GenericResponse();
        Optional<User> existingUser = userDAO.findUserByEmailId(request.getEmailId());

        if(request.getEmailId().equals(existingUser.get().getEmailId()) && request.getUserName().equals(existingUser.get().getUserName()))
        {

            try {
                Random rand = new Random();
                String authenticationCode = String.valueOf(rand.nextInt(10000) + 1);
                existingUser.get().setVerificationCode(authenticationCode);
                userDAO.save(existingUser.get());

                sendEmail(request.getEmailId(), authenticationCode);

            } catch (MailException e)
            {
                System.out.println("Error: " + e.getMessage());
            }

            forgotUPassword.setMessage("Verification code sent to email id: " + existingUser.get().getEmailId());

        }
        else
        {
            forgotUPassword.setMessage("Enter correct email id");
        }
        forgotUPassword.setStatusCode(HttpStatus.OK.toString());
        return forgotUPassword;
    }

    @Override
    public GenericResponse setPassword(SetPasswordRequest request) {

        GenericResponse setPassword = new GenericResponse();
        Optional<User> existingUser = userDAO.findUserByUsername(request.getUserName());
        System.out.println("username :" + request.getUserName());
        System.out.println("DB username :" + existingUser.get().getUserName());

        if(request.getUserName().equals(existingUser.get().getUserName()) )
        {
            if (request.getAuthenticationCode().equals(existingUser.get().getVerificationCode())) {

                if (request.getPassword().equals(request.getConfirmPassword()))
                {
                    existingUser.get().setPassword(request.getPassword());
                    userDAO.save(existingUser.get());

                    setPassword.setMessage("Password updated successfully for " + request.getUserName());
                }
                else
                    {
                    setPassword.setMessage("Password and Confirm Password does not match");
                }
            }
            else
            {
                setPassword.setMessage("Enter correct Verification code sent to the email id "+ existingUser.get().getEmailId());

            }
        }
        else
        {
            setPassword.setMessage("Username: " +request.getUserName() + "does not exist");
        }

        setPassword.setStatusCode(HttpStatus.OK.toString());
        return setPassword;
    }


    public void sendEmail(String emailId, String code) throws MailException
    {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("sjsu.seekers@gmail.com");
        mail.setTo(emailId);
        mail.setSubject("Starbucks Verification ");
        mail.setText("Verification Code: "+ code );

        javaMailSender.send(mail);

    }

    public void sendUsernameByEmail(String emailId, String userName) throws MailException
    {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("sjsu.seekers@gmail.com");
        mail.setTo(emailId);
        mail.setSubject("Starbucks Notification ");
        mail.setText("Username: "+ userName );

        javaMailSender.send(mail);

    }

    }



