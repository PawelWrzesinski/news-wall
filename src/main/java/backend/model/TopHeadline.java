package backend.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TopHeadline {
    private String author;
    private String title;
    private String description;
    private LocalDate date;
    private String sourceName;
    private String articleUrl;
    private String imageUrl;

    public TopHeadline(
            String author,
            String title,
            String description,
            LocalDate date,
            String sourceName,
            String articleUrl,
            String imageUrl) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.date = date;
        this.sourceName = sourceName;
        this.articleUrl = articleUrl;
        this.imageUrl = imageUrl;
    }
}
