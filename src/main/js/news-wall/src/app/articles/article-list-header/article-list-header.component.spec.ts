import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleListHeaderComponent } from './article-list-header.component';

describe('ArticleListHeaderComponent', () => {
  let component: ArticleListHeaderComponent;
  let fixture: ComponentFixture<ArticleListHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticleListHeaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleListHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
