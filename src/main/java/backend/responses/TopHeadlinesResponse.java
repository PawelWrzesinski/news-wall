package backend.responses;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import backend.model.TopHeadline;

import java.util.List;

@Data
@Builder
public class TopHeadlinesResponse {
    private String country;
    private String category;
    @Singular
    private List<TopHeadline> headlines;
}