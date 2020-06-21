package project.classes;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private double orderPrice;
    private String orderMenu;
    private List<String> oldOrderMenu = new ArrayList<>();
    private int chefId;
    private int waiterId;

    public Order(String orderMenu, double orderPrice, int chefId, int waiterId) {
        this.orderPrice = orderPrice;
        this.orderMenu = orderMenu;
        this.chefId = chefId;
        this.waiterId = waiterId;
    }

    public Order(int orderId, String orderMenu, double orderPrice, int chefId, int waiterId) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.orderMenu = orderMenu;
        this.chefId = this.waiterId;
    }

    public Order(int orderId, double orderPrice, List<String> orderMenu) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.oldOrderMenu = orderMenu;
    }

    public void showOrders() {
        System.out.println("Order: " + orderId +
                ", order price: " + orderPrice +
                ", order Menu: " + orderMenu +
                '}');
    }

    public int getOrderId() {
        return orderId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public List<String> getOrderMenu() {
        return oldOrderMenu;
    }

    public String getNewOrderMenu() {
        return orderMenu;
    }

    public int getChefId() {
        return chefId;
    }

    public int getWaiterId() {
        return waiterId;
    }

    @Override
    public String toString() {
        return " Order ID: " + orderId +
                ", Order Menu: " + orderMenu +
                ", Price: " + orderPrice + "\n";
    }
}