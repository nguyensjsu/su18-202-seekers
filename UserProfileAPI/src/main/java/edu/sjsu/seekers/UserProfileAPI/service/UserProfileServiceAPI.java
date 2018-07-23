package edu.sjsu.seekers.UserProfileAPI.service;

import edu.sjsu.seekers.UserProfileAPI.Request.CreateNewUserRequest;
import edu.sjsu.seekers.UserProfileAPI.Request.UserSignInRequest;
import edu.sjsu.seekers.UserProfileAPI.Request.UserSignOutRequest;
import edu.sjsu.seekers.UserProfileAPI.Response.GenericResponse;

public interface UserProfileServiceAPI {

    GenericResponse authenticateUser(UserSignInRequest request);
    GenericResponse signOutUser(UserSignOutRequest request);
    GenericResponse createNewUser(CreateNewUserRequest request);

}
