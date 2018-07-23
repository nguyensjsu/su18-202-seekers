package edu.sjsu.seekers.UserProfileAPI.service.impl;

import edu.sjsu.seekers.UserProfileAPI.Request.CreateNewUserRequest;
import edu.sjsu.seekers.UserProfileAPI.Request.UserSignInRequest;
import edu.sjsu.seekers.UserProfileAPI.Request.UserSignOutRequest;
import edu.sjsu.seekers.UserProfileAPI.Response.GenericResponse;
import edu.sjsu.seekers.UserProfileAPI.service.UserProfileServiceAPI;
import edu.sjsu.seekers.starbucks.dao.UserDAO;
import edu.sjsu.seekers.starbucks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceAPIImpl implements UserProfileServiceAPI {

    @Autowired
    UserDAO userDAO;

    @Override
    public GenericResponse authenticateUser(UserSignInRequest request) {

        GenericResponse userDetails = new GenericResponse();
        Optional<User> userByUserName = userDAO.findUserByUsername(request.getUserName());
        System.out.println(userByUserName.get().getPassword());
        if (userByUserName.get().getPassword().equals(request.getpassword())) {
            userByUserName.get().setIsLoggedIn("Y");
            userDAO.save(userByUserName.get());
            userDetails.setMessage("Login Successful");
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
        if(request.getUserName()!=null && request.getPassword()!= null && request.getEmailId()!= null ) {
            newUser.setFullName(request.getFullName());
            newUser.setUserName(request.getUserName());
            newUser.setPassword(request.getPassword());
            newUser.setEmailId(request.getEmailId());
            newUser.setPhoneNumber(request.getPhoneNumber());
            newUser.setDateOfBirth(request.getDateOfBirth());
            newUser.setIsLoggedIn("N");
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

}

