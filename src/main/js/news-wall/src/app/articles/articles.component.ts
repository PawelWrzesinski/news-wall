import { Component, OnInit } from '@angular/core';
import { Article } from 'src/model/artilcle';
import { TopHeadline } from 'src/model/topheadlines';
import { ArticlesService } from './articles.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {

  articles: Article[] = [];

  topHeadlines: TopHeadline[] = [];

  qParam: string = 'tech'

  countryParam: string = 'pl';
  categoryParam: string = 'technology';
  idParam: number = 0;

  constructor(private articlesService: ArticlesService) { }

  ngOnInit(): void {
    this.getArticles();
    // setTimeout(() => console.log(this.articles), 2000);
  }


  getArticles(): void {
    this.articlesService.getAllArticles(this.qParam).subscribe(articles => {
      this.articles = articles;
    });
  }

  getAllTopHeadlines(countryParam: string, categoryParam: string): void {
    this.articlesService.getAllTopHeadlines(countryParam, categoryParam).subscribe(topHeadlines => this.topHeadlines = topHeadlines);
  }

  getTopHeadlinesById(countryParam: string, categoryParam: string, idParam: number): void {
    this.articlesService.getTopHeadlinesById(countryParam, categoryParam, idParam).subscribe(topHeadlines => this.topHeadlines = topHeadlines);
  }
}
