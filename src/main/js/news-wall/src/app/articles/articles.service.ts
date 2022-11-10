import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Article } from 'src/model/artilcle';
import { TopHeadline } from 'src/model/topheadlines';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  topHeadlinesBaseUrl = 'http://localhost:8080/topheadlines';

  articlesBseUrl = 'http://localhost:8080/news';

  constructor(private http: HttpClient) { }

  getAllArticles(q: String): Observable<Article[]> {
    return this.http.get<Article[]>(this.articlesBseUrl + '/' + q + '/');
  }

  getAllTopHeadlines(country: String, category: String): Observable<TopHeadline[]> {
    return this.http.get<Article[]>(this.topHeadlinesBaseUrl + '/' + country + '/' + category + '/')
  }

  getTopHeadlinesById(country: String, category: String, id: number): Observable<TopHeadline[]> {
    return this.http.get<Article[]>(this.topHeadlinesBaseUrl + '/' + country + '/' + category + '/' + id + '/')
  }
}
