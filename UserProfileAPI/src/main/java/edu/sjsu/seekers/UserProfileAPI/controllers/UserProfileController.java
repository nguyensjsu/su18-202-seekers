package edu.sjsu.seekers.UserProfileAPI.controllers;
import edu.sjsu.seekers.UserProfileAPI.Request.CreateNewUserRequest;
import edu.sjsu.seekers.UserProfileAPI.Request.UserSignInRequest;
import edu.sjsu.seekers.UserProfileAPI.Request.UserSignOutRequest;
import edu.sjsu.seekers.UserProfileAPI.Response.GenericResponse;
import edu.sjsu.seekers.UserProfileAPI.service.UserProfileServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProfileController {


//ObjectMapper mapper = new ObjectMapper();
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

}
