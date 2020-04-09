package project;

public class Food extends Product {
    private String ingredients;
    private int amount;

    public Food(String productName, String category, String ingredients, int amount) {
        super(productName, category);
        this.ingredients = ingredients;
        this.amount = amount;
    }

}