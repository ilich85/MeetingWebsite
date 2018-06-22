package com.ilich.model;

public enum Gender {
    MAN("Man"), WOMAN("Woman");
    private String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}