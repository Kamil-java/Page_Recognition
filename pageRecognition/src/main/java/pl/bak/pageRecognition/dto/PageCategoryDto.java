package pl.bak.pageRecognition.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PageCategoryDto {
    private String category;
    private double confidence;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
