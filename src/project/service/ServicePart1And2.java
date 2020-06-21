package project.service;
import project.classes.*;

import java.util.*;

public class ServicePart1And2 {

    Restaurant restaurant;

    public ServicePart1And2() {
        this.restaurant = new Restaurant();
    }

    public void Run() {
        restaurant.readChefsFromCSV();
        restaurant.readMenusFromCSV();
        restaurant.readOrdersFromCSV();
        restaurant.readWaitersFromCSV();

        String option = "";
        Scanner input = new Scanner(System.in);

        while(!option.equals("exit")) {
            System.out.println("Write below your next option. Type \"exit\" when you finished");
            option = input.nextLine();

            switch (option) {
                case "add menu":
                    String what = "";
                    Scanner in = new Scanner(System.in);
                    BillOfFare newMenu = new BillOfFare();

                    while(!option.equals("done")) {

                        System.out.println("Add food or drink? Type \"done\" when you finished adding products into the menu");
                        option = input.nextLine();

                        switch (option) {
                            case "add food":
                                System.out.println("You are now adding new food items...");

                                System.out.println("Food name: ");
                                String foodName = "";
                                foodName = input.nextLine();

                                System.out.println("Food category: ");
                                String foodCategory = "";
                                foodCategory = input.nextLine();

                                System.out.println("Food ingredients: ");
                                String foodIngredients = "";
                                foodIngredients = input.nextLine();

                                System.out.println("Food amount: ");
                                int foodAmount = 0;
                                foodAmount = Integer.parseInt(input.nextLine());

                                List<Food> newFoods = new ArrayList<>();
                                Food newFood = new Food(foodName, foodCategory, foodIngredients, foodAmount);
                                newFoods.add(newFood);
                                newMenu.addFoodToMenu(newFood);

                                break;

                            case "add drink":

                                System.out.println("You are now adding new drink items...");

                                System.out.println("Drink name: ");
                                String drinkName = "";
                                drinkName = input.nextLine();

                                System.out.println("Drink category: ");
                                String drinkCategory = "";
                                drinkCategory = input.nextLine();

                                System.out.println("Is it sugary? 1 - yes, 0 - no");
                                boolean sugary = false;
                                sugary = Boolean.parseBoolean(input.nextLine());

                                System.out.println("Does it contain alcohol? 1 - yes, 0 - no ");
                                boolean sprituous = false;
                                sprituous = Boolean.parseBoolean(input.nextLine());

                                List<Drink> newDrinks = new ArrayList<>();
                                Drink newDrink = new Drink(drinkName, drinkCategory, sugary, sprituous);
                                newDrinks.add(newDrink);

                                newMenu.addDrinkToMenu(newDrink);

                                break;

                            case "done":
                                System.out.println("Finished");
                                break;

                            default:
                                System.out.println("The text you typed is not correct");
                                break;
                        }
                    }

                    System.out.println("Price of the menu: ");
                    int priceMenu = Integer.parseInt(input.nextLine());
                    newMenu.setPriceMenu(priceMenu);
                    restaurant.addMenu(newMenu);
                    System.out.println("Menu added successfully\n");
                    break;

                case "show menus":
                    System.out.println("Showing the menus...");
                    restaurant.showMenus();
                    break;

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

                    List<Order> foods = null;

                    Chef newChef = new Chef(chefName, age, hireDate, salary);
                    restaurant.addChef(newChef);
                    System.out.println("Chef added successfully\n");


                case "erase chef":
                    System.out.println("Type the name of the chef you want to erase: ");
                    String chefErasedName = "";
                    chefErasedName = input.nextLine();
                    restaurant.eraseChef(chefErasedName);
                    System.out.println(chefErasedName + " successfully erased");
                    break;

                case "show chefs":
                    System.out.println("Showing the chefs in the restaurant...");
                    restaurant.showChefsInfo();
                    break;

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

                    List<Order> ordersToDeliver = null;

                    Waiter newWaiter = new Waiter(waiterName, waiterAge, waiterHireDate, waiterSalary);
                    restaurant.addWaiter(newWaiter);
                    System.out.println("Waiter added successfully\n");

                    break;

                case "erase waiter":
                    System.out.println("Type the name of the waiter you want to erase: ");
                    String waiterErasedName = "";
                    waiterErasedName = input.nextLine();
                    restaurant.eraseWaiter(waiterErasedName);
                    System.out.println(waiterErasedName + " successfully erased");
                    break;

                case "show waiters":
                    System.out.println("Showing the waiters in the restaurant...");
                    restaurant.showWaiterInfo();
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
                    String newOrders = "";
                    newOrders = input.nextLine();

                    System.out.println("Order price: ");
                    int price = 0;
                    price = Integer.parseInt(input.nextLine());

                    System.out.println("Prepared by: ");
                    int chefId = 0;
                    chefId = Integer.parseInt(input.nextLine());

                    System.out.println("Delivered by: ");
                    int waiterId = 0;
                    waiterId = Integer.parseInt(input.nextLine());

                    Order order = new Order(newOrders, price, chefId, waiterId);
                    restaurant.addOrder(order);

                    break;

                case "show orders":
                    System.out.println("");
                    restaurant.showOrders();
                    break;

                case "exit":
                    System.out.println("Finished");
                    restaurant.writeMenusToCSV();
                    restaurant.writeChefsToCSV();
                    restaurant.writeOrdersToCSV();
                    restaurant.writeWaitersToCSV();
                    break;

                default:
                    System.out.println("Invalid");
            }
        }
        input.close();
    }
}