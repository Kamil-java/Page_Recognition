package pl.bak.pageRecognition.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "PageCategory")
@Table(
        name = "page_category"
)
public class PageCategory {

    @Id
    @SequenceGenerator(
            name = "page_category_sequence",
            sequenceName = "page_category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "page_category_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "category",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String category;

    @Column(
            name = "confidence",
            nullable = false
    )
    private double confidence;

    @Column(
            name = "url",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageCategory that = (PageCategory) o;

        if (Double.compare(that.confidence, confidence) != 0) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(category, that.category)) return false;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        temp = Double.doubleToLongBits(confidence);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
