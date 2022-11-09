package backend.controllers;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import backend.model.Article;
import org.springframework.web.bind.annotation.*;
import backend.responses.NewsResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news/")
public class NewsController {
    NewsApiClient newsApiClient = new NewsApiClient("3eefdfedac914be2bd3e50a80170b994");

    List<Article> articleList = new ArrayList<>();

    @GetMapping("{q}")
    public List<Article> getAllNews(@PathVariable final String q) {
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(q)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        articleList.clear();
                        response.getArticles().forEach(article -> {
                            articleList.add(new Article(
                                    article.getContent(),
                                    article.getAuthor(),
                                    article.getTitle(),
                                    article.getDescription(),
                                    LocalDate.parse(article.getPublishedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")),
                                    article.getSource().getName(),
                                    article.getUrl(),
                                    article.getUrlToImage()
                            ));
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
        return articleList;
    }

    @GetMapping("{q}/{id}")
    public NewsResponse getAllNewsById(
            @PathVariable final String q, @PathVariable final int id) {
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(q)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        articleList.clear();
                        response.getArticles().forEach(article -> {
                            articleList.add(new Article(
                                    article.getContent(),
                                    article.getAuthor(),
                                    article.getTitle(),
                                    article.getDescription(),
                                    LocalDate.parse(article.getPublishedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")),
                                    article.getSource().getName(),
                                    article.getUrl(),
                                    article.getUrlToImage()
                            ));
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
        return NewsResponse
                .builder()
                .q(q)
                .article(Article
                        .builder()
                        .articleUrl(articleList.get(id).getArticleUrl())
                        .author(articleList.get(id).getAuthor())
                        .sourceName(articleList.get(id).getSourceName())
                        .imageUrl(articleList.get(id).getImageUrl())
                        .date(articleList.get(id).getDate())
                        .description(articleList.get(id).getDescription())
                        .content(articleList.get(id).getContent())
                        .build())
                .build();
    }
}
