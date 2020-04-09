package project;

import java.util.ArrayList;
import java.util.List;

public class Chef extends Employee {

    private List<BillOfFare> foods = new ArrayList<>();

    public Chef(String name, int age, String hireDate, int salary) {
        super(name, age, hireDate, salary);
    }

    public void addMealToPrepare(BillOfFare order) {
        foods.add(order);
        System.out.println(this.name + " is now preparing " + order);
    }

    public Chef(String name, int age, String hireDate, int salary, List<BillOfFare> foods) {
        super(name, age, hireDate, salary);
        this.foods = foods;
    }

    protected void chefInfo() {
        super.employeeInfo();
        System.out.println("List of meals to prepare: " + foods);
    }

    @Override
    public void printEmployeePosition() {
        System.out.println(this.name + "is a chef");
    }
}
