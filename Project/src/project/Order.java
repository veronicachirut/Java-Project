package project;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private double orderPrice;
    private List<String> orderMenu = new ArrayList<>();

    public Order(int orderId, double orderPrice) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
    }

    public Order(int orderId, double orderPrice, List<String> orderMenu) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.orderMenu = orderMenu;
    }

    public void showOrders() {
        System.out.println("Order: " + orderId +
                ", order price: " + orderPrice +
                ", order Menu: " + orderMenu +
                '}');
    }
}
