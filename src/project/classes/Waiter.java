package project.classes;

import project.classes.Drink;
import project.classes.Employee;
import project.classes.Food;

import java.util.ArrayList;
import java.util.List;

public class Waiter extends Employee {

    private List<Order> orders = new ArrayList<>();

    public Waiter(int waiterId, String name, int age, String hireDate, int salary) {
        super(waiterId, name, age, hireDate, salary);
    }

    public Waiter(String name, int age, String hireDate, int salary) {
        super(name, age, hireDate, salary);
    }

    public void addOrderToDeliver(Order order) {
        orders.add(order);
    }

    protected void waiterInfo() {
        super.employeeInfo();
        System.out.println("List of orders to deliver: " + orders);
    }

    @Override
    public void printEmployeePosition() {
        System.out.println(this.name + "is a waiter");
    }
}