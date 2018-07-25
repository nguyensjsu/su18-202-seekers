package edu.sjsu.seekers.PaymentAPI.Response;

import edu.sjsu.seekers.starbucks.model.OrderDetails;

import java.util.List;

public class ReviewOrderDetailsResponse extends GenericResponse {

    private List<OrderDetails> orderDetails;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    private String paymentType;


    public String getOrderDetails() {
        String toReturn ="";

        if(this.orderDetails!=null) {
            for (OrderDetails o : this.orderDetails) {
                toReturn = toReturn + " Product Name='" + o.getProductKey().getProductName() + '\'' + ",Product Description='" + o.getProductKey().getProductDescription() + '\'' + ",Product Quantity='" + o.getOrderQuantity() + '\''+
                ",Toppings='" + o.getToppings() + '\'';
            }
            return toReturn;
        }
        else
        {
            return toReturn;
        }
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        String toReturn = super.toString();

        toReturn = toReturn + " paymentType='" + paymentType + '\'';
        for(OrderDetails o : this.orderDetails){
        }

        return toReturn;
    }
}
