package project;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String restaurantName;
    private String restaurantLocation;
    private List<Chef> chefs = new ArrayList<>();
    private List<Waiter> waiters = new ArrayList<>();
    private List<BillOfFare> menus = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Restaurant(String restaurantName, String restaurantLocation) {
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
    }

    public Restaurant() {
        this.restaurantName = null;
        this.restaurantLocation = null;
    }

    public void addChef(Chef chef) {
        this.chefs.add(chef);
    }

    public void addMenu(BillOfFare menu) {
        this.menus.add(menu);
    }
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void showChefsInfo() {
        for (Chef chef: chefs) {
            chef.chefInfo();
        }
    }

    public void showMenus() {
        for (BillOfFare menu: menus) {
            menu.printMenu();
        }
    }

    public void showOrders() {
        for (Order order: orders) {
            order.showOrders();
        }
    }

//    public void eraseChefs(Chef chef) {
//        chefs.remove(chef);
//    }

    public void eraseChef(String name) {
        chefs.removeIf(chef -> chef.getName().toLowerCase().equals(name.toLowerCase()));
    }


    public List<BillOfFare> getMenus() {
        return menus;
    }
}
