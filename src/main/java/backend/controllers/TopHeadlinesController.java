package backend.controllers;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import backend.model.TopHeadline;
import org.springframework.web.bind.annotation.*;
import backend.responses.TopHeadlinesResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/topheadlines/")
public class TopHeadlinesController {
    NewsApiClient newsApiClient = new NewsApiClient("3eefdfedac914be2bd3e50a80170b994");

    List<TopHeadline> headlineList = new ArrayList<>();

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("{country}/{category}")
    public List<TopHeadline> getAllTopHeadlines(@PathVariable final String country,
            @PathVariable final String category) {
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .country(country)
                        .category(category)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        headlineList.clear();
                        response.getArticles().forEach(article -> {
                            headlineList.add(new TopHeadline(
                                    article.getAuthor(),
                                    article.getTitle(),
                                    article.getDescription(),
                                    LocalDate.parse(article.getPublishedAt(),
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")),
                                    article.getSource().getName(),
                                    article.getUrl(),
                                    article.getUrlToImage()));
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                });
        return headlineList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("{country}/{category}/{id}")
    public TopHeadlinesResponse getTopHeadlinesById(
            @PathVariable final String country, @PathVariable final String category, @PathVariable final int id) {
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .country(country)
                        .category(category)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        headlineList.clear();
                        response.getArticles().forEach(article -> {
                            headlineList.add(new TopHeadline(
                                    article.getAuthor(),
                                    article.getTitle(),
                                    article.getDescription(),
                                    LocalDate.parse(article.getPublishedAt(),
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")),
                                    article.getSource().getName(),
                                    article.getUrl(),
                                    article.getUrlToImage()));
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                });
        return TopHeadlinesResponse
                .builder()
                .country(country)
                .category(category)
                .headline(TopHeadline
                        .builder()
                        .articleUrl(headlineList.get(id).getArticleUrl())
                        .author(headlineList.get(id).getAuthor())
                        .sourceName(headlineList.get(id).getSourceName())
                        .imageUrl(headlineList.get(id).getImageUrl())
                        .date(headlineList.get(id).getDate())
                        .description(headlineList.get(id).getDescription())
                        .build())
                .build();
    }
}
