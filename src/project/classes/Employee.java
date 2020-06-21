package project.classes;

public abstract class Employee {
    private int employeeId;
    protected String name;
    private int age;
    private final String hireDate;
    private int salary;

    public Employee(int employeeId, String name, int age, String hireDate, int salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public Employee(String name, int age, String hireDate, int salary) {
        this.name = name;
        this.age = age;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    protected void employeeInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Hire date: " + hireDate);
        System.out.println("Salary: " + salary);
    }

    public void getAgeWhenHired() {
        String hireYear;
        hireYear = hireDate.substring(6);

        long yearsOld = age - (2020 - Long.parseLong(hireYear));
        System.out.println("Hired at " + yearsOld + " years old");
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHireDate() {
        return hireDate;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return " Name: " + name +
                ", Age: " + getAge() +
                ", Hire date: " + getHireDate() +
                ", Salary: " + getSalary();
    }

    public abstract void printEmployeePosition();
}