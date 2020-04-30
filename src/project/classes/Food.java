package project.classes;

public class Food extends Product {
    private String ingredients;
    private int amount;

    public Food(String productName, String category, String ingredients, int amount) {
        super(productName, category);
        this.ingredients = ingredients;
        this.amount = amount;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getAmount() {
        return amount;
    }
}