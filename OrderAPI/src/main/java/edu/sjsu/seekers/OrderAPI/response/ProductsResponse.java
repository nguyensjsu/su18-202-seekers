package edu.sjsu.seekers.OrderAPI.response;


public class ProductsResponse extends GenericResponse {



    public ProductsResponse() {

    }
    private static final long serialVersionUID = 1L;

    private String message;
    private String statusCode;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "GenericResponse [message=" + message + ", statusCode=" + statusCode + "]";
    }


}
