package project.service;
import project.classes.*;
import project.database.DataBase;

import java.sql.SQLException;
import java.util.*;

public class ServicePart3 {

    Restaurant restaurant;

    public ServicePart3() {
        this.restaurant = new Restaurant();
    }

    public void Run() throws SQLException {
        DataBase dataBase = new DataBase();

        String option = "";
        Scanner input = new Scanner(System.in);

        while(!option.equals("exit")) {
            System.out.println("Write below your next option. Type \"exit\" when you finished");
            option = input.nextLine();

            switch (option) {

                case "add chef":
                    System.out.println("You are now adding a new chef...");

                    System.out.println("Chef name: ");
                    String chefName ="";
                    chefName = input.nextLine();

                    System.out.println("Chef age: ");
                    int age = 0;
                    age = Integer.parseInt(input.nextLine());

                    System.out.println("Chef hire date: ");
                    String hireDate = "";
                    hireDate = input.nextLine();

                    System.out.println("Chef salary: ");
                    int salary = 0;
                    salary = Integer.parseInt(input.nextLine());

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Chef newChef = new Chef(chefName, age, hireDate, salary);
                        dataBase.addChef(newChef);
                        restaurant.addChef(newChef);

                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Chef added successfully\n");

                    break;

                case "erase chef":
                    System.out.println("Type the name of the chef you want to erase: ");
                    chefName = input.nextLine();
                    try {

                        Class.forName("com.mysql.jdbc.Driver");
                        dataBase.deleteChef(chefName);

                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Chef successfully erased");
                    break;

                case "update chef":
                    System.out.println("Updating a chef...");
                    System.out.println("What chef do you want to update? Type the ID: ");
                    int chefId = 0;
                    chefId = Integer.parseInt(input.nextLine());
                    System.out.println("What column do you want to update? age/salary: ");
                    String column = "";
                    column = input.nextLine();
                    System.out.println("The new value: ");
                    int value = 0;
                    value = Integer.parseInt(input.nextLine());
                    dataBase.updateChef(column, value, chefId);
                    System.out.println("Chef successfully updated");
                    break;

                case "show chefs":
                    System.out.println("Showing the chefs in the restaurant...");
                    List<Chef> chefs = dataBase.getChefs();
                    System.out.println(chefs);
                    break;

                case "order chefs by hiredate":
                    System.out.println("Showing the chefs in the restaurant ordered by salary...");
                    List<Chef> chefList = dataBase.getOrderedChefsBySalary();
                    System.out.println(chefList);

                case "add waiter":
                    System.out.println("You are now adding a new waiter...");

                    System.out.println("Waiter name: ");
                    String waiterName ="";
                    waiterName = input.nextLine();

                    System.out.println("Waiter age: ");
                    int waiterAge = 0;
                    waiterAge = Integer.parseInt(input.nextLine());

                    System.out.println("Waiter hire date: ");
                    String waiterHireDate = "";
                    waiterHireDate = input.nextLine();

                    System.out.println("Waiter salary: ");
                    int waiterSalary = 0;
                    waiterSalary = Integer.parseInt(input.nextLine());

                    Waiter newWaiter = new Waiter(waiterName, waiterAge, waiterHireDate, waiterSalary);
                    restaurant.addWaiter(newWaiter);

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        dataBase.addWaiter(newWaiter);

                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Waiter added successfully\n");

                    break;

                case "erase waiter":
                    System.out.println("Type the name of the waiter you want to erase: ");
                    waiterName = input.nextLine();
                    try {

                        Class.forName("com.mysql.jdbc.Driver");
                        dataBase.deleteWaiter(waiterName);

                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Waiter successfully erased");
                    break;

                case "update waiter":
                    System.out.println("Updating a waiter...");
                    System.out.println("What waiter do you want to update? Type the ID: ");
                    int waiterId = 0;
                    waiterId = Integer.parseInt(input.nextLine());
                    System.out.println("What column do you want to update? age/salary: ");
                    column = input.nextLine();
                    System.out.println("The new value: ");
                    value = Integer.parseInt(input.nextLine());
                    dataBase.updateWaiter(column, value, waiterId);
                    System.out.println("Waiter successfully updated");
                    break;

                case "show waiters":
                    System.out.println("Showing the waiters in the restaurant...");
                    List<Waiter> waiters = dataBase.getWaiters();
                    System.out.println(waiters);
                    break;

                case "show menus by price":
                    List<BillOfFare> menus = restaurant.getMenus();
                    menus.sort(Comparator.comparing(BillOfFare::getPriceMenu));
                    for(BillOfFare menu : menus)
                        restaurant.showMenus();
                    break;

                case "add order":
                    System.out.println("You are now adding a new order...");

                    System.out.println("What would you like to order?");
                    String newOrder = "";
                    newOrder = input.nextLine();

                    System.out.println("Order price: ");
                    double price = 0;
                    price = Double.parseDouble(input.nextLine());

                    System.out.println("Prepared by: (chefId) ");
                    chefId = Integer.parseInt(input.nextLine());

                    System.out.println("Delivered by: (waiterId) ");
                    waiterId = Integer.parseInt(input.nextLine());

                    Order order = new Order(newOrder, price, chefId, waiterId);
                    restaurant.addOrder(order);

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        dataBase.addOrder(order);

                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "show orders":
                    System.out.println("Showing the orders in the restaurant...");
                    List<Order> orders = dataBase.getOrders();
                    System.out.println(orders);
                    break;

                case "erase order":
                    System.out.println("Type the id of the order you want to erase: ");
                    int orderId = 0;
                    orderId = Integer.parseInt(input.nextLine());
                    try {

                        Class.forName("com.mysql.jdbc.Driver");
                        dataBase.deleteOrder(orderId);

                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Order successfully erased");
                    break;

                case "update order":
                    System.out.println("Updating an order...");

                    System.out.println("What order do you want to update? Type the ID: ");
                    orderId = Integer.parseInt(input.nextLine());

                    System.out.println("The new order menu: ");
                    String newOrderMenu = "";
                    newOrderMenu = input.nextLine();

                    System.out.println("New price: ");
                    price = Integer.parseInt(input.nextLine());

                    dataBase.updateOrderMenu(newOrderMenu, orderId);
                    dataBase.updateOrderPrice(price, orderId);
                    System.out.println("Order menu successfully updated");
                    break;

                case "search food":
                    System.out.println("Searching a food...");
                    String foodName = "";
                    foodName = input.nextLine();
                    System.out.println(dataBase.searchFood(foodName));
                    break;

                case "exit":
                    System.out.println("Finished");
                    break;

                default:
                    System.out.println("Invalid");
            }
        }
        input.close();
    }
}