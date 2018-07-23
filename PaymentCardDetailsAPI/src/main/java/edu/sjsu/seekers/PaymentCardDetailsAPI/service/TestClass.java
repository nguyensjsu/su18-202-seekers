package edu.sjsu.seekers.PaymentCardDetailsAPI.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )

public class TestClass {
    String a,b;
    public TestClass(String a, String b)
    {
        this.a=a;
        this.b=b;
    }
}