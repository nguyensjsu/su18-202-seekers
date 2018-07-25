package edu.sjsu.seekers.UserProfileAPI.controllers;
import edu.sjsu.seekers.UserProfileAPI.Request.*;
import edu.sjsu.seekers.UserProfileAPI.Response.ForgotUsernameResponse;
import edu.sjsu.seekers.UserProfileAPI.Response.GenericResponse;
import edu.sjsu.seekers.UserProfileAPI.service.UserProfileServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProfileController {

    @Autowired
    UserProfileServiceAPI userProfileServiceAPI;

    @RequestMapping(value="/SignIn",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> authenticateUser(@RequestBody UserSignInRequest request){
            ResponseEntity<GenericResponse> responseEntity = null;
            try {
                System.out.println("UserSignInRequest: " + request);

                GenericResponse response = userProfileServiceAPI.authenticateUser(request);
                responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

            }
            catch (Exception e)
            {
                GenericResponse response = new GenericResponse();
                response.setMessage(e.getMessage());
                response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
                responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
            }
            return responseEntity;
        }

    @RequestMapping(value="/SignOut",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> signOutUser(@RequestBody UserSignOutRequest signOutRequest){
        ResponseEntity<GenericResponse> responseEntity = null;
        try {


            System.out.println("UserSignOutRequest: " + signOutRequest);

            GenericResponse response = userProfileServiceAPI.signOutUser(signOutRequest);
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            GenericResponse response = new GenericResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @RequestMapping(value="/CreateUser",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> CreateUser(@RequestBody CreateNewUserRequest createNewUserRequest)
    {
        ResponseEntity<GenericResponse> responseEntity = null;
        try {

            System.out.println("CreateNewUserRequest: " + createNewUserRequest);

            GenericResponse response = userProfileServiceAPI.createNewUser(createNewUserRequest);
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            GenericResponse response = new GenericResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @RequestMapping(value="/UpdateExistingUser",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> UpdateUser(@RequestBody UpdateExistingUserRequest updateExistingUserRequest)
    {
        ResponseEntity<GenericResponse> responseEntity = null;
        try {

            System.out.println("UpdateExistingUserRequest: " + updateExistingUserRequest);

            GenericResponse response = userProfileServiceAPI.updateExistingUser(updateExistingUserRequest);
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            GenericResponse response = new GenericResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @RequestMapping(value="/ResetPassword",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> ResetUserPassword(@RequestBody ResetUserPasswordRequest resetUserPasswordRequest)
    {
        ResponseEntity<GenericResponse> responseEntity = null;
        try {

            System.out.println("ResetUserPasswordRequest: " + resetUserPasswordRequest);

            GenericResponse response = userProfileServiceAPI.resetExisitngUserPassword(resetUserPasswordRequest);
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            GenericResponse response = new GenericResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;
    }

    @RequestMapping(value="/VerifyAccount",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> verifyAccount(@RequestBody VerifyAccountRequest verifyAccountRequest)
    {
        ResponseEntity<GenericResponse> responseEntity = null;
        try {

            System.out.println("VerifyAccountRequest: " + verifyAccountRequest);

            GenericResponse response = userProfileServiceAPI.verifyAccount(verifyAccountRequest);
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            GenericResponse response = new GenericResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;

    }

    @RequestMapping(value="/ForgotUsername",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ForgotUsernameResponse> forgotUsername(@RequestBody ForgotUsernameRequest forgotUsernameRequest)
    {
        ResponseEntity<ForgotUsernameResponse> responseEntity = null;
        try {

            System.out.println("ForgotUsernameRequest: " + forgotUsernameRequest);

            ForgotUsernameResponse response = userProfileServiceAPI.forgotUsername(forgotUsernameRequest);
            responseEntity = new ResponseEntity<ForgotUsernameResponse>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            ForgotUsernameResponse response = new ForgotUsernameResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<ForgotUsernameResponse>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;

    }

    @RequestMapping(value="/DeleteUser",  method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<GenericResponse> deleteUser(@RequestBody DeleteUserRequest deleteUserRequest)
    {
        ResponseEntity<GenericResponse> responseEntity = null;
        try {

            System.out.println("DeleteUserRequest: " + deleteUserRequest);

            GenericResponse response = userProfileServiceAPI.deleteUser(deleteUserRequest);
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            GenericResponse response = new GenericResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.EXPECTATION_FAILED.toString());
            responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.EXPECTATION_FAILED);
        }
        return responseEntity;

    }



}
