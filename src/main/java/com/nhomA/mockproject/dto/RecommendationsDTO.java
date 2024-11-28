package com.nhomA.mockproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecommendationsDTO {
    @JsonProperty("recommendations")
    private List<Long> recommendations;

    public List<Long> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Long> recommendations) {
        this.recommendations = recommendations;
    }
}
