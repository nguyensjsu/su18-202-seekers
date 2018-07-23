package edu.sjsu.seekers.OrderAPI;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TestModel {

    String a;
    String b;
    public TestModel(String a, String b)
    {
        this.a = a;
        this.b = b;
    }
}
