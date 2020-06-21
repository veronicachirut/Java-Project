package project.classes;

public class Food extends Product {
    private String ingredients;
    private int amount;

    public Food(int foodId, String productName, String category, String ingredients, int amount) {
        super(foodId, productName, category);
        this.ingredients = ingredients;
        this.amount = amount;
    }

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

    public String toString() {
        return " Name: " + getProductName() +
                ", Category: " + getCategory() +
                ", Ingredients: " + ingredients +
                ", Amount: " + amount;
    }
}