package com.shop.config;


import com.shop.service.NaverNewsCrawlerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyNewsScheduler {

    private final NaverNewsCrawlerService naverNewsCrawlerService;

    public DailyNewsScheduler(NaverNewsCrawlerService naverNewsCrawlerService) {
        this.naverNewsCrawlerService = naverNewsCrawlerService;
    }

    // 매일 아침 9시 정각에 실행
    @Scheduled(cron = "0 0 9 * * *")
    public void fetchGolfzonNews() {
        // 네이버에서 골프존 관련 기사 가져오기

    }
}
