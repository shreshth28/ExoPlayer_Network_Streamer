package com.example.masterflow;

import java.util.List;

public class Item {
    private int id;
    private int ingredientLength;
    private int stepsLength;
    private String name;
    private List<Integer> quantity;
    private List<String> measure;
    private List<String> ingredient;
    private List<String> shortDesciprtion;
    private List<String> description;
    private List<String> videoURL;
    private List<String> thumbnailURL;
    private int servings;
    private String image;

    public int getId() {
        return id;
    }

    public int getIngredientLength() {
        return ingredientLength;
    }

    public int getStepsLength() {
        return stepsLength;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public List<String> getMeasure() {
        return measure;
    }

    public List<String> getIngredient() {
        return ingredient;
    }

    public List<String> getShortDesciprtion() {
        return shortDesciprtion;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getVideoURL() {
        return videoURL;
    }

    public List<String> getThumbnailURL() {
        return thumbnailURL;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public Item(int id, int ingredientLength, int stepsLength, String name, List<Integer> quantity, List<String> measure, List<String> ingredient, List<String> shortDesciprtion, List<String> description, List<String> videoURL, List<String> thumbnailURL, int servings, String image) {
        this.id = id;
        this.ingredientLength = ingredientLength;
        this.stepsLength = stepsLength;
        this.name = name;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
        this.shortDesciprtion = shortDesciprtion;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.servings = servings;
        this.image = image;
    }
}
