package edu.sjsu.seekers.starbucks.model;


import javax.persistence.*;

@Entity
public class User {
    /*
        To be modified as per current table structure

     */

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="userid")
    private Integer userid;

    @Column(name="username")
    private String username;

    @Column(name="fullname")
    private String fullname;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
