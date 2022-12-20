package com.nmnews.nmnewsagency.model;

/**
 * Created by apple on 11/7/16.
 */
public class Child {

    String name;


    public String getImg(int childPosition) {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getnewsId(int childPosition) {
        return newsId;
    }

    public void setnewsId(String newsId) {
        this.newsId = newsId;
    }

    String img;
    String newsId;

    public Child(String name,String img,String newsId) {
        this.name = name;
        this.img=img;
        this.newsId=newsId;
    }

    public String getName() {
        return name;
    }
}