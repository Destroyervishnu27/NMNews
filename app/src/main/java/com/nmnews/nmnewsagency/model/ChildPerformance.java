package com.nmnews.nmnewsagency.model;

public class ChildPerformance {
    String name;
    int newsid;

    public ChildPerformance(String name, int newsId) {
        this.name = name;
        this.newsid = newsId;
    }

    public String getName() {
        return name;
    }
    public int getNewsId() {
        return newsid;
    }
}
