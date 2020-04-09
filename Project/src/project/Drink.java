package project;

public class Drink extends Product {
    public boolean sugary;
    public boolean sprituous;

    public Drink(String productName, String category, boolean sugary, boolean sprituous) {
        super(productName, category);
        this.sugary = sugary;
        this.sprituous = sprituous;
    }
}
