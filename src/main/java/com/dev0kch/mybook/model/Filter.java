package com.dev0kch.mybook.model;

import java.util.List;

public class Filter {

    private List<Long> categories;
    private List<String> languages;

    private int review;

    public Filter(List<Long> categories, List<String> languages, int review) {
        this.categories = categories;
        this.languages = languages;
        this.review = review;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
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
}
