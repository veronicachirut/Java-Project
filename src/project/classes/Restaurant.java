package project.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import project.csv.*;

public class Restaurant {

    private String restaurantName;
    private String restaurantLocation;
    private List<Chef> chefs = new ArrayList<>();
    private List<Waiter> waiters = new ArrayList<>();
    private List<BillOfFare> menus = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    public Audit audit = Audit.getInstance();
    public ReadRepository readRepository = ReadRepository.getInstance();
    public WriteRepository writeRepository = WriteRepository.getInstance();

    public Restaurant() {
        this.restaurantName = null;
        this.restaurantLocation = null;
    }

    public Restaurant(String restaurantName, String restaurantLocation) {
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
    }

    public void addChef(Chef chef) {
        this.chefs.add(chef);
        audit.WriteToAudit("Added a chef");
    }

    public void addWaiter(Waiter waiter) {
        this.waiters.add(waiter);
        audit.WriteToAudit("Added a waiter");
    }

    public void addMenu(BillOfFare menu) {
        this.menus.add(menu);
        audit.WriteToAudit("Added a menu");
    }
    public void addOrder(Order order) {
        this.orders.add(order);
        audit.WriteToAudit("Added an order");
    }

    public void showChefsInfo() {
        for (Chef chef: chefs) {
            chef.chefInfo();
        }
        audit.WriteToAudit("Showed the chefs");
    }

    public void showWaiterInfo() {
        for (Waiter waiter: waiters) {
            waiter.waiterInfo();
        }
        audit.WriteToAudit("Showed the chefs");
    }

    public void showMenus() {
        for (BillOfFare menu: menus) {
            menu.printMenu();
        }
        audit.WriteToAudit("Showed the menu");
    }

    public void showOrders() {
        for (Order order: orders) {
            order.showOrders();
        }
        audit.WriteToAudit("Showed the orders");
    }

    public void eraseChef(String name) {
        chefs.removeIf(chef -> chef.getName().toLowerCase().equals(name.toLowerCase()));
        audit.WriteToAudit("Erased a chef");
    }

    public void eraseWaiter(String name) {
        waiters.removeIf(waiter -> waiter.getName().toLowerCase().equals(name.toLowerCase()));
        audit.WriteToAudit("Erased a waiter");
    }

    public List<BillOfFare> getMenus() {
        return menus;
    }

    public List<Food> getFoodOfMenu (BillOfFare menu) {
        return menu.getFoods();
    }

    public void writeChefsToCSV () {
        writeRepository.WriteToChefsRepository(chefs);
    }

    public void writeWaitersToCSV () {
        writeRepository.WriteToWaitersRepository(waiters);
    }

    public void writeMenusToCSV () {
        writeRepository.writeToMenusRepository(menus);
    }

    public void writeOrdersToCSV () {
        writeRepository.writeToOrdersRepository(orders);
    }

    public void readChefsFromCSV () {
        readRepository.readFromChefsRepository(chefs);
    }

    public void readWaitersFromCSV () {
        readRepository.readFromWaitersRepository(waiters);
    }

    public void readMenusFromCSV () {
        readRepository.readFromMenusRepository(menus);
    }

    public void readOrdersFromCSV () {
        readRepository.readFromOrdersRepository(orders);
    }
}
