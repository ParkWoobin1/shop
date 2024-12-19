package com.shop.service;


import org.eclipse.jetty.io.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;


@Service
public class CronService {
    public String gdracademy(String link){
        Connection conn = (Connection) Jsoup.connect(link);

        /*try {

        }*/
        return link;
    }
}
