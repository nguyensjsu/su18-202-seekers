package edu.sjsu.seekers.UserProfileAPI.Request;

public class VerifyAccountRequest {
    private String userName;
    private String authenticationCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        this.authenticationCode = authenticationCode;
    }
}
