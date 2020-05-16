package com.example.masterflow;

public class Ingredient {

    private String ingredient;
    private int quantity;
    private String measure;

    public String getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public Ingredient(String ingredient, int quantity, String measure) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measure = measure;
    }




}
