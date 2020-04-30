package project.classes;

import project.classes.Product;

public class ProductPreparation extends Product {
    private int cooksAvailable;
    private int preparationTime;

    public ProductPreparation(String productName, String category, int cooksAvailable, int preparationTime) {
        super(productName, category);
        this.cooksAvailable = cooksAvailable;
        this.preparationTime = preparationTime;
    }

    public int getCooksAvailable() {
        return cooksAvailable;
    }

    public void prepareProduct(String productName) {
        if(getCooksAvailable() > 0) {
            cooksAvailable--;
        }
        else {
            System.out.println("No cook is available for now. Please wait.");
        }
    }
}
