package com.example.demo.dto;

public class RecommendationRequest {

    private String tags;
    private String difficulty;
    private Integer maxItems;

    public RecommendationRequest() {
    }

    public RecommendationRequest(String tags, String difficulty, Integer maxItems) {
        this.tags = tags;
        this.difficulty = difficulty;
        this.maxItems = maxItems;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(Integer maxItems) {
        this.maxItems = maxItems;
    }
}
