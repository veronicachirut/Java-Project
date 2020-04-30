package project.csv;

import project.classes.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadRepository {
    static private ReadRepository instance = null;

    private ReadRepository () {
    }

    public static ReadRepository getInstance() {
        if (instance == null) {
            synchronized (ReadRepository.class) {
                if (instance == null) {
                    instance = new ReadRepository();
                }
            }
        }
        return instance;
    }

    public void readFromChefsRepository(List<Chef> chefs) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("chefs.csv"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] infoChef = line.split(", ");
                Chef chef = new Chef(infoChef[0], Integer.parseInt(infoChef[1]), infoChef[2], Integer.parseInt(infoChef[3]));
                chefs.add(chef);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"chefs.csv\" not found");
        } catch (IOException e) {
            System.out.println("Eroare");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void readFromWaitersRepository(List<Waiter> waiters) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("waiters.csv"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] infoWaiter = line.split(", ");
                Waiter waiter = new Waiter(infoWaiter[0], Integer.parseInt(infoWaiter[1]), infoWaiter[2], Integer.parseInt(infoWaiter[3]));
                waiters.add(waiter);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"waiters.csv\" not found");
        } catch (IOException e) {
            System.out.println("Eroare");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void readFromMenusRepository (List<BillOfFare> menus) {
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("menus.csv"));
            while ( (line = br.readLine()) != null) {
                BillOfFare menu = new BillOfFare();
                String[] infoMenu = line.split(" - ");

                String[] infoFoods = infoMenu[0].split("; ");
                for (String infoFood : infoFoods) {
                    String[] eachInfoForFood = infoFood.split(", ");
                    String name = eachInfoForFood[0];
                    String category = eachInfoForFood[1];
                    String ingredients = eachInfoForFood[2];
                    int amount = Integer.parseInt(eachInfoForFood[3]);
                    Food newFood = new Food(name, category, ingredients, amount);
                    menu.addFoodToMenu(newFood);
                }

                String[] infoDrinks = infoMenu[1].split("; ");
                for (String infoDrink : infoDrinks) {
                    String[] eachInfoForDrink = infoDrink.split(", ");
                    String name = eachInfoForDrink[0];
                    String category = eachInfoForDrink[1];
                    boolean isSugary = Boolean.parseBoolean(eachInfoForDrink[2]);
                    boolean isSprituous = Boolean.parseBoolean(eachInfoForDrink[3]);
                    Drink newDrink = new Drink(name, category, isSugary, isSprituous);
                    menu.addDrinkToMenu(newDrink);
                }
                double price = Double.parseDouble(infoMenu[2]);
                menu.setPriceMenu(price);
                menus.add(menu);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"menus.csv\" not found");
        }
        catch (IOException e) {
            System.out.println("Eroare");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void readFromOrdersRepository(List<Order> orders) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("orders.csv"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] infoOrder = line.split("; ");
                int id = Integer.parseInt(infoOrder[0]);
                String[] orderMenus = infoOrder[1].split(", ");
                List<String> ord = new ArrayList<>();
                for (String orderMenu : orderMenus) {
                    ord.add(orderMenu);
                }
                double price = Double.parseDouble(infoOrder[2]);

                Order order = new Order(id, price, ord);
                orders.add(order);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"orders.csv\" not found");
        } catch (IOException e) {
            System.out.println("Eroare");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}