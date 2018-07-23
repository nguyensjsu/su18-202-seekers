package edu.sjsu.seekers.UserProfileAPI.Request;

public class UserSignOutRequest {

    private static final long serialVersionUID = 1L;



    private String userName;

    public UserSignOutRequest()
    {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserSignInRequest{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
