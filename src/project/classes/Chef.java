package project.classes;

import java.util.ArrayList;
import java.util.List;

public class Chef extends Employee {
    public List<Order> foods = new ArrayList<>();

    public Chef(String name, int age, String hireDate, int salary) {
        super(name, age, hireDate, salary);
    }

    public void addMealToPrepare(Order order) {
        foods.add(order);
        System.out.println(this.name + " is now preparing " + order);
    }

    public Chef(String name, int age, String hireDate, int salary, List<Order> foods) {
        super(name, age, hireDate, salary);
        this.foods = foods;
    }

    protected void chefInfo() {
        super.employeeInfo();
        System.out.println("List of meals to prepare: " + foods);
    }

    public List<Order> getFoods() {
        return foods;
    }

    @Override
    public void printEmployeePosition() {
        System.out.println(this.name + "is a chef");
    }
}
