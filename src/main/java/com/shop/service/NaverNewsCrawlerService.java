package com.shop.service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NaverNewsCrawlerService {

    private static final String NAVER_NEWS_URL = "https://search.naver.com/search.naver?where=news&query=";

    public List<NewsArticle> crawlNews(String keyword) {
        List<NewsArticle> articles = new ArrayList<>();
        try {
            // 네이버 뉴스 검색 URL 생성
            String searchUrl = NAVER_NEWS_URL + keyword;

            // Jsoup으로 HTML 가져오기
            Document document = Jsoup.connect(searchUrl).get();

            // 기사 리스트 가져오기
            Elements newsElements = document.select(".news_area");

            for (Element newsElement : newsElements) {
                String title = newsElement.select(".news_tit").text(); // 기사 제목
                String link = newsElement.select(".news_tit").attr("href"); // 기사 링크

                // 결과 리스트에 추가
                articles.add(new NewsArticle(title, link));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public static class NewsArticle {
        private String title;
        private String link;

        public NewsArticle(String title, String link) {
            this.title = title;
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }

        @Override
        public String toString() {
            return "NewsArticle{" +
                    "title='" + title + '\'' +
                    ", link='" + link + '\'' +
                    '}';
        }
    }

}
