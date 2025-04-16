package com.conference.model.enums;

public enum PaperState {
    SUBMITTED("primary"),
    UNDER_REVIEW("warning"),
    REVIEWED("info"),
    ACCEPTED("success"),
    REJECTED("danger");
    
    private final String color;
    
    PaperState(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }
}