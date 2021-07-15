package pl.bak.pageRecognition.uri.body_from_request;

public class Category {
    private double confidence;

    private String name;

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
