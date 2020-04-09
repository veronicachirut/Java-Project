package project;

import java.util.ArrayList;
import java.util.List;

public class Waiter extends Employee {

    private List<Food> foods = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();

    public Waiter(String name, int age, String hireDate, int salary) {
        super(name, age, hireDate, salary);
    }

    public void addFoodToDeliver(Food order) {
        foods.add(order);
    }
    public void addDrinkToDeliver(Drink order) {
        drinks.add(order);
    }

    @Override
    public void printEmployeePosition() {
        System.out.println(this.name + "is a waiter");
    }
}
