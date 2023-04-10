package com.dev0kch.mybook.model;

import java.util.List;

public class Filter {

    private List<String> categories;
    private List<String> languages;

    private int review;

    private int price;

    public Filter(List<String> categories, List<String> languages, int review, int price) {
        this.categories = categories;
        this.languages = languages;
        this.review = review;
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
