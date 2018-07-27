package edu.sjsu.seekers.UserProfileAPI.service;

import edu.sjsu.seekers.UserProfileAPI.Request.*;
import edu.sjsu.seekers.UserProfileAPI.Response.ForgotUsernameResponse;
import edu.sjsu.seekers.UserProfileAPI.Response.GenericResponse;

public interface UserProfileServiceAPI {

    GenericResponse authenticateUser(UserSignInRequest request);
    GenericResponse signOutUser(UserSignOutRequest request);
    GenericResponse createNewUser(CreateNewUserRequest request);
    GenericResponse updateExistingUser(UpdateExistingUserRequest request);
    GenericResponse resetExisitngUserPassword(ResetUserPasswordRequest request);
    GenericResponse verifyAccount(VerifyAccountRequest request);
    GenericResponse forgotUsername(ForgotUsernameRequest request);
    GenericResponse deleteUser(DeleteUserRequest request);
    GenericResponse forgotPassword(ForgotPasswordRequest request);
    GenericResponse setPassword(SetPasswordRequest request);

}
