package backend.responses;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import backend.model.Article;

import java.util.List;
@Data
@Builder
public class NewsResponse {
    private String q;

    @Singular
    private List<Article> articles;
}
