package edu.sjsu.seekers.UserProfileAPI.Response;

public class ForgotUsernameResponse extends GenericResponse {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ForgotUsernameResponse{" +
                "userName='" + userName + '\'' +
                '}';
    }
}

