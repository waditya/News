package com.example.aditya.news;

import com.example.aditya.news.model.NewsArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya on 7/29/2017.
 */

public class NewsStore {
    private static List<NewsArticle> newsArticles = new ArrayList<>();

    public static List<NewsArticle> getNewsArticles() {
        return newsArticles;
    }

    public static void setNewsArticles(List<NewsArticle> newsArticles) {
        NewsStore.newsArticles = newsArticles;
    }


}
