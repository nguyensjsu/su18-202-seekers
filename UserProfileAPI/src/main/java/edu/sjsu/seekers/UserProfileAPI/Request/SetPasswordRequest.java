package edu.sjsu.seekers.UserProfileAPI.Request;

public class SetPasswordRequest {

    private String userName;
    private String password;
    private String confirmPassword;
    private String AuthenticationCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAuthenticationCode() {
        return AuthenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        AuthenticationCode = authenticationCode;
    }
}
