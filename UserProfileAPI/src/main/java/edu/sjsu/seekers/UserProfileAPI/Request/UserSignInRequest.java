package edu.sjsu.seekers.UserProfileAPI.Request;

import java.io.Serializable;

public class UserSignInRequest implements Serializable {

    private static final long serialVersionUID = 1L;



    private String userName;
    private String password;

//    public UserSignInRequest(String userName, String password)
//    {
//        super();
//        this.userName=userName;
//        this.password=password;
//    }

    public UserSignInRequest()
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

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserSignInRequest{" +
                "userName='" + userName + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}


