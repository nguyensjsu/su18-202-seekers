package edu.sjsu.seekers.UserProfileAPI.Request;

public class ForgotPasswordRequest {

    private String emailId;
    private String userName;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
