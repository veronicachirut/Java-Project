package project.classes;

import java.util.ArrayList;
import java.util.List;

public class BillOfFare {
    private List<Food> foods = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private double priceMenu;

    public BillOfFare(List<Food> foods, List<Drink> drinks, double priceMenu) {
        this.foods = foods;
        this.drinks = drinks;
        this.priceMenu = priceMenu;
    }

    public BillOfFare() {
    }

    public void addFoodToMenu(Food food) {
        foods.add(food);
    }
    public void addDrinkToMenu(Drink drink) {
        drinks.add(drink);
    }

    public void setPriceMenu(double priceMenu) {
        this.priceMenu = priceMenu;
    }

    public void printMenu() {
        System.out.println(">>>>>>>>>>>>>>> MENU >>>>>>>>>>>>>>>\nMEALS:");
        for (Food food: foods) {
            System.out.println("" + food.getProductName());
            System.out.println("Category: " + food.getCategory() + ". Ingredients: " + food.getIngredients() + " .Amount: " + food.getAmount());
        }
        System.out.println("DRINKS:");
        for (Drink drink: drinks) {
            System.out.println(drink.getProductName());
            System.out.println("Category: " + drink.getCategory() + ". Is sugary? " + drink.isSugary() + ". Is sprituous? " + drink.isSpirituous());
        }
        System.out.println("PRICE: " + priceMenu + "\n");
    }

    public double getPriceMenu() {
        return priceMenu;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
}