package com.example.sellapp.models;

public class RecommendedModel {
    String name;
    String img_url;
    String rating;
    String description;
    Double price;

    public RecommendedModel() {
    }

    public RecommendedModel(String name, String img_url, String rating, String description, Double price) {
        this.name = name;
        this.img_url = img_url;
        this.rating = rating;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
