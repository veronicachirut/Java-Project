package project.csv;

import project.classes.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteRepository {
    static private WriteRepository instance = null;

    private WriteRepository () {
    }

    public static WriteRepository getInstance() {
        if (instance == null) {
            synchronized (WriteRepository.class) {
                if (instance == null) {
                    instance = new WriteRepository();
                }
            }
        }
        return instance;
    }

    public void WriteToChefsRepository(List<Chef> chefs) {
        try (FileWriter file = new FileWriter("chefs.csv", false)) {
            for (Chef chef : chefs ) {
                String newRow = chef.getName() + ", " +
                        chef.getAge() + ", " +
                        chef.getHireDate() + ", " +
                        chef.getSalary() + "\n";
                file.write(newRow);
                file.flush();
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File \"chefs.csv\" not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteToWaitersRepository(List<Waiter> waiters) {
        try (FileWriter file = new FileWriter("waiters.csv", false)) {
            for (Waiter waiter : waiters ) {
                String newRow = waiter.getName() + ", " +
                        waiter.getAge() + ", " +
                        waiter.getHireDate() + ", " +
                        waiter.getSalary() + "\n";
                file.write(newRow);
                file.flush();
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File \"waiters.csv\" not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToMenusRepository (List<BillOfFare> menus) {
        try (FileWriter file = new FileWriter("menus.csv", false)) {
            for (BillOfFare menu : menus) {
                StringBuilder newRow = new StringBuilder();

                for (Food food : menu.getFoods()) {
                    String name = food.getProductName();
                    String category = food.getCategory();
                    String ingredients = food.getIngredients();
                    int amount = food.getAmount();
                    newRow.append(name).append(", ")
                            .append(category).append(", ")
                            .append(ingredients).append(", ")
                            .append(amount).append("; ");
                }
                newRow.append(" - ");
                for (Drink drink : menu.getDrinks()) {
                    String name = drink.getProductName();
                    String category = drink.getCategory();
                    boolean isSugary = drink.isSugary();
                    boolean isSprituous = drink.isSpirituous();
                    newRow.append(name).append(", ")
                            .append(category).append(", ")
                            .append(isSugary).append(", ")
                            .append(isSprituous).append("; ");
                }
                newRow.append(" - ");
                newRow.append(menu.getPriceMenu()).append("\n");
                String row = newRow.toString();
                file.write(row);
                file.flush();
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File \"menus.csv\" not found");
        }
        catch (IOException e) {
            System.out.println("Eroare");
        }
    }

    public void writeToOrdersRepository (List<Order> orders) {
        try (FileWriter file = new FileWriter("orders.csv", false)) {
            for (Order order : orders) {
                String newRow = order.getOrderId() + "; ";
                for (String orderMenu : order.getOrderMenu()) {
                    newRow = newRow + orderMenu + ", ";
                }
                newRow = newRow + "; " +  order.getOrderPrice() + "\n";
                file.write(newRow);
                file.flush();
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File \"orders.csv\" not found");
        }
        catch (IOException e) {
            System.out.println("Eroare");
        }
    }
}
