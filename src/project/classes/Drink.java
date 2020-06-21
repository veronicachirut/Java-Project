package project.classes;


public class Drink extends Product {
    public boolean sugary;
    public boolean spirituous;

    public Drink(int drinkId, String productName, String category, boolean sugary, boolean sprituous) {
        super(drinkId, productName, category);
        this.sugary = sugary;
        this.spirituous = sprituous;
    }

    public Drink(String productName, String category, boolean sugary, boolean sprituous) {
        super(productName, category);
        this.sugary = sugary;
        this.spirituous = sprituous;
    }

    public boolean isSugary() {
        return sugary;
    }

    public boolean isSpirituous() {
        return spirituous;
    }

    public String toString() {
        return "  Name: " + getProductName() +
                ", Category: " + getCategory() +
                ", Sugary? " + sugary +
                ", Spirituous? " + spirituous;
    }
}
