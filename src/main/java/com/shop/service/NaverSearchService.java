package com.shop.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.stereotype.Service;

@Service
public class NaverSearchService {
    private final WebClient webClient;
    private static final String clientId = "2jReynrwSNCvuIj131Gd";
    private static final String clientSecret = "h2bvJWMGQ5";

    @Value("${naver.clientId}")
    private String clientId2= clientId; // 네이버 API 클라이언트 ID
    @Value("${naver.clientSecret}")
    private String clientSecret2 = clientSecret; // 네이버 API 클라이언트 Secret

    //private final RestTemplate restTemplate;

    public NaverSearchService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.naver.com").build();

    }

    public String search(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/search").queryParam("query", query).build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    //24-12-16 코드 재작업


    //24-12-16 코드 주석처리
    /*public JsonNode searchGolfzonArticles() {
        String apiUrl = "https://openapi.naver.com/v1/search/news.json";

        String query = "골프존";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("query", query)
                .queryParam("display", 5);  // 5개의 기사를 가져옵니다.

        String url = uriBuilder.toUriString();

        // 헤더에 클라이언트 ID와 Secret 추가
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId2);
        headers.set("X-Naver-Client-Secret", clientSecret2);

        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);

        // API 호출
        return restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, JsonNode.class).getBody();
    }*/
}
