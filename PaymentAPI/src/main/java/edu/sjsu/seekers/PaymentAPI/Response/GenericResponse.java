package edu.sjsu.seekers.PaymentAPI.Response;

import java.io.Serializable;

public class GenericResponse implements Serializable {

    private String ResponseMessage;
    private String statusCode;

    public GenericResponse(String responseMessage, String statusCode) {
        ResponseMessage = responseMessage;
        this.statusCode = statusCode;
    }

    public GenericResponse()
    {

    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "ResponseMessage='" + ResponseMessage + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}